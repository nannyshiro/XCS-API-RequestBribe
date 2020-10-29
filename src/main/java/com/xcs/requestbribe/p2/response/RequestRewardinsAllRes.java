package com.xcs.requestbribe.p2.response;

import lombok.Data;

@Data
public class RequestRewardinsAllRes extends RequestBribeResponse {

	private String IsSuccess;
	private String Msg;
	private String REWARD_ID;

}
