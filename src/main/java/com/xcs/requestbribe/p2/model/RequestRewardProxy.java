package com.xcs.requestbribe.p2.model;

import lombok.Data;

@Data
public class RequestRewardProxy extends RequestBribeModel {

    private String PROXY_ID;
    private String REWARD_ID;
    private String PROXY_STAFF_ID;
    private String ATTORNEY_STAFF_ID;
    private String PROXY_NO_1;
    private String PROXY_NO_2;
    private String PROXY_DATE;
    private String PROXY_DESC;
    private String PAYMENT_TYPE;
    private String PAYMENT_NAME;
    private String APPROVE_PAYMENT_DATE;
    private String IS_ACTIVE;

}
