package com.xcs.requestbribe.p2.request;

import lombok.Data;

@Data
public class RequestRewardDetailinsAllReq extends RequestBribeRequest {

    private String REWARD_DETAIL_ID;
    private String REWARD_ID;
    private String PAYMENT_ID;
    private String IS_ACTIVE;

}
