package com.xcs.requestbribe.p2.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RequestBribe extends RequestBribeModel {

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
    private String AUTHORITY_DESC;
    private String APPROVE_PAYMENT_DATE;
    private String IS_ACTIVE;
    private String IS_PAY;
    private String NOTICE_CODE;
    private String NOTICE_DATE;
    private String NOTICE_STAFF;
    private String OPERATION_DEPT_CODE;
    private List<RequestBribeDetail> RequestBribeDetail = new ArrayList<>();
    private List<RequestBribeStaff> RequestBribeStaff = new ArrayList<>();

}
