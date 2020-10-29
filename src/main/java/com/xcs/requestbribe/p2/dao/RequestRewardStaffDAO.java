package com.xcs.requestbribe.p2.dao;

import com.xcs.requestbribe.p2.model.RequestList;
import com.xcs.requestbribe.p2.request.*;
import com.xcs.requestbribe.p2.response.RequestRewardStaffinsAllRes;
import com.xcs.requestbribe.p2.response.RequestRewardStaffupdByConRes;
import com.xcs.requestbribe.p2.response.RequestRewardStaffupdDeleteRes;

import java.util.List;

public interface RequestRewardStaffDAO {

    public RequestRewardStaffinsAllRes RequestRewardStaffinsAll(RequestRewardStaffinsAllReq req);

    public RequestRewardStaffupdByConRes RequestRewardStaffupdByCon(RequestRewardStaffupdByConReq req);

    public RequestRewardStaffupdDeleteRes RequestRewardStaffupdDelete(RequestRewardStaffupdDeleteReq req);

}
