package com.xcs.requestbribe.p2.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import java.util.List;

@Data
public class RequestinsAllreq {

    private RequestBribeinsAllReq requestBribeinsAllReq;

    private RequestBribeDetailinsAllReq requestBribeDetailinsAllReq;

    private List<RequestBribeStaffinsAllReq> requestBribeStaffinsAllReqs;

    private RequestRewardinsAllReq requestRewardinsAllReq;

    private RequestRewardDetailinsAllReq requestRewardDetailinsAllReq;

    private List<RequestRewardStaffinsAllReq> requestRewardStaffinsAllReq;

    private RequestBribeRewardinsAllReq requestBribeRewardinsAllReq;

}
