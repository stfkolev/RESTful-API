package com.kolev.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "The user already exists")
public class UserAlreadyExists extends RuntimeException {
}
