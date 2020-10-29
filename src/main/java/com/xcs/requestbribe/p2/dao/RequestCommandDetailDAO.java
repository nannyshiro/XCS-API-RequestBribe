package com.xcs.requestbribe.p2.dao;

import com.xcs.requestbribe.p2.model.RequestBribe;
import com.xcs.requestbribe.p2.request.*;
import com.xcs.requestbribe.p2.response.*;

public interface RequestCommandDetailDAO {

    public RequestCommandDetailinsAllupdByConRes RequestCommandDetailinsAll(RequestCommandDetailinsAllupdByConReq req);

    public RequestCommandDetailinsAllupdByConRes RequestCommandDetailupdByCon(RequestCommandDetailinsAllupdByConReq req);

    public RequestCommandDetailupdDeleteRes RequestCommandDetailupdDelete(RequestCommandDetailupdDeleteReq req);

}
