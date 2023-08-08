package web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import services.api.services.message.MessageApiService;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private MessageApiService messageApiService;

}
