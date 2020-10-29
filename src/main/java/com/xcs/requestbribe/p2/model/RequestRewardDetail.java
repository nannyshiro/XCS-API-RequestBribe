package com.xcs.requestbribe.p2.model;

import lombok.Data;

@Data
public class RequestRewardDetail extends RequestBribeModel {

    private String REWARD_DETAIL_ID;
    private String REWARD_ID;
    private String PAYMENT_ID;
    private String IS_ACTIVE;

}
