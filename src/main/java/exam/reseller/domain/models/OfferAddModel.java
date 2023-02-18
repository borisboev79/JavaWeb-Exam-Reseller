package exam.reseller.domain.models;

import exam.reseller.domain.entities.User;
import exam.reseller.domain.enums.ConditionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferAddModel {

    @Size(min = 2, max = 50)
    @NotNull
    private String description;

    @Positive
    @NotNull
    private BigDecimal price;

    @NotNull
    private ConditionType condition;

}
