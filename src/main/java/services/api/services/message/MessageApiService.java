package services.api.services.message;

import entities.DTO.MessageCreationRequest;
import entities.DTO.MessageResponse;
import entities.message.Message;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import services.domain.services.message.MessageCrud;

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
