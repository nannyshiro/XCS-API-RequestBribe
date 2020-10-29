package com.xcs.requestbribe.p2.dao;

import com.xcs.requestbribe.p2.constant.Message;
import com.xcs.requestbribe.p2.model.RequestList;
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
public class RequestRewardStaffDAOImpl extends RequestBribeExt implements RequestRewardStaffDAO {

    private static final Logger log = LoggerFactory.getLogger(RequestRewardStaffDAOImpl.class);

    @Override
    public RequestRewardStaffinsAllRes RequestRewardStaffinsAll(RequestRewardStaffinsAllReq req) {

        RequestRewardStaffinsAllRes res = new RequestRewardStaffinsAllRes();

        try {

            req.setSTAFF_ID(getSequences(" SELECT OPS_REWARD_STAFF_SEQ.NEXTVAL FROM DUAL "));

            StringBuilder sqlBuilder = new StringBuilder()
                    .append(" INSERT INTO OPS_REQUEST_REWARD_STAFF (STAFF_ID, REWARD_ID, STAFF_REF_ID, TITLE_ID, STAFF_CODE, ID_CARD, STAFF_TYPE, TITLE_NAME_TH, TITLE_NAME_EN, TITLE_SHORT_NAME_TH, TITLE_SHORT_NAME_EN, FIRST_NAME, LAST_NAME, AGE, OPERATION_POS_CODE, OPREATION_POS_NAME, OPREATION_POS_LEVEL, OPERATION_POS_LEVEL_NAME, OPERATION_DEPT_CODE, OPERATION_DEPT_NAME, OPERATION_DEPT_LEVEL, OPERATION_UNDER_DEPT_CODE, OPERATION_UNDER_DEPT_NAME, OPERATION_UNDER_DEPT_LEVEL, OPERATION_WORK_DEPT_CODE, OPERATION_WORK_DEPT_NAME, OPERATION_WORK_DEPT_LEVEL, OPERATION_OFFICE_CODE, OPERATION_OFFICE_NAME, OPERATION_OFFICE_SHORT_NAME, MANAGEMENT_POS_CODE, MANAGEMENT_POS_NAME, MANAGEMENT_POS_LEVEL, MANAGEMENT_POS_LEVEL_NAME, MANAGEMENT_DEPT_CODE, MANAGEMENT_DEPT_NAME, MANAGEMENT_DEPT_LEVEL, MANAGEMENT_UNDER_DEPT_CODE, MANAGEMENT_UNDER_DEPT_NAME, MANAGEMENT_UNDER_DEPT_LEVEL, MANAGEMENT_WORK_DEPT_CODE, MANAGEMENT_WORK_DEPT_NAME, MANAGEMENT_WORK_DEPT_LEVEL, MANAGEMENT_OFFICE_CODE, MANAGEMENT_OFFICE_NAME, MANAGEMENT_OFFICE_SHORT_NAME, REPRESENT_POS_CODE, REPRESENT_POS_NAME, REPRESENT_POS_LEVEL, REPRESENT_POS_LEVEL_NAME, REPRESENT_DEPT_CODE, REPRESENT_DEPT_NAME, REPRESENT_DEPT_LEVEL, REPRESENT_UNDER_DEPT_CODE, REPRESENT_UNDER_DEPT_NAME, REPRESENT_UNDER_DEPT_LEVEL, REPRESENT_WORK_DEPT_CODE, REPRESENT_WORK_DEPT_NAME, REPRESENT_WORK_DEPT_LEVEL, REPRESENT_OFFICE_CODE, REPRESENT_OFFICE_NAME, REPRESENT_OFFICE_SHORT_NAME, STATUS, REMARK, FIRST_PART, FIRST_MONEY, SECOND_PART, SECOND_MONEY, TOTAL_MONEY, CONTRIBUTOR_ID, IS_ACTIVE, SEQ) VALUES ('" + req.getSTAFF_ID() + "', '" + req.getREWARD_ID() + "', '" + req.getSTAFF_REF_ID() + "', '" + req.getTITLE_ID() + "', '" + req.getSTAFF_CODE() + "', '" + req.getID_CARD() + "', '" + req.getSTAFF_TYPE() + "', '" + req.getTITLE_NAME_TH() + "', '" + req.getTITLE_NAME_EN() + "', '" + req.getTITLE_SHORT_NAME_TH() + "', '" + req.getTITLE_SHORT_NAME_EN() + "', '" + req.getFIRST_NAME() + "', '" + req.getLAST_NAME() + "', '" + req.getAGE() + "', '" + req.getOPERATION_POS_CODE() + "', '" + req.getOPREATION_POS_NAME() + "', '" + req.getOPREATION_POS_LEVEL() + "', '" + req.getOPERATION_POS_LEVEL_NAME() + "', '" + req.getOPERATION_DEPT_CODE() + "', '" + req.getOPERATION_DEPT_NAME() + "', '" + req.getOPERATION_DEPT_LEVEL() + "', '" + req.getOPERATION_UNDER_DEPT_CODE() + "', '" + req.getOPERATION_UNDER_DEPT_NAME() + "', '" + req.getOPERATION_UNDER_DEPT_LEVEL() + "', '" + req.getOPERATION_WORK_DEPT_CODE() + "', '" + req.getOPERATION_WORK_DEPT_NAME() + "', '" + req.getOPERATION_WORK_DEPT_LEVEL() + "', '" + req.getOPERATION_OFFICE_CODE() + "', '" + req.getOPERATION_OFFICE_NAME() + "', '" + req.getOPERATION_OFFICE_SHORT_NAME() + "', '" + req.getMANAGEMENT_POS_CODE() + "', '" + req.getMANAGEMENT_POS_NAME() + "', '" + req.getMANAGEMENT_POS_LEVEL() + "', '" + req.getMANAGEMENT_POS_LEVEL_NAME() + "', '" + req.getMANAGEMENT_DEPT_CODE() + "', '" + req.getMANAGEMENT_DEPT_NAME() + "', '" + req.getMANAGEMENT_DEPT_LEVEL() + "', '" + req.getMANAGEMENT_UNDER_DEPT_CODE() + "', '" + req.getMANAGEMENT_UNDER_DEPT_NAME() + "', '" + req.getMANAGEMENT_UNDER_DEPT_LEVEL() + "', '" + req.getMANAGEMENT_WORK_DEPT_CODE() + "', '" + req.getMANAGEMENT_WORK_DEPT_NAME() + "', '" + req.getMANAGEMENT_WORK_DEPT_LEVEL() + "', '" + req.getMANAGEMENT_OFFICE_CODE() + "', '" + req.getMANAGEMENT_OFFICE_NAME() + "', '" + req.getMANAGEMENT_OFFICE_SHORT_NAME() + "', '" + req.getREPRESENT_POS_CODE() + "', '" + req.getREPRESENT_POS_NAME() + "', '" + req.getREPRESENT_POS_LEVEL() + "', '" + req.getREPRESENT_POS_LEVEL_NAME() + "', '" + req.getREPRESENT_DEPT_CODE() + "', '" + req.getREPRESENT_DEPT_NAME() + "', '" + req.getREPRESENT_DEPT_LEVEL() + "', '" + req.getREPRESENT_UNDER_DEPT_CODE() + "', '" + req.getREPRESENT_UNDER_DEPT_NAME() + "', '" + req.getREPRESENT_UNDER_DEPT_LEVEL() + "', '" + req.getREPRESENT_WORK_DEPT_CODE() + "', '" + req.getREPRESENT_WORK_DEPT_NAME() + "', '" + req.getREPRESENT_WORK_DEPT_LEVEL() + "', '" + req.getREPRESENT_OFFICE_CODE() + "', '" + req.getREPRESENT_OFFICE_NAME() + "', '" + req.getREPRESENT_OFFICE_SHORT_NAME() + "', '" + req.getSTATUS() + "', '" + req.getREMARK() + "', '" + req.getFIRST_PART() + "', '" + req.getFIRST_MONEY() + "', '" + req.getSECOND_PART() + "', '" + req.getSECOND_MONEY() + "', '" + req.getTOTAL_MONEY() + "', '" + req.getCONTRIBUTOR_ID() + "', '" + req.getIS_ACTIVE() + "', '" + req.getSEQ() + "') ");

            log.info("[SQL] : "+sqlBuilder.toString().replaceAll("'null'", "null"));

            getJdbcTemplate().update(sqlBuilder.toString().replaceAll("'null'", "null"), new Object[] {});

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);
            res.setSTAFF_ID(req.getSTAFF_ID());

            return res;

        } catch (Exception e) {

            e.printStackTrace();

            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setSTAFF_ID("0");

            return res;

        }

    }

    @Override
    public RequestRewardStaffupdByConRes RequestRewardStaffupdByCon(RequestRewardStaffupdByConReq req) {

        RequestRewardStaffupdByConRes res = new RequestRewardStaffupdByConRes();

        try {

            StringBuilder sqlBuilder = new StringBuilder()
                    .append(" UPDATE OPS_REQUEST_REWARD_STAFF SET REWARD_ID = '" + req.getREWARD_ID() + "', STAFF_REF_ID = '" + req.getSTAFF_REF_ID() + "', TITLE_ID = '" + req.getTITLE_ID() + "', STAFF_CODE = '" + req.getSTAFF_CODE() + "', ID_CARD = '" + req.getID_CARD() + "', STAFF_TYPE = '" + req.getSTAFF_TYPE() + "', TITLE_NAME_TH = '" + req.getTITLE_NAME_TH() + "', TITLE_NAME_EN = '" + req.getTITLE_NAME_EN() + "', TITLE_SHORT_NAME_TH = '" + req.getTITLE_SHORT_NAME_TH() + "', TITLE_SHORT_NAME_EN = '" + req.getTITLE_SHORT_NAME_EN() + "', FIRST_NAME = '" + req.getFIRST_NAME() + "', LAST_NAME = '" + req.getLAST_NAME() + "', AGE = '" + req.getAGE() + "', OPERATION_POS_CODE = '" + req.getOPERATION_POS_CODE() + "', OPREATION_POS_NAME = '" + req.getOPREATION_POS_NAME() + "', OPREATION_POS_LEVEL = '" + req.getOPREATION_POS_LEVEL() + "', OPERATION_POS_LEVEL_NAME = '" + req.getOPERATION_POS_LEVEL_NAME() + "', OPERATION_DEPT_CODE = '" + req.getOPERATION_DEPT_CODE() + "', OPERATION_DEPT_NAME = '" + req.getOPERATION_DEPT_NAME() + "', OPERATION_DEPT_LEVEL = '" + req.getOPERATION_DEPT_LEVEL() + "', OPERATION_UNDER_DEPT_CODE = '" + req.getOPERATION_UNDER_DEPT_CODE() + "', OPERATION_UNDER_DEPT_NAME = '" + req.getOPERATION_UNDER_DEPT_NAME() + "', OPERATION_UNDER_DEPT_LEVEL = '" + req.getOPERATION_UNDER_DEPT_LEVEL() + "', OPERATION_WORK_DEPT_CODE = '" + req.getOPERATION_WORK_DEPT_CODE() + "', OPERATION_WORK_DEPT_NAME = '" + req.getOPERATION_WORK_DEPT_NAME() + "', OPERATION_WORK_DEPT_LEVEL = '" + req.getOPERATION_WORK_DEPT_LEVEL() + "', OPERATION_OFFICE_CODE = '" + req.getOPERATION_OFFICE_CODE() + "', OPERATION_OFFICE_NAME = '" + req.getOPERATION_OFFICE_NAME() + "', OPERATION_OFFICE_SHORT_NAME = '" + req.getOPERATION_OFFICE_SHORT_NAME() + "', MANAGEMENT_POS_CODE = '" + req.getMANAGEMENT_POS_CODE() + "', MANAGEMENT_POS_NAME = '" + req.getMANAGEMENT_POS_NAME() + "', MANAGEMENT_POS_LEVEL = '" + req.getMANAGEMENT_POS_LEVEL() + "', MANAGEMENT_POS_LEVEL_NAME = '" + req.getMANAGEMENT_POS_LEVEL_NAME() + "', MANAGEMENT_DEPT_CODE = '" + req.getMANAGEMENT_DEPT_CODE() + "', MANAGEMENT_DEPT_NAME = '" + req.getMANAGEMENT_DEPT_NAME() + "', MANAGEMENT_DEPT_LEVEL = '" + req.getMANAGEMENT_DEPT_LEVEL() + "', MANAGEMENT_UNDER_DEPT_CODE = '" + req.getMANAGEMENT_UNDER_DEPT_CODE() + "', MANAGEMENT_UNDER_DEPT_NAME = '" + req.getMANAGEMENT_UNDER_DEPT_NAME() + "', MANAGEMENT_UNDER_DEPT_LEVEL = '" + req.getMANAGEMENT_UNDER_DEPT_LEVEL() + "', MANAGEMENT_WORK_DEPT_CODE = '" + req.getMANAGEMENT_WORK_DEPT_CODE() + "', MANAGEMENT_WORK_DEPT_NAME = '" + req.getMANAGEMENT_WORK_DEPT_NAME() + "', MANAGEMENT_WORK_DEPT_LEVEL = '" + req.getMANAGEMENT_WORK_DEPT_LEVEL() + "', MANAGEMENT_OFFICE_CODE = '" + req.getMANAGEMENT_OFFICE_CODE() + "', MANAGEMENT_OFFICE_NAME = '" + req.getMANAGEMENT_OFFICE_NAME() + "', MANAGEMENT_OFFICE_SHORT_NAME = '" + req.getMANAGEMENT_OFFICE_SHORT_NAME() + "', REPRESENT_POS_CODE = '" + req.getREPRESENT_POS_CODE() + "', REPRESENT_POS_NAME = '" + req.getREPRESENT_POS_NAME() + "', REPRESENT_POS_LEVEL = '" + req.getREPRESENT_POS_LEVEL() + "', REPRESENT_POS_LEVEL_NAME = '" + req.getREPRESENT_POS_LEVEL_NAME() + "', REPRESENT_DEPT_CODE = '" + req.getREPRESENT_DEPT_CODE() + "', REPRESENT_DEPT_NAME = '" + req.getREPRESENT_DEPT_NAME() + "', REPRESENT_DEPT_LEVEL = '" + req.getREPRESENT_DEPT_LEVEL() + "', REPRESENT_UNDER_DEPT_CODE = '" + req.getREPRESENT_UNDER_DEPT_CODE() + "', REPRESENT_UNDER_DEPT_NAME = '" + req.getREPRESENT_UNDER_DEPT_NAME() + "', REPRESENT_UNDER_DEPT_LEVEL = '" + req.getREPRESENT_UNDER_DEPT_LEVEL() + "', REPRESENT_WORK_DEPT_CODE = '" + req.getREPRESENT_WORK_DEPT_CODE() + "', REPRESENT_WORK_DEPT_NAME = '" + req.getREPRESENT_WORK_DEPT_NAME() + "', REPRESENT_WORK_DEPT_LEVEL = '" + req.getREPRESENT_WORK_DEPT_LEVEL() + "', REPRESENT_OFFICE_CODE = '" + req.getREPRESENT_OFFICE_CODE() + "', REPRESENT_OFFICE_NAME = '" + req.getREPRESENT_OFFICE_NAME() + "', REPRESENT_OFFICE_SHORT_NAME = '" + req.getREPRESENT_OFFICE_SHORT_NAME() + "', STATUS = '" + req.getSTATUS() + "', REMARK = '" + req.getREMARK() + "', FIRST_PART = '" + req.getFIRST_PART() + "', FIRST_MONEY = '" + req.getFIRST_MONEY() + "', SECOND_PART = '" + req.getSECOND_PART() + "', SECOND_MONEY = '" + req.getSECOND_MONEY() + "', TOTAL_MONEY = '" + req.getTOTAL_MONEY() + "', CONTRIBUTOR_ID = '" + req.getCONTRIBUTOR_ID() + "', IS_ACTIVE = '" + req.getIS_ACTIVE() + "', SEQ = '" + req.getSEQ() + "' WHERE STAFF_ID = '" + req.getSTAFF_ID() + "' ");

            log.info("[SQL] : "+sqlBuilder.toString().replaceAll("'null'", "null"));

            getJdbcTemplate().update(sqlBuilder.toString().replaceAll("'null'", "null"), new Object[] {});

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);
            res.setSTAFF_ID(req.getSTAFF_ID());

            return res;

        } catch (Exception e) {

            e.printStackTrace();

            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setSTAFF_ID("0");

            return res;

        }

    }

    @Override
    public RequestRewardStaffupdDeleteRes RequestRewardStaffupdDelete(RequestRewardStaffupdDeleteReq req) {

        RequestRewardStaffupdDeleteRes res = new RequestRewardStaffupdDeleteRes();

        try {

            StringBuilder sqlBuilder = new StringBuilder()
                    .append(" UPDATE OPS_REQUEST_REWARD_STAFF SET IS_ACTIVE = '0' WHERE STAFF_ID = '" + req.getSTAFF_ID() + "' ");

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