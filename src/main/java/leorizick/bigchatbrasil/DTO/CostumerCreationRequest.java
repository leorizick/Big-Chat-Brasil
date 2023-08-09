package leorizick.bigchatbrasil.DTO;

import leorizick.bigchatbrasil.entities.costumer.TypePlan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class CostumerCreationRequest {

    private String name;
    private String telephone;
    private String cpf;
    private String cnpj;
    private String companyName;
    private TypePlan type;
    private Double balance;
    private Double avaiableLimit;

}
