package com.bahriddin.wallet.service.functionality;

import com.bahriddin.wallet.payload.response.ApiResponse;

public interface Update<U, I> {
    ApiResponse<?> update(U dto, I id);
}
