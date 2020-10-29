package com.xcs.requestbribe.p2.model;

import lombok.Data;

@Data
public class RequestNoticeStaff extends RequestBribeModel {

    private String NOTICE_STAFF_ID;
    private String NOTICE_TITLE_ID;
    private String NOTICE_TITLE_SHORT_NAME_TH;
    private String NOTICE_STAFF;
    private String NOTICE_MANAGEMENT_POS_NAME;
    private String NOTICE_MANAGEMENT_POS_LEVEL;
    private String NOTICE_MANAGEMENT_POS_LEVEL_NAME;
    private String NOTICE_OPREATION_POS_NAME;
    private String NOTICE_OPREATION_POS_LEVEL;
    private String NOTICE_OPERATION_POS_LEVEL_NAME;
    private String NOTICE_CONTRIBUTOR_ID;
    private String NOTICE_CONTRIBUTOR;
    private String NOTICE_PART;

}
