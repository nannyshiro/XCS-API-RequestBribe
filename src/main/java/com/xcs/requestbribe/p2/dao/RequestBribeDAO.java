package com.xcs.requestbribe.p2.dao;

import com.xcs.requestbribe.p2.model.RequestBribe;
import com.xcs.requestbribe.p2.model.RequestList;
import com.xcs.requestbribe.p2.request.*;
import com.xcs.requestbribe.p2.response.RequestBribeinsAllRes;
import com.xcs.requestbribe.p2.response.RequestBribeupdByConRes;
import com.xcs.requestbribe.p2.response.RequestBribeupdDeleteRes;

import java.util.List;

public interface RequestBribeDAO {

    public RequestBribe RequestBribegetByCon(RequestBribegetByConReq req);

    public RequestBribeinsAllRes RequestBribeinsAll(RequestBribeinsAllReq req);

    public RequestBribeupdByConRes RequestBribeupdByCon(RequestBribeupdByConReq req);

    public RequestBribeupdDeleteRes RequestBribeupdDelete(RequestBribeupdDeleteReq req);

}
