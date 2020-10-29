package com.xcs.requestbribe.p2.dao;

import com.xcs.requestbribe.p2.model.RequestList;
import com.xcs.requestbribe.p2.model.RequestStaffList;
import com.xcs.requestbribe.p2.request.RequestListgetByConAdvReq;
import com.xcs.requestbribe.p2.request.RequestListgetByKeywordReq;
import com.xcs.requestbribe.p2.request.RequestStaffListgetByConReq;

import java.util.List;

public interface RequestStaffListDAO {

    public RequestStaffList RequestStaffListgetByCon(RequestStaffListgetByConReq req);

}
