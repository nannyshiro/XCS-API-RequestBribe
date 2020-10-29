package com.xcs.requestbribe.p2.model;

import lombok.Data;

@Data
public class RequestArrestStaff extends RequestBribeModel {

    private String ARREST_STAFF_ID;
    private String ARREST_TITLE_ID;
    private String ARREST_TITLE_SHORT_NAME_TH;
    private String ARREST_STAFF;
    private String ARREST_MANAGEMENT_POS_NAME;
    private String ARREST_MANAGEMENT_POS_LEVEL;
    private String ARREST_MANAGEMENT_POS_LEVEL_NAME;
    private String ARREST_OPREATION_POS_NAME;
    private String ARREST_OPREATION_POS_LEVEL;
    private String ARREST_OPERATION_POS_LEVEL_NAME;
    private String ARREST_CONTRIBUTOR_ID;
    private String ARREST_CONTRIBUTOR;
    private String ARREST_PART_I;
    private String ARREST_PART_II;

}
