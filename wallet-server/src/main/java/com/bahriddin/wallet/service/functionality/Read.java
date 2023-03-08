package com.bahriddin.wallet.service.functionality;

import com.bahriddin.wallet.payload.dao.SuperResponse;


public interface Read<I> {
    SuperResponse<?> read(I id);
}
