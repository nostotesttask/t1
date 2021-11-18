package org.example.currencyRater.api;

import org.example.currencyRater.sevice.ConverterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/convert")
public class ConverterApi {

    private final ConverterService converterService;

    public ConverterApi(ConverterService converterService) {
        this.converterService = converterService;
    }

    @GetMapping
    public BigDecimal convert(
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("amount") BigDecimal amount) {
        return converterService.convertAmount(from, to, amount);
    }
}
