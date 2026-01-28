package tech.sumato.statefinance.web.controllers.api;


import org.apache.http.auth.AuthenticationException;
import org.egov.common.contract.response.ResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tech.sumato.statefinance.web.entity.BudgetRegister;
import tech.sumato.statefinance.web.models.BudgetRegisterDTO;
import tech.sumato.statefinance.web.models.GenericRequest;
import tech.sumato.statefinance.web.service.StateFinanceService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class StateFinanceApiController {

    private Logger LOGGER = LoggerFactory.getLogger(StateFinanceApiController.class);


    @Autowired
    private StateFinanceService stateFinanceService;



    @PostMapping(value = "/budgets/submit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String, Object> postForApproval(
            @RequestBody @Valid GenericRequest<BudgetRegisterDTO> requestBody) {


        Map<String, Object> response = new HashMap<>();


        BudgetRegisterDTO budgetRegisterDTO = requestBody.getData();


        BudgetRegister budgetRegister = stateFinanceService.findBudgetRegisterByTenantAndBudgetRegisterId(budgetRegisterDTO.getTenantId(), budgetRegisterDTO.getBudgetRegisterId());



        if (null != budgetRegister) {

            ResponseInfo responseInfo = ResponseInfo.builder()
                    .status(String.valueOf(HttpStatus.CONFLICT.value()))
                    .build();

            response.put("ResponseInfo", responseInfo);
            Map<String, String> errorRes = new HashMap<>();
            errorRes.put("message", "Budget Register already exists!");
            response.put("data", errorRes);

            return response;
        }

        BudgetRegister savedBudgetRegister =  stateFinanceService.saveBudgetRegister(budgetRegisterDTO.mapToEntity());


        ResponseInfo responseInfo = ResponseInfo.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .build();

        response.put("ResponseInfo", responseInfo);
        response.put("data", savedBudgetRegister);



        return response;

    }



//    @GetMapping(value = "/budgets",
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody Map<String, Object> showBudgets() throws AuthenticationException {
//
//        List<BudgetRegisterDTO> budgetRegisters =  stateFinanceService.findAllBudgets();
//
////        throw new AuthenticationException();
//
//        Map<String, Object> response = new HashMap<>();
//
////        response.put("message", "Budget registers !");
////        response.put("status", "success");
////        response.put("data", budgetRegisters);
//
//        ResponseInfo responseInfo =  ResponseInfo.builder().status(String.valueOf(HttpStatus.OK.value())).build();
//
//        response.put("ResponseInfo", responseInfo);
//        response.put("Budgets", budgetRegisters);
//
//        return response;
//
//    }


    @GetMapping(
            value = "/budgets",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody Map<String, Object> showBudgetsPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) throws AuthenticationException {

        Page<BudgetRegisterDTO> budgetPage =
                stateFinanceService.findBudgets(PageRequest.of(page, size));

        ResponseInfo responseInfo = ResponseInfo.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .build();

        Map<String, Object> response = new HashMap<>();
        response.put("ResponseInfo", responseInfo);
        response.put("Budgets", budgetPage.getContent());

        // Pagination metadata
        Map<String, Object> pagination = new HashMap<>();
        pagination.put("page", budgetPage.getNumber());
        pagination.put("size", budgetPage.getSize());
        pagination.put("totalElements", budgetPage.getTotalElements());
        pagination.put("totalPages", budgetPage.getTotalPages());

        response.put("Pagination", pagination);

        return response;
    }


}
