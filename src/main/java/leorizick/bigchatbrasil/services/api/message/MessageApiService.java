package leorizick.bigchatbrasil.services.api.message;

import jakarta.transaction.Transactional;
import leorizick.bigchatbrasil.DTO.MessageCreationRequest;
import leorizick.bigchatbrasil.DTO.MessageResponse;
import leorizick.bigchatbrasil.entities.message.Message;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import leorizick.bigchatbrasil.services.domain.message.MessageSender;

@Service
@RequiredArgsConstructor
public class MessageApiService {

    private final MessageSender messageSender;
    private final ModelMapper modelMapper;

    @Transactional
    public MessageResponse sendMessage(MessageCreationRequest messageCreationRequest){
        Message message = modelMapper.map(messageCreationRequest, Message.class);
        message = messageSender.sendMessage(message);
        return modelMapper.map(message, MessageResponse.class);
    }
}
