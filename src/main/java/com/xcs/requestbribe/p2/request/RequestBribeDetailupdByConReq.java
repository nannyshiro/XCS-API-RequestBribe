package com.xcs.requestbribe.p2.request;

import lombok.Data;

@Data
public class RequestBribeDetailupdByConReq extends RequestBribeRequest {

    private String BRIBE_DETAIL_ID;
    private String BRIBE_ID;
    private String PAYMENT_DETAIL_ID;
    private String IS_ACTIVE;

}
