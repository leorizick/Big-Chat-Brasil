package leorizick.bigchatbrasil.services.domain.mapping;

import leorizick.bigchatbrasil.DTO.MessageCreationRequest;
import leorizick.bigchatbrasil.entities.message.Message;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import leorizick.bigchatbrasil.services.domain.costumer.CostumerCrud;

@Configuration
@RequiredArgsConstructor
public class MessageMapper {

    private final ModelMapper modelMapper;
    private final CostumerCrud costumerCrud;

    @PostConstruct
    public void configure() {
        createFromMessageCreationRequest();
    }

    private void createFromMessageCreationRequest() {
        modelMapper.createTypeMap(MessageCreationRequest.class, Message.class)
                .setConverter(mappingContext -> {
                    var src = mappingContext.getSource();
                    var message = mappingContext.getDestination();

                    if (message == null) {
                        message = new Message();
                    }
                    message.setMessage(src.getMessage());
                    message.setReceiver(costumerCrud.findById(src.getReceiver()));
                    message.setSender(costumerCrud.findById(src.getSender()));
                    message.setTell(src.getTell());
                    message.setWhatsapp(src.isWhatsapp());
                    return message;

                });
    }
}
