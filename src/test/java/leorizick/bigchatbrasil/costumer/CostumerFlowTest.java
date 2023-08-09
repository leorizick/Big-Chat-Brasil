package leorizick.bigchatbrasil.costumer;

import io.restassured.RestAssured;
import leorizick.bigchatbrasil.DTO.CostumerResponse;
import leorizick.bigchatbrasil.entities.costumer.TypePlan;
import leorizick.bigchatbrasil.tools.CostumerHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CostumerFlowTest {
    @LocalServerPort
    private int localServerPort;

    private static final String NAME = "Nome Teste";
    private static final String CNPJ = "CNPJ Teste";
    private static final String CPF = "CPF Teste";
    private static final String COMPANY_NAME = "Empresa Teste";
    private static final String TELEPHONE = "Telefone Teste";
    private static final TypePlan PRE_PAGO = TypePlan.PRE_PAGO;
    private static final TypePlan POS_PAGO = TypePlan.POS_PAGO;
    private static final Double AVAIABLE_LIMIT = 1000.00;
    private static final Double BALANCE = 1000.00;

    private static final String BASIC_COSTUMER_API = "api/costumer";
    private static final String GET_RECIPE_API = "api/costumer/{id}";

    @BeforeEach()
    public void setUp(){
        RestAssured.port = localServerPort;
    }

    @Test
    public void shouldCreateACostumer(){
        CostumerResponse response = CostumerHelper.createACostumerPosPago();

        Assertions.assertNotNull(response.getId());
        Assertions.assertEquals(NAME, response.getName());
        Assertions.assertEquals(CNPJ, response.getCnpj());
        Assertions.assertEquals(CPF, response.getCpf());
        Assertions.assertEquals(COMPANY_NAME, response.getCompanyName());
        Assertions.assertEquals(TELEPHONE, response.getTelephone());
        Assertions.assertEquals(POS_PAGO, response.getType());
        Assertions.assertEquals(AVAIABLE_LIMIT, response.getLimit());
        Assertions.assertEquals(BALANCE, response.getBalance());
    }

    @Test
    public void shouldFindACostumerById(){
        CostumerResponse costumer = CostumerHelper.createACostumerPosPago();
        CostumerResponse findCostumer = CostumerHelper.findCostumer(costumer);

        Assertions.assertEquals(costumer.getId(), findCostumer.getId());
    }
}
