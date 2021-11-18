import org.example.currencyRater.client.ExchangeRatesIoClient;
import org.example.currencyRater.client.ExchangeRatesIoModel;
import org.example.currencyRater.sevice.ConverterService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.HashMap;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ConverterServiceTest {

    private ConverterService converterService;

    @Mock
    private ExchangeRatesIoClient client;

    @Before
    public void initMock() {
        MockitoAnnotations.openMocks(this.getClass());
        Mockito.when(client.convertAmount(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(getServiceResult());
        converterService = new ConverterService(client, "any");
    }

    @Test
    public void test() {
        BigDecimal rate = converterService.convertAmount("USD", "RUB", new BigDecimal(1));
        Assert.assertEquals(rate.doubleValue(), 67.5, 0.001);
    }

    private ExchangeRatesIoModel getServiceResult() {
        ExchangeRatesIoModel result = new ExchangeRatesIoModel();
        result.setSuccess(true);
        result.setRates(new HashMap<>());
        result.getRates().put("USD", new BigDecimal(1.2));
        result.getRates().put("RUB", new BigDecimal(81));
        return result;
    }
}
