package com.bahriddin.wallet.service.functionality;

import com.bahriddin.wallet.payload.response.ApiResponse;

public interface Create<C> {
    ApiResponse<?> create(C dto);
}
