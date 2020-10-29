package com.xcs.requestbribe.p2.response;

import lombok.Data;

@Data
public class RequestBribeinsAllRes extends RequestBribeResponse {

	private String IsSuccess;
	private String Msg;
	private String BRIBE_ID;

}
