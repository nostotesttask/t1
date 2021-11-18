package org.example.currencyRater.sevice;

import lombok.extern.slf4j.Slf4j;
import org.example.currencyRater.client.ExchangeRatesIoClient;
import org.example.currencyRater.client.ExchangeRatesIoModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Service
public class ConverterService {

    private static final String DEFAULT_BASE_CURRENCY = "EUR";

    private final ExchangeRatesIoClient converter;
    private final String apiKey;

    public ConverterService(ExchangeRatesIoClient converter,
                            @Value("${converter.exchangerates.apiKey}") String apiKey) {
        this.converter = converter;
        this.apiKey = apiKey;
    }

    public BigDecimal convertAmount(String from, String to, BigDecimal amount) {
        ExchangeRatesIoModel result;
        String searchString = String.format("%s,%s", from, to);
        try {
            result = converter.convertAmount(apiKey, DEFAULT_BASE_CURRENCY, searchString);
        } catch (Exception e) {
            log.error("Error occurred during exchange rates service calling, from {}, to {}, amount {}, message {}", from, to, amount, e.getMessage());
            throw new RuntimeException("Error occurred during exchange rates service calling", e);
        }

        if (!result.isSuccess()) {
            log.error("Exchangerates service unable to return success result, from {}, to {}, amount {}", from, to, amount);
            throw new RuntimeException("Exchangerates service unable to return success result");
        }

        BigDecimal rateFrom = result.getRates().get(from);
        BigDecimal rateTo = result.getRates().get(to);

        if(rateFrom == null || rateTo == null) {
            log.error("Exchangerates service returns unexpected result, from {}, to {}, amount {}", from, to, amount);
            throw new RuntimeException("Exchangerates service returns unexpected result");
        }

        return amount.multiply(rateTo).divide(rateFrom, 6, RoundingMode.HALF_UP);
    }
}
