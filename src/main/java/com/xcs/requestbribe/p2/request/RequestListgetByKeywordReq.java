package com.xcs.requestbribe.p2.request;

import lombok.Data;

@Data
public class RequestListgetByKeywordReq extends RequestBribeRequest {

    private String TEXT_SEARCH;
    private String ACCOUNT_OFFICE_CODE;

}
