package exam.reseller.repositories;

import exam.reseller.domain.entities.Condition;
import exam.reseller.domain.enums.ConditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, Long> {

    Condition findByName(ConditionType conditionType);
}
