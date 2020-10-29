package com.xcs.requestbribe.p2.dao;

import com.xcs.requestbribe.p2.constant.Message;
import com.xcs.requestbribe.p2.model.RequestList;
import com.xcs.requestbribe.p2.request.*;
import com.xcs.requestbribe.p2.response.RequestBribeDetailinsAllRes;
import com.xcs.requestbribe.p2.response.RequestBribeDetailupdDeleteRes;
import com.xcs.requestbribe.p2.response.RequestBribeinsAllRes;
import com.xcs.requestbribe.p2.response.RequestBribeupdDeleteRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class RequestBribeDetailDAOImpl extends RequestBribeExt implements RequestBribeDetailDAO {

    private static final Logger log = LoggerFactory.getLogger(RequestBribeDetailDAOImpl.class);

    @Override
    public RequestBribeDetailinsAllRes RequestBribeDetailinsAll(RequestBribeDetailinsAllReq req) {

        RequestBribeDetailinsAllRes res = new RequestBribeDetailinsAllRes();

        try {

            req.setBRIBE_DETAIL_ID(getSequences(" SELECT OPS_REQUEST_BRIBE_DETAIL_SEQ.NEXTVAL FROM DUAL "));

            StringBuilder sqlBuilder = new StringBuilder()
                    .append(" INSERT INTO OPS_REQUEST_BRIBE_DETAIL (BRIBE_DETAIL_ID, BRIBE_ID, PAYMENT_DETAIL_ID, IS_ACTIVE) VALUES ('" + req.getBRIBE_DETAIL_ID() + "', '" + req.getBRIBE_ID() + "', '" + req.getPAYMENT_DETAIL_ID() + "', '" + req.getIS_ACTIVE() + "') ");

            log.info("[SQL] : "+sqlBuilder.toString().replaceAll("'null'", "null"));

            getJdbcTemplate().update(sqlBuilder.toString().replaceAll("'null'", "null"), new Object[] {});

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);
            res.setBRIBE_DETAIL_ID(req.getBRIBE_DETAIL_ID());

            return res;

        } catch (Exception e) {

            e.printStackTrace();

            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setBRIBE_DETAIL_ID("0");

            return res;

        }

    }

    @Override
    public RequestBribeDetailupdDeleteRes RequestBribeDetailupdDelete(RequestBribeDetailupdDeleteReq req) {

        RequestBribeDetailupdDeleteRes res = new RequestBribeDetailupdDeleteRes();

        try {

            StringBuilder sqlBuilder = new StringBuilder()
                    .append(" UPDATE OPS_REQUEST_BRIBE_DETAIL SET IS_ACTIVE = '0' WHERE BRIBE_DETAIL_ID = '" + req.getBRIBE_DETAIL_ID() + "' ");

            log.info("[SQL] : "+sqlBuilder.toString());

            getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {

            e.printStackTrace();

            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());

            return res;

        }

    }

}