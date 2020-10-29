package com.xcs.requestbribe.p2.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RequestReward extends RequestBribeModel {

    private String REWARD_ID;
    private String BRIBE_REWARD_ID;
    private String REWARD_OFFICE_ID;
    private String REWARD_CODE;
    private String REWARD_TYPE;
    private String REFFERENCE_CODE;
    private String REWARD_OFFICE_CODE;
    private String REWARD_OFFICE_NAME;
    private String REWARD_DATE;
    private String FIRST_PART_TOTAL;
    private String FIRST_MONEY_TOTAL;
    private String FIRST_MONEY_PER_PART;
    private String FIRST_REMAINDER;
    private String SECOND_PART_TOTAL;
    private String SECOND_MONEY_TOTAL;
    private String SECOND_MONEY_PER_PART;
    private String SECOND_REMAINDER;
    private String REWARD_MONEY;
    private String BRIBE_MONEY;
    private String AUTHORITY_DESC;
    private String AUTHORITY_COMMAND_DESC;
    private String APPROVE_PAYMENT_DATE;
    private String IS_ACTIVE;
    private String IS_PAY;
    private List<RequestRewardDetail> RequestRewardDetail = new ArrayList<>();
    private List<RequestRewardStaff> RequestRewardStaff = new ArrayList<>();

}
