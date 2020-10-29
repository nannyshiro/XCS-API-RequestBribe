package com.xcs.requestbribe.p2.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RequestStaffList extends RequestBribeModel {

    private String INDICTMENT_ID;
    private String NOTICE_ID;
    private String NOTICE_INFORMER;
    private String POSITION;

    private List<RequestNoticeStaff> requestNoticeStaffs = new ArrayList<>();
    private List<RequestArrestStaff> requestArrestStaffs = new ArrayList<>();
    private List<RequestLawsuitStaff> requestLawsuitStaffs = new ArrayList<>();
    private List<RequestProveStaff> requestProveStaffs = new ArrayList<>();
    private List<RequestCompareStaff> requestCompareStaffs = new ArrayList<>();
    private List<RequestRevenueStaff> requestRevenueStaffs = new ArrayList<>();

}
