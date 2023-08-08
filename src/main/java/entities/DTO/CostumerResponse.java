package entities.DTO;

import entities.costumer.enums.TypePlan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostumerResponse {

    private Long id;
    private String name;
    private String telephone;
    private String cpf;
    private String cnpj;
    private String companyName;
    private TypePlan type;
    private Double balance;
    private Double limit;
}
