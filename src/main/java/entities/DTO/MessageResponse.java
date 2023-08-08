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
public class MessageResponse {

    private Long id;
    private String tell;
    private boolean whatsapp;
    private String message;
    private CostumerResponse sender;
    private CostumerResponse receiver;
    private Double cost;
}
