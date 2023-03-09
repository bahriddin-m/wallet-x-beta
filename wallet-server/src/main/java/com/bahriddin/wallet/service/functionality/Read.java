package com.bahriddin.wallet.service.functionality;

import com.bahriddin.wallet.payload.response.ApiResponse;


public interface Read<R> {
    ApiResponse<?> read(R id);
}
