package com.bahriddin.wallet.service.functionality;

import com.bahriddin.wallet.payload.dao.SuperResponse;


public interface Delete<I> {
    SuperResponse<?> delete(I id);
}
