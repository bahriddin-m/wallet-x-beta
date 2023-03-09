package com.bahriddin.wallet.service.functionality;

import com.bahriddin.wallet.payload.response.ApiResponse;


public interface Delete<D> {
    ApiResponse<?> delete(D id);
}
