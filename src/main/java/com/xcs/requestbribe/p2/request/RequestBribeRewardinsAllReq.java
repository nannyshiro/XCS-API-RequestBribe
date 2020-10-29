package com.xcs.requestbribe.p2.request;

import lombok.Data;

@Data
public class RequestBribeRewardinsAllReq extends RequestBribeRequest {

	private String BRIBE_REWARD_ID;
	private String INDICTMENT_ID;
	private String HAVE_NOTICE;
	private String IS_ACTIVE;

}
