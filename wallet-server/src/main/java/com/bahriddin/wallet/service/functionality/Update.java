package com.bahriddin.wallet.service.functionality;

import com.bahriddin.wallet.payload.dao.SuperResponse;

public interface Update<D, I> {
    SuperResponse<?> update(D dto, I id);
}
