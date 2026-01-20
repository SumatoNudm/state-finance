package tech.sumato.statefinance.web.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.SafeHtml;
import tech.sumato.statefinance.web.models.BudgetRegisterDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@Entity
@Table(name = "budget_register")
@Getter
@Setter
public class BudgetRegister implements Serializable {

    public static final String SEQ_BUDGETREGISTER = "SEQ_BUDGETREGISTER";
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "budgetregisterid")
    private Long budgetRegisterId;

    @NotNull
    @Column(name = "tenantid")
    private String tenantId;

    @SafeHtml
    @Length(max = 50)
    @Column(unique = true, updatable = false, name = "budgetregisternumber")
    private String budgetRegisterNumber;

    @SafeHtml
    @Length(max = 100)
    @Column(updatable = false, name = "budgetregistername")
    private String budgetRegisterName;

    @NotNull
    @Column(name = "startingdate")
    private String startingDate;

    @NotNull
    @Column(name = "endingdate")
    private String endingDate;

    @NotNull
    @Column(name = "currentfy")
    private String currentFy;

    @NotNull
    @Column(name = "nextfy")
    private String nextFy;


    public BudgetRegisterDTO toDTO() {

//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        BudgetRegisterDTO budgetRegisterDTO = new BudgetRegisterDTO();
        budgetRegisterDTO.setId(id);
        budgetRegisterDTO.setTenantId(tenantId);
        budgetRegisterDTO.setBudgetRegisterId(budgetRegisterId);
        budgetRegisterDTO.setBudgetRegisterNumber(budgetRegisterNumber);
        budgetRegisterDTO.setBudgetRegisterName(budgetRegisterName);
        budgetRegisterDTO.setStartingDate( startingDate);
        budgetRegisterDTO.setEndingDate(endingDate);
        budgetRegisterDTO.setCurrentFy(currentFy);
        budgetRegisterDTO.setNextFy(nextFy);

        return budgetRegisterDTO;

    }
}
