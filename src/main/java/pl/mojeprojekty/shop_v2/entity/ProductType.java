package pl.mojeprojekty.shop_v2.entity;

import lombok.Getter;

@Getter
public enum ProductType {
    WEALTH("app.wealth"),
    GOOD_HEALTH("app.health"),
    FREEDOM("app.freedom"),
    FRIENDS("app.friends");

    private final String messageKey;

    ProductType(String messageKey) {
        this.messageKey = messageKey;
    }
}
