package com.xcs.requestbribe.p2.dao;

import com.xcs.requestbribe.p2.model.RequestBribeReward;
import com.xcs.requestbribe.p2.request.RequestBribeRewardgetByIndictmentIDReq;
import com.xcs.requestbribe.p2.request.RequestBribeRewardinsAllReq;
import com.xcs.requestbribe.p2.request.RequestBribeRewardupdDeleteReq;
import com.xcs.requestbribe.p2.response.RequestBribeRewardinsAllRes;

import java.util.List;

public interface RequestBribeRewardDAO {

	public RequestBribeRewardinsAllRes RequestBribeRewardinsAll(RequestBribeRewardinsAllReq req);

}
