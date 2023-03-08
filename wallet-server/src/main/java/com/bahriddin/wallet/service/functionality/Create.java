package com.bahriddin.wallet.service.functionality;

import com.bahriddin.wallet.payload.dao.SuperResponse;

public interface Create<D> {
    SuperResponse<?> create(D dto);
}
