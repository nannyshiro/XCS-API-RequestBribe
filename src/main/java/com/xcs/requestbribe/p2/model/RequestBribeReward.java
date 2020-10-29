package com.xcs.requestbribe.p2.model;

import lombok.Data;

@Data
public class RequestBribeReward extends RequestBribeModel {

	private String BRIBE_REWARD_ID;
	private String INDICTMENT_ID;
	private String HAVE_NOTICE;
	private String IS_ACTIVE;

}
