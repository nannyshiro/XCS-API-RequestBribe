package com.xcs.requestbribe.p2.dao;

import com.xcs.requestbribe.p2.constant.Message;
import com.xcs.requestbribe.p2.model.RequestBribe;
import com.xcs.requestbribe.p2.model.RequestBribeDetail;
import com.xcs.requestbribe.p2.model.RequestBribeStaff;
import com.xcs.requestbribe.p2.model.RequestCommand;
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
public class RequestCommandDAOImpl extends RequestBribeExt implements RequestCommandDAO {

    private static final Logger log = LoggerFactory.getLogger(RequestCommandDAOImpl.class);

    @Override
    public RequestCommandinsAllupdByConRes RequestCommandinsAll(RequestCommandinsAllupdByConReq req) {

        RequestCommandinsAllupdByConRes res = new RequestCommandinsAllupdByConRes();

        try {

            req.setCOMMAND_ID(getSequences(" SELECT OPS_REQUEST_COMMAND_SEQ.NEXTVAL FROM DUAL "));

            StringBuilder sqlBuilder = new StringBuilder()
                    .append(" INSERT INTO OPS_REQUEST_COMMAND (COMMAND_ID, ARREST_ID, COMMAND_NO, COMMAND_DATE, TOTAL_PART, IS_ACTIVE) VALUES ('" + req.getCOMMAND_ID() + "', '" + req.getARREST_ID() + "', '" + req.getCOMMAND_NO() + "', TO_DATE('" + req.getCOMMAND_DATE() + "', 'YYYY-MM-DD HH24:MI:SS'), '" + req.getTOTAL_PART() + "', '" + req.getIS_ACTIVE() + "') ");

            log.info("[SQL] : "+sqlBuilder.toString().replaceAll("'null'", "null"));

            getJdbcTemplate().update(sqlBuilder.toString().replaceAll("'null'", "null"), new Object[] {});

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);
            res.setCOMMAND_ID(req.getCOMMAND_ID());

            return res;

        } catch (Exception e) {

            e.printStackTrace();

            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setCOMMAND_ID("0");

            return res;

        }

    }

    @Override
    public RequestCommandinsAllupdByConRes RequestCommandupdByCon(RequestCommandinsAllupdByConReq req) {

        RequestCommandinsAllupdByConRes res = new RequestCommandinsAllupdByConRes();

        try {

            StringBuilder sqlBuilder = new StringBuilder()
                    .append(" UPDATE OPS_REQUEST_COMMAND SET ARREST_ID = '" + req.getARREST_ID() + "', COMMAND_NO = '" + req.getCOMMAND_NO() + "', COMMAND_DATE = TO_DATE('" + req.getCOMMAND_DATE() + "', 'YYYY-MM-DD HH24:MI:SS'), TOTAL_PART = '" + req.getTOTAL_PART() + "', IS_ACTIVE = '" + req.getIS_ACTIVE() + "' WHERE COMMAND_ID = '" + req.getCOMMAND_ID() + "' ");

            log.info("[SQL] : "+sqlBuilder.toString().replaceAll("'null'", "null"));

            getJdbcTemplate().update(sqlBuilder.toString().replaceAll("'null'", "null"), new Object[] {});

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);
            res.setCOMMAND_ID(req.getCOMMAND_ID());

            return res;

        } catch (Exception e) {

            e.printStackTrace();

            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setCOMMAND_ID("0");

            return res;

        }

    }

    @Override
    public RequestCommandupdDeleteRes RequestCommandupdDelete(RequestCommandupdDeleteReq req){

        RequestCommandupdDeleteRes res = new RequestCommandupdDeleteRes();

        try {

            StringBuilder sqlBuilder = new StringBuilder()
                    .append(" UPDATE OPS_REQUEST_COMMAND SET IS_ACTIVE = '0' WHERE COMMAND_ID = '" + req.getCOMMAND_ID() + "' ");

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