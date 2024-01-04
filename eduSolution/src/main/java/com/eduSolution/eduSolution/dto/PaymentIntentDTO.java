package com.eduSolution.eduSolution.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentIntentDTO {

    public enum Currency{
        PLN, EUR;
    }
    private String description;
    private int amount;
    private Currency currency;
}
