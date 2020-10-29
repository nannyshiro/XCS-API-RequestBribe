package com.xcs.requestbribe.p2.request;

import lombok.Data;

import java.util.List;

@Data
public class RequestBribeinsAllReq extends RequestBribeRequest {

    private String BRIBE_ID;
    private String BRIBE_REWARD_ID;
    private String COMMAND_DETAIL_ID;
    private String BRIBE_OFFICE_ID;
    private String BRIBE_CODE;
    private String BRIBE_DATE;
    private String BRIBE_OFFICE_CODE;
    private String BRIBE_OFFICE_NAME;
    private String INFORMER_INFO;
    private String BRIBE_MONEY;
    private String BRIBE_REMAINDER;
    private String AUTHORITY_DESC;
    private String APPROVE_PAYMENT_DATE;
    private String IS_PAY;
    private String IS_ACTIVE;
    private List<RequestBribeDetailinsAllReq> RequestBribeDetail;
    private List<RequestBribeStaffinsAllReq> RequestBribeStaff;

}
