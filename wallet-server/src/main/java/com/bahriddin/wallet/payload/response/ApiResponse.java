package com.bahriddin.wallet.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> implements Serializable {
    private boolean success;
    private String message;
    private T data;

    // constructors
    public ApiResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // static factory methods
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data);
    }
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }
    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(true, message);
    }
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message);
    }

}
