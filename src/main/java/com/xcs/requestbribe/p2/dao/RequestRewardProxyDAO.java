package com.xcs.requestbribe.p2.dao;

import com.xcs.requestbribe.p2.model.RequestList;
import com.xcs.requestbribe.p2.model.RequestRewardProxy;
import com.xcs.requestbribe.p2.request.RequestListgetByConAdvReq;
import com.xcs.requestbribe.p2.request.RequestRewardProxygetByConReq;
import com.xcs.requestbribe.p2.request.RequestRewardProxyinsAllReq;
import com.xcs.requestbribe.p2.response.RequestRewardProxyinsAllRes;

import java.util.List;

public interface RequestRewardProxyDAO {

    public RequestRewardProxy RequestRewardProxygetByCon(RequestRewardProxygetByConReq req);

    public RequestRewardProxyinsAllRes RequestRewardProxyinsAll(RequestRewardProxyinsAllReq req);

}
