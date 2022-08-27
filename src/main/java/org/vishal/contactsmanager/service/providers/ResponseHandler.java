package org.vishal.contactsmanager.service.providers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> response(Object responseObj, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status.value());
        map.put("data", responseObj);
        map.put("error", null);
        return new ResponseEntity<>(map, status);
    }

    public static ResponseEntity<Object> error(String message, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status.value());
        map.put("data", null);
        map.put("error", message);
        return new ResponseEntity<>(map, status);
    }
}
