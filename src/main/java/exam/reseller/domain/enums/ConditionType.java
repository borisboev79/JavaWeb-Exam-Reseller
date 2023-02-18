package exam.reseller.domain.enums;

import lombok.Getter;

@Getter
public enum ConditionType {
    EXCELLENT("Excellent"),
    GOOD("Good"),
    ACCEPTABLE("Acceptable");

   public final String label;

   ConditionType(String label){
       this.label = label;
   }

}
