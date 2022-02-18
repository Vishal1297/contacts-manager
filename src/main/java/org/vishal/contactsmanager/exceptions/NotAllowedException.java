package org.vishal.contactsmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NotAllowedException extends ApplicationException {

    /**
     * Instantiates a new Not allowed exception.
     *
     * @param message the message
     */
    public NotAllowedException(String message) {
        super(message);
    }
}
