package org.vishal.contactsmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ApplicationException extends Exception {

    /**
     * Instantiates a new Application exception.
     *
     * @param message
     */
    public ApplicationException(String message) {
        super(message);
    }
}
