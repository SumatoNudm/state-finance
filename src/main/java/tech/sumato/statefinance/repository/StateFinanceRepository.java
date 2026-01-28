package tech.sumato.statefinance.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.sumato.statefinance.web.entity.BudgetRegister;

@Repository
public interface StateFinanceRepository extends JpaRepository<BudgetRegister, Long> {


   BudgetRegister findByTenantIdAndBudgetRegisterId(String tenantId, Long budgetRegisterId);
    

    @Query("SELECT b FROM BudgetRegister b WHERE LOWER(b.id) LIKE %:search% OR LOWER(b.tenantId) LIKE %:search%")
    Page<BudgetRegister> searchBudgets(
            @Param("search") String search,
            Pageable pageable
    );

}
