package org.example.currencyRater.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "exchangeratesapi", url = "${converter.exchangerates.url}")
public interface ExchangeRatesIoClient {

    @RequestMapping(method = RequestMethod.GET, value = "/latest", produces = "application/json")
    ExchangeRatesIoModel convertAmount(
            @RequestParam("access_key") String access_key,
            @RequestParam("base") String from,
            @RequestParam("symbols") String to);
}
