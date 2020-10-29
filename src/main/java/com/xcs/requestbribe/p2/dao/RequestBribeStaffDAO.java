package com.xcs.requestbribe.p2.dao;

import com.xcs.requestbribe.p2.request.RequestBribeStaffinsAllReq;
import com.xcs.requestbribe.p2.request.RequestBribeStaffupdByConReq;
import com.xcs.requestbribe.p2.response.RequestBribeStaffinsAllRes;
import com.xcs.requestbribe.p2.response.RequestBribeStaffupdByConRes;

public interface RequestBribeStaffDAO {

    public RequestBribeStaffinsAllRes RequestBribeStaffinsAll(RequestBribeStaffinsAllReq req);

    public RequestBribeStaffupdByConRes RequestBribeStaffupdByCon(RequestBribeStaffupdByConReq req);

}
