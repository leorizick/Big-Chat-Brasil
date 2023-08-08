package services.exceptions;

import org.springframework.http.HttpStatus;

public class OutOfCreditsException extends RestException {

    public OutOfCreditsException(String title, String description) {
        super(HttpStatus.FORBIDDEN, title, description);
    }

    public OutOfCreditsException(String title) {
        super(HttpStatus.FORBIDDEN, title);
    }
}
