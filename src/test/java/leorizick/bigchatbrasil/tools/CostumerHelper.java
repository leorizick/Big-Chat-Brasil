package leorizick.bigchatbrasil.tools;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import leorizick.bigchatbrasil.DTO.CostumerCreationRequest;
import leorizick.bigchatbrasil.DTO.CostumerResponse;
import leorizick.bigchatbrasil.entities.costumer.TypePlan;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;

public class CostumerHelper {

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

    public static CostumerResponse createACostumerPosPago(){
        var request = CostumerCreationRequest.builder()
                .avaiableLimit(AVAIABLE_LIMIT)
                .balance(BALANCE)
                .cnpj(CNPJ)
                .companyName(COMPANY_NAME)
                .cpf(CPF)
                .name(NAME)
                .telephone(TELEPHONE)
                .type(POS_PAGO)
                .build();

        var response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(request)
                .post(BASIC_COSTUMER_API);

        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());

        return response.as(CostumerResponse.class);
    }

    public static CostumerResponse findCostumer(CostumerResponse costumerResponse){
        var getCostumerResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .pathParam("id", costumerResponse.getId())
                .get(GET_RECIPE_API);

        return getCostumerResponse.as(CostumerResponse.class);
    }
}
