package org.example.currencyRater.client;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class ExchangeRatesIoModel {

    private boolean success;
    private Map<String, BigDecimal> rates;
}
