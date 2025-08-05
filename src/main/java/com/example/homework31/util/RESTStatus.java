package com.example.homework31.util;

import org.springframework.http.HttpStatus;

public interface RESTStatus {
    default int getHttpStatus() {
        return HttpStatus.OK.value();
    }
}
