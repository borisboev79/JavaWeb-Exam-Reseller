package exam.reseller.domain.entities;


import exam.reseller.domain.enums.ConditionType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="conditions")
public class Condition extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private ConditionType name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

}
