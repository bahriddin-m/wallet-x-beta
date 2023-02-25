package com.bahriddin.wallet.payload.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuperResponse<T> implements Serializable {
    private boolean success;

    private String message;

    private T data;

    public SuperResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SuperResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public SuperResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> SuperResponse<T> successResponse(T data) {
        return new SuperResponse<>(true, data);
    }

    public static <T> SuperResponse<T> successResponse(String message, T data) {
        return new SuperResponse<>(true, message, data);
    }

    public static <T> SuperResponse<T> successResponse(String message) {
        return new SuperResponse<>(true, message);
    }

    public static <T> SuperResponse<T> errorResponse(String message) {
        return new SuperResponse<>(false, message);
    }
}
