package top.zhz.boot.config.enums;

import lombok.Getter;

/**
 * @author zhz
 */

@Getter
public enum DrinkType {
   COFFEE("咖啡",20), MILK("牛奶",15), JUICE("果汁",10);

   private final String label;

   private final int money;

   DrinkType(String label, int money){
      this.label = label;
      this.money = money;
   }


}
