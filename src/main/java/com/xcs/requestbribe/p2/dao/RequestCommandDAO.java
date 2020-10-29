package com.xcs.requestbribe.p2.dao;

import com.xcs.requestbribe.p2.model.RequestCommand;
import com.xcs.requestbribe.p2.request.RequestCommandinsAllupdByConReq;
import com.xcs.requestbribe.p2.request.RequestCommandupdDeleteReq;
import com.xcs.requestbribe.p2.response.RequestCommandinsAllupdByConRes;
import com.xcs.requestbribe.p2.response.RequestCommandupdDeleteRes;

public interface RequestCommandDAO {

    public RequestCommandinsAllupdByConRes RequestCommandinsAll(RequestCommandinsAllupdByConReq req);

    public RequestCommandinsAllupdByConRes RequestCommandupdByCon(RequestCommandinsAllupdByConReq req);

    public RequestCommandupdDeleteRes RequestCommandupdDelete(RequestCommandupdDeleteReq req);

}
