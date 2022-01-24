package Personage.NPC;

import Entity.Seller;

public class Merchant implements Seller {

    public enum Goods {
        Potion
    }

    @Override
    public String sell(Merchant.Goods goods) {
        String result = "";
        if(goods == Goods.Potion){
            result = "Potion";
        }
        return result;
    }
}
