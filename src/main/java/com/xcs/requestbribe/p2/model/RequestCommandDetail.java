package com.xcs.requestbribe.p2.model;

import lombok.Data;

@Data
public class RequestCommandDetail extends RequestBribeModel {

    private String COMMAND_DETAIL_ID;
    private String COMMAND_ID;
    private String NOTICE_ID;
    private String PART;
    private String IS_ACTIVE;

}
