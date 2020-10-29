package com.xcs.requestbribe.p2.model;

import lombok.Data;

@Data
public class RequestLawsuitStaff extends RequestBribeModel {

    private String LAWSUIT_STAFF_ID;
    private String LAWSUIT_TITLE_ID;
    private String LAWSUIT_TITLE_SHORT_NAME_TH;
    private String LAWSUIT_STAFF;
    private String LAWSUIT_MANAGEMENT_POS_NAME;
    private String LAWSUIT_MANAGEMENT_POS_LEVEL;
    private String LAWSUIT_MANAGEMENT_POS_LEVEL_NAME;
    private String LAWSUIT_OPREATION_POS_NAME;
    private String LAWSUIT_OPREATION_POS_LEVEL;
    private String LAWSUIT_OPERATION_POS_LEVEL_NAME;
    private String LAWSUIT_CONTRIBUTOR_ID;
    private String LAWSUIT_CONTRIBUTOR;
    private String LAWSUIT_PART;

}
