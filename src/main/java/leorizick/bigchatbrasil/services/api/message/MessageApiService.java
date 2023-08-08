package leorizick.bigchatbrasil.services.api.message;

import leorizick.bigchatbrasil.DTO.MessageCreationRequest;
import leorizick.bigchatbrasil.DTO.MessageResponse;
import leorizick.bigchatbrasil.entities.message.Message;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import leorizick.bigchatbrasil.services.domain.message.MessageCrud;

@Service
@RequiredArgsConstructor
public class MessageApiService {

    private MessageCrud messageCrud;
    private ModelMapper modelMapper;

    public MessageResponse sendMessage(Long senderId, Long receiverId, MessageCreationRequest messageCreationRequest){
        Message message = modelMapper.map(messageCreationRequest, Message.class);
        message = messageCrud.sendMessage(senderId, receiverId, message);
        return modelMapper.map(message, MessageResponse.class);
    }
}
