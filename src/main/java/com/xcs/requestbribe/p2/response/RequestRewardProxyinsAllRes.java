package com.xcs.requestbribe.p2.response;

import lombok.Data;

@Data
public class RequestRewardProxyinsAllRes extends RequestBribeResponse {

	private String IsSuccess;
	private String Msg;
	private String PROXY_ID;

}
