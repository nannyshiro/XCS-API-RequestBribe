package com.xcs.requestbribe.p2.model;

import lombok.Data;

@Data
public class RequestList extends RequestBribeModel {

    private String NOTICE_ID;
    private String NOTICE_ARREST_ID;
    private String NOTICE_CODE;
    private String NOTICE_DATE;
    private String NOTICE_IS_ACTIVE;
    private String ARREST_ARREST_ID;
    private String ARREST_INDICTMENT_ID;
//    private String ARREST_INDICTMENT_DETAIL_ID;
    private String ARREST_CODE;
    private String ARREST_DATE;
    private String OCCURRENCE_DATE;
    private String ARREST_STAFF_NAME;
    private String ARREST_OPREATION_POS_NAME;
    private String ARREST_OPERATION_OFFICE_NAME;
    private String ARREST_OPERATION_OFFICE_SHORT_NAME;
    private String ARREST_OFFICE_NAME;
    private String SUB_DISTRICT_NAME_TH;
    private String SUB_DISTRICT_NAME_EN;
    private String DISTRICT_NAME_TH;
    private String DISTRICT_NAME_EN;
    private String PROVINCE_NAME_TH;
    private String PROVINCE_NAME_EN;
    private String ARREST_IS_ACTIVE;
    private String LAWSUIT_LAWSUIT_ID;
//    private String LAWSUIT_INDICTMENT_ID;
//    private String LAWSUIT_DETAIL_ID;
//    private String LAWSUIT_INDICTMENT_DETAIL_ID;
    private String LAWSUIT_IS_OUTSIDE;
    private String LAWSUIT_NO;
    private String LAWSUIT_NO_YEAR;
    private String LAWSUIT_DATE;
    private String LAWSUIT_STAF_NAME;
    private String LAWSUIT_OPREATION_POS_NAME;
    private String LAWSUIT_OPERATION_OFFICE_NAME;
    private String LAWSUIT_OPERATION_OFFICE_SHORT_NAME;
    private String LAWSUIT_OFFICE_NAME;
    private String LAWSUIT_TYPE;
    private String LAWSUIT_END;
    private String LAWSUIT_IS_ACTIVE;
    private String COMPARE_ID;
    private String COMPARE_LAWSUIT_ID;
    private String COMPARE_STAFF_NAME;
    private String COMPARE_OPREATION_POS_NAME;
    private String COMPARE_OPERATION_OFFICE_NAME;
    private String COMPARE_OPERATION_OFFICE_SHORT_NAME;
    private String COMPARE_IS_OUTSIDE;
    private String COMPARE_NO;
    private String COMPARE_NO_YEAR;
    private String COMPARE_DATE;
    private String COMPARE_OFFICE_NAME;
    private String COMPARE_IS_ACTIVE;
    private String BRIBE_REWARD_STATUS;
    private String BRIBE_REWARD_PAY;

    private String BRIBE_ID;
    private String REWARD_ID;
    private String BRIBE_CODE;
    private String REWARD_CODE;

}
