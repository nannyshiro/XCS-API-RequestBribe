package com.xcs.requestbribe.p2.dao;

import com.xcs.requestbribe.p2.model.RequestArrestLawsuitProveCompare;
import com.xcs.requestbribe.p2.model.RequestList;
import com.xcs.requestbribe.p2.request.RequestArrestLawsuitProveComparegetByConReq;

import java.util.List;

public interface RequestArrestLawsuitProveCompareDAO {

    public List<RequestArrestLawsuitProveCompare> RequestArrestLawsuitProveComparegetByCon(RequestArrestLawsuitProveComparegetByConReq req);

}
