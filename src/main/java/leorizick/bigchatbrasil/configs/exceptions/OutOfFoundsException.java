package leorizick.bigchatbrasil.configs.exceptions;

import org.springframework.http.HttpStatus;

public class OutOfFoundsException extends RestException {

    public OutOfFoundsException(String title, String description) {
        super(HttpStatus.FORBIDDEN, title, description);
    }

    public OutOfFoundsException(String title) {
        super(HttpStatus.FORBIDDEN, title);
    }
}
