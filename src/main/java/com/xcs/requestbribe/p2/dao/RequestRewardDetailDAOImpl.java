package com.xcs.requestbribe.p2.dao;

import com.xcs.requestbribe.p2.constant.Message;
import com.xcs.requestbribe.p2.model.RequestList;
import com.xcs.requestbribe.p2.request.*;
import com.xcs.requestbribe.p2.response.RequestBribeinsAllRes;
import com.xcs.requestbribe.p2.response.RequestBribeupdDeleteRes;
import com.xcs.requestbribe.p2.response.RequestRewardDetailinsAllRes;
import com.xcs.requestbribe.p2.response.RequestRewardDetailupdDeleteRes;
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
public class RequestRewardDetailDAOImpl extends RequestBribeExt implements RequestRewardDetailDAO {

    private static final Logger log = LoggerFactory.getLogger(RequestRewardDetailDAOImpl.class);

    @Override
    public RequestRewardDetailinsAllRes RequestRewardDetailinsAll(RequestRewardDetailinsAllReq req) {

        RequestRewardDetailinsAllRes res = new RequestRewardDetailinsAllRes();

        try {

            req.setREWARD_DETAIL_ID(getSequences(" SELECT OPS_REQUEST_REWARD_DETAIL_SEQ.NEXTVAL FROM DUAL "));

            StringBuilder sqlBuilder = new StringBuilder()
                    .append(" INSERT INTO OPS_REQUEST_REWARD_DETAIL (REWARD_DETAIL_ID, REWARD_ID, PAYMENT_ID, IS_ACTIVE) VALUES ('" + req.getREWARD_DETAIL_ID() + "', '" + req.getREWARD_ID() + "', '" + req.getPAYMENT_ID() + "', '" + req.getIS_ACTIVE() + "') ");

            log.info("[SQL] : "+sqlBuilder.toString().replaceAll("'null'", "null"));

            getJdbcTemplate().update(sqlBuilder.toString().replaceAll("'null'", "null"), new Object[] {});

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);
            res.setREWARD_DETAIL_ID(req.getREWARD_DETAIL_ID());

            return res;

        } catch (Exception e) {

            e.printStackTrace();

            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setREWARD_DETAIL_ID("0");

            return res;

        }

    }

    @Override
    public RequestRewardDetailupdDeleteRes RequestRewardDetailupdDelete(RequestRewardDetailupdDeleteReq req) {

        RequestRewardDetailupdDeleteRes res = new RequestRewardDetailupdDeleteRes();

        try {

            StringBuilder sqlBuilder = new StringBuilder()
                    .append(" UPDATE OPS_REQUEST_REWARD_DETAIL SET IS_ACTIVE = '0' WHERE REWARD_DETAIL_ID = '" + req.getREWARD_DETAIL_ID() + "' ");

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