package com.xcs.requestbribe.p2.request;

import lombok.Data;

@Data
public class RequestListgetByConAdvReq extends RequestBribeRequest {

    private String NOTICE_CODE;
    private String NOTICE_DATE_FROM;
    private String NOTICE_DATE_TO;
    private String ARREST_CODE;
    private String ARREST_DATE_FROM;
    private String ARREST_DATE_TO;
    private String OFFICE_NAME;
    private String ARREST_STAFF;
    private String LAWSUIT_NO;
    private String LAWSUIT_NO_YEAR;
    private String LAWSUIT_DATE_FROM;
    private String LAWSUIT_DATE_TO;
    private String LAWSUIT_STAFF;
    private String LAWSUIT_TYPE;
    private String IS_OUTSIDE;
    private String COMPARE_NO;
    private String COMPARE_NO_YEAR;
    private String COMPARE_DATE_FROM;
    private String COMPARE_DATE_TO;
    private String COMPARE_STAFF;
    private String BRIBE_CODE;
    private String REWARD_CODE;
    private String BRIBE_REWARD_STATUS;
    private String BRIBE_REWARD_PAY;
    private String ACCOUNT_OFFICE_CODE;

}
