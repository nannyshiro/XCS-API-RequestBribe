package com.xcs.requestbribe.p2.response;

import lombok.Data;

@Data
public class RequestRewardStaffinsAllRes extends RequestBribeResponse {

	private String IsSuccess;
	private String Msg;
	private String STAFF_ID;

}
