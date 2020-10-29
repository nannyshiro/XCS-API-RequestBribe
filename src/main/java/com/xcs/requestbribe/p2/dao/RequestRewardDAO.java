package com.xcs.requestbribe.p2.dao;

import com.xcs.requestbribe.p2.model.RequestList;
import com.xcs.requestbribe.p2.model.RequestReward;
import com.xcs.requestbribe.p2.request.*;
import com.xcs.requestbribe.p2.response.RequestRewardinsAllRes;
import com.xcs.requestbribe.p2.response.RequestRewardupdByConRes;
import com.xcs.requestbribe.p2.response.RequestRewardupdDeleteRes;

import java.util.List;

public interface RequestRewardDAO {

    public RequestReward RequestRewardgetByCon(RequestRewardgetByConReq req);

    public RequestRewardinsAllRes RequestRewardinsAll(RequestRewardinsAllReq req);

    public RequestRewardupdByConRes RequestRewardupdByCon(RequestRewardupdByConReq req);

    public RequestRewardupdDeleteRes RequestRewardupdDelete(RequestRewardupdDeleteReq req);

}
