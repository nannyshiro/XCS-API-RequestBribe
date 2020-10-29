package com.xcs.requestbribe.p2.request;

import com.xcs.requestbribe.p2.model.RequestCommandDetail;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RequestCommandinsAllupdByConReq extends RequestBribeRequest {

    private String COMMAND_ID;
    private String ARREST_ID;
    private String COMMAND_NO;
    private String COMMAND_DATE;
    private String TOTAL_PART;
    private String IS_ACTIVE;
    private List<RequestCommandDetailinsAllupdByConReq> RequestCommandDetail = new ArrayList<>();

}
