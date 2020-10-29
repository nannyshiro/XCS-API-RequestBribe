package com.xcs.requestbribe.p2.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RequestCommandinsAllupdByConRes extends RequestBribeResponse {

	private String IsSuccess;
	private String Msg;
	private String COMMAND_ID;
	private List<String> COMMAND_DETAIL_ID = new ArrayList<>();

}
