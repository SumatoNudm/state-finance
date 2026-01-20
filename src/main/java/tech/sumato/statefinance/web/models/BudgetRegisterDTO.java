package tech.sumato.statefinance.web.models;

import lombok.Getter;
import lombok.Setter;
import tech.sumato.statefinance.web.entity.BudgetRegister;

import java.util.Date;

@Getter
@Setter
public class BudgetRegisterDTO {

    private Long id;

    private Long budgetRegisterId;

    private String tenantId;

    private String budgetRegisterNumber;

    private String budgetRegisterName;

    private String startingDate;

    private String endingDate;

    private String currentFy;

    private String nextFy;

    public BudgetRegister mapToEntity() {
        BudgetRegister budgetRegister = new BudgetRegister();
        budgetRegister.setBudgetRegisterId(budgetRegisterId);
        budgetRegister.setTenantId(tenantId);
        budgetRegister.setBudgetRegisterName(budgetRegisterName);
        budgetRegister.setBudgetRegisterNumber(budgetRegisterNumber);
        budgetRegister.setStartingDate(startingDate);
        budgetRegister.setEndingDate(endingDate);
        budgetRegister.setCurrentFy(currentFy);
        budgetRegister.setNextFy(nextFy);
        return budgetRegister;
    }
}
