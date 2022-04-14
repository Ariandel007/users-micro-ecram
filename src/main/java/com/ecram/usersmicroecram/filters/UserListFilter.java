package com.ecram.usersmicroecram.filters;

import lombok.Data;

import java.time.Instant;

@Data
public class UserListFilter {
    private Instant startDate;
    private Instant endDate;
    private int page = 0;
    private int size = 1;
    private boolean isAscendingUserId = true;
}
