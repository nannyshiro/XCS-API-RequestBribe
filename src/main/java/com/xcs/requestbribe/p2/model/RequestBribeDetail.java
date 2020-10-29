package com.xcs.requestbribe.p2.model;

import lombok.Data;

import java.util.List;

@Data
public class RequestBribeDetail extends RequestBribeModel {

    private String BRIBE_DETAIL_ID;
    private String BRIBE_ID;
    private String PAYMENT_DETAIL_ID;
    private String IS_ACTIVE;

}
