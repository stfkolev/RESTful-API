package com.kolev.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "The order name already exists")
public class OrderAlreadyExists extends RuntimeException {
}
