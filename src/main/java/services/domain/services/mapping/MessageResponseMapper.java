package services.domain.services.mapping;

import entities.DTO.CostumerResponse;
import entities.DTO.MessageResponse;
import entities.costumer.Costumer;
import entities.message.Message;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MessageResponseMapper {

    private final ModelMapper modelMapper;

    @PostConstruct
    public void configure() {
        createFromMessage();
    }

    private void createFromMessage() {
        modelMapper.createTypeMap(Message.class, MessageResponse.class)
                .setConverter(mappingContext -> {
                    var src = mappingContext.getSource();

                    return MessageResponse.builder()
                            .id(src.getId())
                            .whatsapp(src.isWhatsapp())
                            .message(src.getMessage())
                            .tell(src.getTell())
                            .cost(src.getCost())
                            .build();
                });
    }
}
