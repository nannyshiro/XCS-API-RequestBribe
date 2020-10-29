package com.xcs.requestbribe.p2.dao;

import com.xcs.requestbribe.p2.constant.Message;
import com.xcs.requestbribe.p2.model.RequestBribe;
import com.xcs.requestbribe.p2.model.RequestBribeDetail;
import com.xcs.requestbribe.p2.model.RequestBribeStaff;
import com.xcs.requestbribe.p2.request.*;
import com.xcs.requestbribe.p2.response.*;
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
public class RequestCommandDetailDAOImpl extends RequestBribeExt implements RequestCommandDetailDAO {

    private static final Logger log = LoggerFactory.getLogger(RequestCommandDetailDAOImpl.class);

    @Override
    public RequestCommandDetailinsAllupdByConRes RequestCommandDetailinsAll(RequestCommandDetailinsAllupdByConReq req) {

        RequestCommandDetailinsAllupdByConRes res = new RequestCommandDetailinsAllupdByConRes();

        try {

            req.setCOMMAND_DETAIL_ID(getSequences(" SELECT OPS_REQUEST_COMMAND_DETAIL_SEQ.NEXTVAL FROM DUAL "));

            StringBuilder sqlBuilder = new StringBuilder()
                    .append(" INSERT INTO OPS_REQUEST_COMMAND_DETAIL (COMMAND_DETAIL_ID, COMMAND_ID, NOTICE_ID, PART, IS_ACTIVE) VALUES ('" + req.getCOMMAND_DETAIL_ID() + "', '" + req.getCOMMAND_ID() + "', '" + req.getNOTICE_ID() + "', '" + req.getPART() + "', '" + req.getIS_ACTIVE() + "') ");

            log.info("[SQL] : "+sqlBuilder.toString().replaceAll("'null'", "null"));

            getJdbcTemplate().update(sqlBuilder.toString().replaceAll("'null'", "null"), new Object[] {});

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);
            res.setCOMMAND_DETAIL_ID(req.getCOMMAND_DETAIL_ID());

            return res;

        } catch (Exception e) {

            e.printStackTrace();

            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setCOMMAND_DETAIL_ID("0");

            return res;

        }

    }

    @Override
    public RequestCommandDetailinsAllupdByConRes RequestCommandDetailupdByCon(RequestCommandDetailinsAllupdByConReq req) {

        RequestCommandDetailinsAllupdByConRes res = new RequestCommandDetailinsAllupdByConRes();

        try {

            StringBuilder sqlBuilder = new StringBuilder()
                    .append(" UPDATE OPS_REQUEST_COMMAND_DETAIL SET COMMAND_ID = '" + req.getCOMMAND_ID() + "', NOTICE_ID = '" + req.getNOTICE_ID() + "', PART = '" + req.getPART() + "', IS_ACTIVE = '" + req.getIS_ACTIVE() + "' WHERE COMMAND_DETAIL_ID = '" + req.getCOMMAND_DETAIL_ID() + "' ");

            log.info("[SQL] : "+sqlBuilder.toString().replaceAll("'null'", "null"));

            getJdbcTemplate().update(sqlBuilder.toString().replaceAll("'null'", "null"), new Object[] {});

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);
            res.setCOMMAND_DETAIL_ID(req.getCOMMAND_DETAIL_ID());

            return res;

        } catch (Exception e) {

            e.printStackTrace();

            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setCOMMAND_DETAIL_ID("0");

            return res;

        }

    }

    @Override
    public RequestCommandDetailupdDeleteRes RequestCommandDetailupdDelete(RequestCommandDetailupdDeleteReq req){

        RequestCommandDetailupdDeleteRes res = new RequestCommandDetailupdDeleteRes();

        try {

            StringBuilder sqlBuilder = new StringBuilder()
                    .append(" UPDATE OPS_REQUEST_COMMAND_DETAIL SET IS_ACTIVE = '0' WHERE COMMAND_DETAIL_ID = '" + req.getCOMMAND_DETAIL_ID() + "' ");

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