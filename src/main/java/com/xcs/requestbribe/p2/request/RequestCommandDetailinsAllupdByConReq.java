package com.xcs.requestbribe.p2.request;

import lombok.Data;

import java.util.List;

@Data
public class RequestCommandDetailinsAllupdByConReq extends RequestBribeRequest {

    private String COMMAND_DETAIL_ID;
    private String COMMAND_ID;
    private String NOTICE_ID;
    private String PART;
    private String IS_ACTIVE;

}
