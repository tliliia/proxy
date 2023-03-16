package com.tronina.proxy.mapper;

import java.util.Date;

public class DateMapper {
    public Date asDate(long timestamp) {
        return new Date(timestamp);
    }
}
