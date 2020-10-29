package com.xcs.requestbribe.p2.dao;

import com.xcs.requestbribe.p2.model.RequestList;
import com.xcs.requestbribe.p2.request.RequestBribeDetailinsAllReq;
import com.xcs.requestbribe.p2.request.RequestBribeDetailupdDeleteReq;
import com.xcs.requestbribe.p2.request.RequestListgetByConAdvReq;
import com.xcs.requestbribe.p2.request.RequestListgetByKeywordReq;
import com.xcs.requestbribe.p2.response.RequestBribeDetailinsAllRes;
import com.xcs.requestbribe.p2.response.RequestBribeDetailupdDeleteRes;

import java.util.List;

public interface RequestBribeDetailDAO {

    public RequestBribeDetailinsAllRes RequestBribeDetailinsAll(RequestBribeDetailinsAllReq req);

    public RequestBribeDetailupdDeleteRes RequestBribeDetailupdDelete(RequestBribeDetailupdDeleteReq req);

}
