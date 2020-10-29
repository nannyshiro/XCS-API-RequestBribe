package com.xcs.requestbribe.p2.dao;

import com.xcs.requestbribe.p2.constant.Message;
import com.xcs.requestbribe.p2.model.*;
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
public class RequestRewardDAOImpl extends RequestBribeExt implements RequestRewardDAO {

    private static final Logger log = LoggerFactory.getLogger(RequestRewardDAOImpl.class);

    @Override
    public RequestReward RequestRewardgetByCon(RequestRewardgetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append(" SELECT DISTINCT OPS_REQUEST_REWARD.REWARD_ID, OPS_REQUEST_REWARD.BRIBE_REWARD_ID, OPS_REQUEST_REWARD.REWARD_OFFICE_ID, OPS_REQUEST_REWARD.REWARD_CODE, OPS_REQUEST_REWARD.REWARD_TYPE, OPS_REQUEST_REWARD.REFFERENCE_CODE, OPS_REQUEST_REWARD.REWARD_OFFICE_CODE, OPS_REQUEST_REWARD.REWARD_OFFICE_NAME, OPS_REQUEST_REWARD.REWARD_DATE, OPS_REQUEST_REWARD.FIRST_PART_TOTAL, OPS_REQUEST_REWARD.FIRST_MONEY_TOTAL, OPS_REQUEST_REWARD.FIRST_MONEY_PER_PART, OPS_REQUEST_REWARD.FIRST_REMAINDER, OPS_REQUEST_REWARD.SECOND_PART_TOTAL, OPS_REQUEST_REWARD.SECOND_MONEY_TOTAL, OPS_REQUEST_REWARD.SECOND_MONEY_PER_PART, OPS_REQUEST_REWARD.SECOND_REMAINDER, OPS_REQUEST_REWARD.REWARD_MONEY, OPS_REQUEST_REWARD.BRIBE_MONEY, OPS_REQUEST_REWARD.AUTHORITY_DESC, OPS_REQUEST_REWARD.AUTHORITY_COMMAND_DESC, OPS_REQUEST_REWARD.APPROVE_PAYMENT_DATE, OPS_REQUEST_REWARD.IS_ACTIVE, OPS_REQUEST_REWARD.IS_PAY FROM OPS_REQUEST_REWARD LEFT JOIN OPS_REQUEST_BRIBE_REWARD ON OPS_REQUEST_REWARD.BRIBE_REWARD_ID = OPS_REQUEST_BRIBE_REWARD.BRIBE_REWARD_ID WHERE OPS_REQUEST_REWARD.IS_ACTIVE = 1 ");

        if (req.getREWARD_ID() != null && !"".equals(req.getREWARD_ID())) {

            sqlBuilder.append(" AND OPS_REQUEST_REWARD.REWARD_ID = " + req.getREWARD_ID() + " ");

        }

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        RequestReward data = getJdbcTemplate().queryForObject(sqlBuilder.toString(), new RowMapper<RequestReward>() {

            public RequestReward mapRow(ResultSet rs, int rowNum) throws SQLException {

                RequestReward item = new RequestReward();

                item.setREWARD_ID(rs.getString("REWARD_ID"));
                item.setBRIBE_REWARD_ID(rs.getString("BRIBE_REWARD_ID"));
                item.setREWARD_OFFICE_ID(rs.getString("REWARD_OFFICE_ID"));
                item.setREWARD_CODE(rs.getString("REWARD_CODE"));
                item.setREWARD_TYPE(rs.getString("REWARD_TYPE"));
                item.setREFFERENCE_CODE(rs.getString("REFFERENCE_CODE"));
                item.setREWARD_OFFICE_CODE(rs.getString("REWARD_OFFICE_CODE"));
                item.setREWARD_OFFICE_NAME(rs.getString("REWARD_OFFICE_NAME"));
                item.setREWARD_DATE(rs.getString("REWARD_DATE"));
                item.setFIRST_PART_TOTAL(rs.getString("FIRST_PART_TOTAL"));
                item.setFIRST_MONEY_TOTAL(rs.getString("FIRST_MONEY_TOTAL"));
                item.setFIRST_MONEY_PER_PART(rs.getString("FIRST_MONEY_PER_PART"));
                item.setFIRST_REMAINDER(rs.getString("FIRST_REMAINDER"));
                item.setSECOND_PART_TOTAL(rs.getString("SECOND_PART_TOTAL"));
                item.setSECOND_MONEY_TOTAL(rs.getString("SECOND_MONEY_TOTAL"));
                item.setSECOND_MONEY_PER_PART(rs.getString("SECOND_MONEY_PER_PART"));
                item.setSECOND_REMAINDER(rs.getString("SECOND_REMAINDER"));
                item.setREWARD_MONEY(rs.getString("REWARD_MONEY"));
                item.setBRIBE_MONEY(rs.getString("BRIBE_MONEY"));
                item.setAUTHORITY_DESC(rs.getString("AUTHORITY_DESC"));
                item.setAUTHORITY_COMMAND_DESC(rs.getString("AUTHORITY_COMMAND_DESC"));
                item.setAPPROVE_PAYMENT_DATE(rs.getString("APPROVE_PAYMENT_DATE"));
                item.setIS_ACTIVE(rs.getString("IS_ACTIVE"));
                item.setIS_PAY(rs.getString("IS_PAY"));

                return item;

            }

        });

        sqlBuilder = new StringBuilder()
                .append(" SELECT DISTINCT OPS_REQUEST_REWARD_DETAIL.* FROM OPS_REQUEST_REWARD LEFT JOIN OPS_REQUEST_REWARD_DETAIL ON OPS_REQUEST_REWARD.REWARD_ID = OPS_REQUEST_REWARD_DETAIL.REWARD_ID ");

        if (req.getREWARD_ID() != null && !"".equals(req.getREWARD_ID())) {

            sqlBuilder.append(" WHERE OPS_REQUEST_REWARD.REWARD_ID = " + req.getREWARD_ID() + " ");

        }

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RequestRewardDetail> requestRewardDetails = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper<RequestRewardDetail>() {

            public RequestRewardDetail mapRow(ResultSet rs, int rowNum) throws SQLException {

                RequestRewardDetail item = new RequestRewardDetail();

                item.setREWARD_DETAIL_ID(rs.getString("REWARD_DETAIL_ID"));
                item.setREWARD_ID(rs.getString("REWARD_ID"));
                item.setPAYMENT_ID(rs.getString("PAYMENT_ID"));
                item.setIS_ACTIVE(rs.getString("IS_ACTIVE"));

                return item;

            }

        });

