package com.smshub.org.core.helpers.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class ErrorDetails {
    private int code;
    private String message;
    private String details;

    public ErrorDetails(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

