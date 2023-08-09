package leorizick.bigchatbrasil.web;

import leorizick.bigchatbrasil.DTO.MessageCreationRequest;
import leorizick.bigchatbrasil.DTO.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import leorizick.bigchatbrasil.services.api.message.MessageApiService;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final
    MessageApiService messageApiService;

    @PostMapping(value = "/api/message")
    public ResponseEntity<MessageResponse> sendMessage(@RequestBody MessageCreationRequest message){
        MessageResponse messageResponse = messageApiService.sendMessage(message);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(messageResponse);
    }
}
