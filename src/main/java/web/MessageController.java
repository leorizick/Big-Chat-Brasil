package web;

import entities.DTO.MessageCreationRequest;
import entities.DTO.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import services.api.services.message.MessageApiService;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private MessageApiService messageApiService;

    @PostMapping(value = "/api/message/{senderId}/{receiverId}")
    public ResponseEntity<MessageResponse> sendMessage(@RequestBody MessageCreationRequest message, @PathVariable Long senderId, Long receiverId){
        MessageResponse messageResponse = messageApiService.sendMessage(senderId, receiverId, message);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(messageResponse);
    }
}
