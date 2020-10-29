package com.xcs.requestbribe.p2.dao;

import com.xcs.requestbribe.p2.model.RequestList;
import com.xcs.requestbribe.p2.request.RequestListgetByConAdvReq;
import com.xcs.requestbribe.p2.request.RequestListgetByKeywordReq;
import com.xcs.requestbribe.p2.request.RequestRewardDetailinsAllReq;
import com.xcs.requestbribe.p2.request.RequestRewardDetailupdDeleteReq;
import com.xcs.requestbribe.p2.response.RequestRewardDetailinsAllRes;
import com.xcs.requestbribe.p2.response.RequestRewardDetailupdDeleteRes;

import java.util.List;

public interface RequestRewardDetailDAO {

    public RequestRewardDetailinsAllRes RequestRewardDetailinsAll(RequestRewardDetailinsAllReq req);

    public RequestRewardDetailupdDeleteRes RequestRewardDetailupdDelete(RequestRewardDetailupdDeleteReq req);

}
