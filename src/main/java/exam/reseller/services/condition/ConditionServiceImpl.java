package exam.reseller.services.condition;

import exam.reseller.domain.entities.Condition;
import exam.reseller.domain.enums.ConditionType;
import exam.reseller.repositories.ConditionRepository;
import exam.reseller.services.DatabaseInitialization;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConditionServiceImpl implements ConditionService, DatabaseInitialization {
    private final ConditionRepository conditionRepository;

    @Autowired
    public ConditionServiceImpl(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }


    @PostConstruct
    @Override
    public void dbInit() {
        if(!isDbInit()) {

            List<Condition> conditions = new ArrayList<>();
            conditions.add(Condition.builder().name(ConditionType.EXCELLENT).description("Excellent condition").build());
            conditions.add(Condition.builder().name(ConditionType.GOOD).description("Good condition").build());
            conditions.add(Condition.builder().name(ConditionType.ACCEPTABLE).description("Acceptable condition").build());


            this.conditionRepository.saveAllAndFlush(conditions);
        }
    }

    @Override
    public boolean isDbInit() {
        return this.conditionRepository.count() > 0;
    }
}