        data.getRequestRewardDetail().addAll(requestRewardDetails);

        sqlBuilder = new StringBuilder()
                .append(" SELECT DISTINCT OPS_REQUEST_REWARD_STAFF.* FROM OPS_REQUEST_REWARD LEFT JOIN OPS_REQUEST_BRIBE_REWARD ON OPS_REQUEST_REWARD.BRIBE_REWARD_ID = OPS_REQUEST_BRIBE_REWARD.BRIBE_REWARD_ID LEFT JOIN OPS_REQUEST_REWARD_STAFF ON OPS_REQUEST_REWARD.REWARD_ID = OPS_REQUEST_REWARD_STAFF.REWARD_ID ");

        if (req.getREWARD_ID() != null && !"".equals(req.getREWARD_ID())) {

            sqlBuilder.append(" WHERE OPS_REQUEST_REWARD.REWARD_ID = " + req.getREWARD_ID() + " ");

        }

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RequestRewardStaff> requestRewardStaffs = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper<RequestRewardStaff>() {

            public RequestRewardStaff mapRow(ResultSet rs, int rowNum) throws SQLException {

                RequestRewardStaff item = new RequestRewardStaff();

                item.setSTAFF_ID(rs.getString("STAFF_ID"));
                item.setREWARD_ID(rs.getString("REWARD_ID"));
                item.setSTAFF_REF_ID(rs.getString("STAFF_REF_ID"));
                item.setTITLE_ID(rs.getString("TITLE_ID"));
                item.setSTAFF_CODE(rs.getString("STAFF_CODE"));
                item.setID_CARD(rs.getString("ID_CARD"));
                item.setSTAFF_TYPE(rs.getString("STAFF_TYPE"));
                item.setTITLE_NAME_TH(rs.getString("TITLE_NAME_TH"));
                item.setTITLE_NAME_EN(rs.getString("TITLE_NAME_EN"));
                item.setTITLE_SHORT_NAME_TH(rs.getString("TITLE_SHORT_NAME_TH"));
                item.setTITLE_SHORT_NAME_EN(rs.getString("TITLE_SHORT_NAME_EN"));
                item.setFIRST_NAME(rs.getString("FIRST_NAME"));
                item.setLAST_NAME(rs.getString("LAST_NAME"));
                item.setAGE(rs.getString("AGE"));
                item.setOPERATION_POS_CODE(rs.getString("OPERATION_POS_CODE"));
                item.setOPREATION_POS_NAME(rs.getString("OPREATION_POS_NAME"));
                item.setOPREATION_POS_LEVEL(rs.getString("OPREATION_POS_LEVEL"));
                item.setOPERATION_POS_LEVEL_NAME(rs.getString("OPERATION_POS_LEVEL_NAME"));
                item.setOPERATION_DEPT_CODE(rs.getString("OPERATION_DEPT_CODE"));
                item.setOPERATION_DEPT_NAME(rs.getString("OPERATION_DEPT_NAME"));
                item.setOPERATION_DEPT_LEVEL(rs.getString("OPERATION_DEPT_LEVEL"));
                item.setOPERATION_UNDER_DEPT_CODE(rs.getString("OPERATION_UNDER_DEPT_CODE"));
                item.setOPERATION_UNDER_DEPT_NAME(rs.getString("OPERATION_UNDER_DEPT_NAME"));
                item.setOPERATION_UNDER_DEPT_LEVEL(rs.getString("OPERATION_UNDER_DEPT_LEVEL"));
                item.setOPERATION_WORK_DEPT_CODE(rs.getString("OPERATION_WORK_DEPT_CODE"));
                item.setOPERATION_WORK_DEPT_NAME(rs.getString("OPERATION_WORK_DEPT_NAME"));
                item.setOPERATION_WORK_DEPT_LEVEL(rs.getString("OPERATION_WORK_DEPT_LEVEL"));
                item.setOPERATION_OFFICE_CODE(rs.getString("OPERATION_OFFICE_CODE"));
                item.setOPERATION_OFFICE_NAME(rs.getString("OPERATION_OFFICE_NAME"));
                item.setOPERATION_OFFICE_SHORT_NAME(rs.getString("OPERATION_OFFICE_SHORT_NAME"));
                item.setMANAGEMENT_POS_CODE(rs.getString("MANAGEMENT_POS_CODE"));
                item.setMANAGEMENT_POS_NAME(rs.getString("MANAGEMENT_POS_NAME"));
                item.setMANAGEMENT_POS_LEVEL(rs.getString("MANAGEMENT_POS_LEVEL"));
                item.setMANAGEMENT_POS_LEVEL_NAME(rs.getString("MANAGEMENT_POS_LEVEL_NAME"));
                item.setMANAGEMENT_DEPT_CODE(rs.getString("MANAGEMENT_DEPT_CODE"));
                item.setMANAGEMENT_DEPT_NAME(rs.getString("MANAGEMENT_DEPT_NAME"));
                item.setMANAGEMENT_DEPT_LEVEL(rs.getString("MANAGEMENT_DEPT_LEVEL"));
                item.setMANAGEMENT_UNDER_DEPT_CODE(rs.getString("MANAGEMENT_UNDER_DEPT_CODE"));
                item.setMANAGEMENT_UNDER_DEPT_NAME(rs.getString("MANAGEMENT_UNDER_DEPT_NAME"));
                item.setMANAGEMENT_UNDER_DEPT_LEVEL(rs.getString("MANAGEMENT_UNDER_DEPT_LEVEL"));
                item.setMANAGEMENT_WORK_DEPT_CODE(rs.getString("MANAGEMENT_WORK_DEPT_CODE"));
                item.setMANAGEMENT_WORK_DEPT_NAME(rs.getString("MANAGEMENT_WORK_DEPT_NAME"));
                item.setMANAGEMENT_WORK_DEPT_LEVEL(rs.getString("MANAGEMENT_WORK_DEPT_LEVEL"));
                item.setMANAGEMENT_OFFICE_CODE(rs.getString("MANAGEMENT_OFFICE_CODE"));
                item.setMANAGEMENT_OFFICE_NAME(rs.getString("MANAGEMENT_OFFICE_NAME"));
                item.setMANAGEMENT_OFFICE_SHORT_NAME(rs.getString("MANAGEMENT_OFFICE_SHORT_NAME"));
                item.setREPRESENT_POS_CODE(rs.getString("REPRESENT_POS_CODE"));
                item.setREPRESENT_POS_NAME(rs.getString("REPRESENT_POS_NAME"));
                item.setREPRESENT_POS_LEVEL(rs.getString("REPRESENT_POS_LEVEL"));
                item.setREPRESENT_POS_LEVEL_NAME(rs.getString("REPRESENT_POS_LEVEL_NAME"));
                item.setREPRESENT_DEPT_CODE(rs.getString("REPRESENT_DEPT_CODE"));
                item.setREPRESENT_DEPT_NAME(rs.getString("REPRESENT_DEPT_NAME"));
                item.setREPRESENT_DEPT_LEVEL(rs.getString("REPRESENT_DEPT_LEVEL"));
                item.setREPRESENT_UNDER_DEPT_CODE(rs.getString("REPRESENT_UNDER_DEPT_CODE"));
                item.setREPRESENT_UNDER_DEPT_NAME(rs.getString("REPRESENT_UNDER_DEPT_NAME"));
                item.setREPRESENT_UNDER_DEPT_LEVEL(rs.getString("REPRESENT_UNDER_DEPT_LEVEL"));
                item.setREPRESENT_WORK_DEPT_CODE(rs.getString("REPRESENT_WORK_DEPT_CODE"));
                item.setREPRESENT_WORK_DEPT_NAME(rs.getString("REPRESENT_WORK_DEPT_NAME"));
                item.setREPRESENT_WORK_DEPT_LEVEL(rs.getString("REPRESENT_WORK_DEPT_LEVEL"));
                item.setREPRESENT_OFFICE_CODE(rs.getString("REPRESENT_OFFICE_CODE"));
                item.setREPRESENT_OFFICE_NAME(rs.getString("REPRESENT_OFFICE_NAME"));
                item.setREPRESENT_OFFICE_SHORT_NAME(rs.getString("REPRESENT_OFFICE_SHORT_NAME"));
                item.setSTATUS(rs.getString("STATUS"));
                item.setREMARK(rs.getString("REMARK"));
                item.setFIRST_PART(rs.getString("FIRST_PART"));
                item.setFIRST_MONEY(rs.getString("FIRST_MONEY"));
                item.setSECOND_PART(rs.getString("SECOND_PART"));
                item.setSECOND_MONEY(rs.getString("SECOND_MONEY"));
                item.setTOTAL_MONEY(rs.getString("TOTAL_MONEY"));
                item.setCONTRIBUTOR_ID(rs.getString("CONTRIBUTOR_ID"));
                item.setIS_ACTIVE(rs.getString("IS_ACTIVE"));
                item.setSEQ(rs.getString("SEQ"));

                return item;

            }

        });

        data.getRequestRewardStaff().addAll(requestRewardStaffs);

        return data;

    }

    @Override
    public RequestRewardinsAllRes RequestRewardinsAll(RequestRewardinsAllReq req) {

        RequestRewardinsAllRes res = new RequestRewardinsAllRes();

        try {

            req.setREWARD_ID(getSequences(" SELECT OPS_REQUEST_REWARD_SEQ.NEXTVAL FROM DUAL "));

            StringBuilder sqlBuilder = new StringBuilder()
                    .append(" INSERT INTO OPS_REQUEST_REWARD (REWARD_ID, BRIBE_REWARD_ID, REWARD_OFFICE_ID, REWARD_CODE, REWARD_TYPE, REFFERENCE_CODE, REWARD_OFFICE_CODE, REWARD_OFFICE_NAME, REWARD_DATE, FIRST_PART_TOTAL, FIRST_MONEY_TOTAL, FIRST_MONEY_PER_PART, FIRST_REMAINDER, SECOND_PART_TOTAL, SECOND_MONEY_TOTAL, SECOND_MONEY_PER_PART, SECOND_REMAINDER, REWARD_MONEY, BRIBE_MONEY, AUTHORITY_DESC, AUTHORITY_COMMAND_DESC, APPROVE_PAYMENT_DATE, IS_PAY, IS_ACTIVE) VALUES ('" + req.getREWARD_ID() + "', '" + req.getBRIBE_REWARD_ID() + "', '" + req.getREWARD_OFFICE_ID() + "', '" + req.getREWARD_CODE() + "', '" + req.getREWARD_TYPE() + "', '" + req.getREFFERENCE_CODE() + "', '" + req.getREWARD_OFFICE_CODE() + "', '" + req.getREWARD_OFFICE_NAME() + "', TO_DATE('" + req.getREWARD_DATE() + "', 'YYYY-MM-DD HH24:MI:SS'), '" + req.getFIRST_PART_TOTAL() + "', '" + req.getFIRST_MONEY_TOTAL() + "', '" + req.getFIRST_MONEY_PER_PART() + "', '" + req.getFIRST_REMAINDER() + "', '" + req.getSECOND_PART_TOTAL() + "', '" + req.getSECOND_MONEY_TOTAL() + "', '" + req.getSECOND_MONEY_PER_PART() + "', '" + req.getSECOND_REMAINDER() + "', '" + req.getREWARD_MONEY() + "', '" + req.getBRIBE_MONEY() + "', '" + req.getAUTHORITY_DESC() + "', '" + req.getAUTHORITY_COMMAND_DESC() + "', TO_DATE('" + req.getAPPROVE_PAYMENT_DATE() + "', 'YYYY-MM-DD HH24:MI:SS'), '" + req.getIS_PAY() + "', '" + req.getIS_ACTIVE() + "') ");

            log.info("[SQL] : "+sqlBuilder.toString().replaceAll("'null'", "null"));

            getJdbcTemplate().update(sqlBuilder.toString().replaceAll("'null'", "null"), new Object[] {});

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);
            res.setREWARD_ID(req.getREWARD_ID());

            return res;

        } catch (Exception e) {

            e.printStackTrace();

            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setREWARD_ID("0");

            return res;

        }

    }

    @Override
    public RequestRewardupdByConRes RequestRewardupdByCon(RequestRewardupdByConReq req) {

        RequestRewardupdByConRes res = new RequestRewardupdByConRes();

        try {

            StringBuilder sqlBuilder = new StringBuilder()
                    .append(" UPDATE OPS_REQUEST_REWARD SET BRIBE_REWARD_ID = '" + req.getBRIBE_REWARD_ID() + "', REWARD_OFFICE_ID = '" + req.getREWARD_OFFICE_ID() + "', REWARD_CODE = '" + req.getREWARD_CODE() + "', REWARD_TYPE = '" + req.getREWARD_TYPE() + "', REFFERENCE_CODE = '" + req.getREFFERENCE_CODE() + "', REWARD_OFFICE_CODE = '" + req.getREWARD_OFFICE_CODE() + "', REWARD_OFFICE_NAME = '" + req.getREWARD_OFFICE_NAME() + "', REWARD_DATE = TO_DATE('" + req.getREWARD_DATE() + "', 'YYYY-MM-DD HH24:MI:SS'), FIRST_PART_TOTAL = '" + req.getFIRST_PART_TOTAL() + "', FIRST_MONEY_TOTAL = '" + req.getFIRST_MONEY_TOTAL() + "', FIRST_MONEY_PER_PART = '" + req.getFIRST_MONEY_PER_PART() + "', FIRST_REMAINDER = '" + req.getFIRST_REMAINDER() + "', SECOND_PART_TOTAL = '" + req.getSECOND_PART_TOTAL() + "', SECOND_MONEY_TOTAL = '" + req.getSECOND_MONEY_TOTAL() + "', SECOND_MONEY_PER_PART = '" + req.getSECOND_MONEY_PER_PART() + "', SECOND_REMAINDER = '" + req.getSECOND_REMAINDER() + "', REWARD_MONEY = '" + req.getREWARD_MONEY() + "', BRIBE_MONEY = '" + req.getBRIBE_MONEY() + "', AUTHORITY_DESC = '" + req.getAUTHORITY_DESC() + "', AUTHORITY_COMMAND_DESC = '" + req.getAUTHORITY_COMMAND_DESC() + "', APPROVE_PAYMENT_DATE = TO_DATE('" + req.getAPPROVE_PAYMENT_DATE() + "', 'YYYY-MM-DD HH24:MI:SS'), IS_PAY = '" + req.getIS_PAY() + "', IS_ACTIVE = '" + req.getIS_ACTIVE() + "' WHERE REWARD_ID = '" + req.getREWARD_ID() + "' ");

            log.info("[SQL] : "+sqlBuilder.toString().replaceAll("'null'", "null"));

            getJdbcTemplate().update(sqlBuilder.toString().replaceAll("'null'", "null"), new Object[] {});

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);
            res.setREWARD_ID(req.getREWARD_ID());

            return res;

        } catch (Exception e) {

            e.printStackTrace();

            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setREWARD_ID("0");

            return res;

        }

    }

    @Override
    public RequestRewardupdDeleteRes RequestRewardupdDelete(RequestRewardupdDeleteReq req) {

        RequestRewardupdDeleteRes res = new RequestRewardupdDeleteRes();

        try {

            StringBuilder sqlBuilder = new StringBuilder()
                    .append(" UPDATE OPS_REQUEST_REWARD SET IS_ACTIVE = '0' WHERE REWARD_ID = '" + req.getREWARD_ID() + "' ");

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