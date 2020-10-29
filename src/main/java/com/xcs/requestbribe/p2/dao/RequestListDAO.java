package com.xcs.requestbribe.p2.dao;

import com.xcs.requestbribe.p2.model.RequestList;
import com.xcs.requestbribe.p2.model.RequestListConAdv;
import com.xcs.requestbribe.p2.request.RequestListgetByConAdvReq;
import com.xcs.requestbribe.p2.request.RequestListgetByKeywordReq;

import java.util.List;

public interface RequestListDAO {

    public List<RequestList> RequestListgetByKeyword(RequestListgetByKeywordReq req);

    public List<RequestListConAdv> RequestListgetByConAdv(RequestListgetByConAdvReq req);

}
