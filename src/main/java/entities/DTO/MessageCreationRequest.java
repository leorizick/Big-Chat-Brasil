package entities.DTO;

import entities.costumer.enums.TypePlan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MessageCreationRequest {

    private String tell;
    private boolean whatsapp;
    private String message;
    private Long sender;
    private Long receiver;
}
