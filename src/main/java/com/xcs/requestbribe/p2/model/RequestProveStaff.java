package com.xcs.requestbribe.p2.model;

import lombok.Data;

@Data
public class RequestProveStaff extends RequestBribeModel {

    private String PROVE_STAFF_ID;
    private String PROVE_TITLE_ID;
    private String PROVE_TITLE_SHORT_NAME_TH;
    private String PROVE_STAFF;
    private String PROVE_MANAGEMENT_POS_NAME;
    private String PROVE_MANAGEMENT_POS_LEVEL;
    private String PROVE_MANAGEMENT_POS_LEVEL_NAME;
    private String PROVE_OPREATION_POS_NAME;
    private String PROVE_OPREATION_POS_LEVEL;
    private String PROVE_OPERATION_POS_LEVEL_NAME;
    private String PROVE_CONTRIBUTOR_ID;
    private String PROVE_CONTRIBUTOR;
    private String PROVE_PART;

}
