package com.xcs.requestbribe.p2.dao;

import com.xcs.requestbribe.p2.constant.Message;
import com.xcs.requestbribe.p2.model.RequestBribe;
import com.xcs.requestbribe.p2.model.RequestBribeDetail;
import com.xcs.requestbribe.p2.model.RequestBribeStaff;
import com.xcs.requestbribe.p2.request.RequestBribegetByConReq;
import com.xcs.requestbribe.p2.request.RequestBribeinsAllReq;
import com.xcs.requestbribe.p2.request.RequestBribeupdByConReq;
import com.xcs.requestbribe.p2.request.RequestBribeupdDeleteReq;
import com.xcs.requestbribe.p2.response.RequestBribeinsAllRes;
import com.xcs.requestbribe.p2.response.RequestBribeupdByConRes;
import com.xcs.requestbribe.p2.response.RequestBribeupdDeleteRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class RequestBribeDAOImpl extends RequestBribeExt implements RequestBribeDAO {

    private static final Logger log = LoggerFactory.getLogger(RequestBribeDAOImpl.class);

    @Override
    public RequestBribe RequestBribegetByCon(RequestBribegetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append(" SELECT DISTINCT OPS_REQUEST_BRIBE.BRIBE_ID, OPS_REQUEST_BRIBE.BRIBE_REWARD_ID, OPS_REQUEST_BRIBE.COMMAND_DETAIL_ID, OPS_REQUEST_BRIBE.BRIBE_OFFICE_ID, OPS_REQUEST_BRIBE.BRIBE_CODE, OPS_REQUEST_BRIBE.BRIBE_DATE, OPS_REQUEST_BRIBE.BRIBE_OFFICE_CODE, OPS_REQUEST_BRIBE.BRIBE_OFFICE_NAME, OPS_REQUEST_BRIBE.INFORMER_INFO, OPS_REQUEST_BRIBE.BRIBE_MONEY, OPS_REQUEST_BRIBE.AUTHORITY_DESC, OPS_REQUEST_BRIBE.APPROVE_PAYMENT_DATE, OPS_REQUEST_BRIBE.IS_ACTIVE, OPS_REQUEST_BRIBE.IS_PAY, OPS_NOTICE.NOTICE_CODE, OPS_NOTICE.NOTICE_DATE, OPS_NOTICE_STAFF.TITLE_NAME_TH||''||OPS_NOTICE_STAFF.FIRST_NAME||''||OPS_NOTICE_STAFF.LAST_NAME AS NOTICE_STAFF, OPS_NOTICE_STAFF.OPERATION_DEPT_CODE FROM OPS_REQUEST_BRIBE_REWARD LEFT JOIN OPS_ARREST_INDICTMENT ON OPS_REQUEST_BRIBE_REWARD.INDICTMENT_ID = OPS_ARREST_INDICTMENT.INDICTMENT_ID LEFT JOIN OPS_ARREST ON OPS_ARREST_INDICTMENT.ARREST_ID = OPS_ARREST.ARREST_ID LEFT JOIN OPS_NOTICE ON OPS_ARREST.ARREST_ID = OPS_NOTICE.ARREST_ID AND OPS_NOTICE.IS_ACTIVE = 1 LEFT JOIN OPS_NOTICE_STAFF ON OPS_NOTICE.NOTICE_ID = OPS_NOTICE_STAFF.NOTICE_ID AND CONTRIBUTOR_ID IN (7,8) LEFT JOIN OPS_REQUEST_BRIBE ON OPS_REQUEST_BRIBE_REWARD.BRIBE_REWARD_ID = OPS_REQUEST_BRIBE.BRIBE_REWARD_ID WHERE OPS_REQUEST_BRIBE.IS_ACTIVE = 1 ");

        if (req.getBRIBE_ID() != null && !"".equals(req.getBRIBE_ID())) {

            sqlBuilder.append(" AND OPS_REQUEST_BRIBE.BRIBE_ID = " + req.getBRIBE_ID() + " ");

        }

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        RequestBribe data = getJdbcTemplate().queryForObject(sqlBuilder.toString(), new RowMapper<RequestBribe>() {

            public RequestBribe mapRow(ResultSet rs, int rowNum) throws SQLException {

                RequestBribe item = new RequestBribe();

                item.setBRIBE_ID(rs.getString("BRIBE_ID"));
                item.setBRIBE_REWARD_ID(rs.getString("BRIBE_REWARD_ID"));
                item.setCOMMAND_DETAIL_ID(rs.getString("COMMAND_DETAIL_ID"));
                item.setBRIBE_OFFICE_ID(rs.getString("BRIBE_OFFICE_ID"));
                item.setBRIBE_CODE(rs.getString("BRIBE_CODE"));
                item.setBRIBE_DATE(rs.getString("BRIBE_DATE"));
                item.setBRIBE_OFFICE_CODE(rs.getString("BRIBE_OFFICE_CODE"));
                item.setBRIBE_OFFICE_NAME(rs.getString("BRIBE_OFFICE_NAME"));
                item.setINFORMER_INFO(rs.getString("INFORMER_INFO"));
                item.setBRIBE_MONEY(rs.getString("BRIBE_MONEY"));
                item.setAUTHORITY_DESC(rs.getString("AUTHORITY_DESC"));
                item.setAPPROVE_PAYMENT_DATE(rs.getString("APPROVE_PAYMENT_DATE"));
                item.setIS_ACTIVE(rs.getString("IS_ACTIVE"));
                item.setIS_PAY(rs.getString("IS_PAY"));
                item.setNOTICE_CODE(rs.getString("NOTICE_CODE"));
                item.setNOTICE_DATE(rs.getString("NOTICE_DATE"));
                item.setNOTICE_STAFF(rs.getString("NOTICE_STAFF"));
                item.setOPERATION_DEPT_CODE(rs.getString("OPERATION_DEPT_CODE"));

                return item;

            }

        });

        sqlBuilder = new StringBuilder()
                .append(" SELECT DISTINCT OPS_REQUEST_BRIBE_DETAIL.* FROM OPS_REQUEST_BRIBE_REWARD LEFT JOIN OPS_REQUEST_BRIBE ON OPS_REQUEST_BRIBE_REWARD.BRIBE_REWARD_ID = OPS_REQUEST_BRIBE.BRIBE_REWARD_ID LEFT JOIN OPS_REQUEST_BRIBE_DETAIL ON OPS_REQUEST_BRIBE.BRIBE_ID = OPS_REQUEST_BRIBE_DETAIL.BRIBE_ID ");

        if (req.getBRIBE_ID() != null && !"".equals(req.getBRIBE_ID())) {

            sqlBuilder.append(" WHERE OPS_REQUEST_BRIBE.BRIBE_ID = " + req.getBRIBE_ID() + " ");

        }

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RequestBribeDetail> requestBribeDetails = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper<RequestBribeDetail>() {

            public RequestBribeDetail mapRow(ResultSet rs, int rowNum) throws SQLException {

                RequestBribeDetail item = new RequestBribeDetail();

                item.setBRIBE_DETAIL_ID(rs.getString("BRIBE_DETAIL_ID"));
                item.setBRIBE_ID(rs.getString("BRIBE_ID"));
                item.setPAYMENT_DETAIL_ID(rs.getString("PAYMENT_DETAIL_ID"));
                item.setIS_ACTIVE(rs.getString("IS_ACTIVE"));

                return item;

            }

        });

        data.getRequestBribeDetail().addAll(requestBribeDetails);

        sqlBuilder = new StringBuilder()
                .append(" SELECT DISTINCT OPS_REQUEST_BRIBE_STAFF.* FROM OPS_REQUEST_BRIBE_REWARD LEFT JOIN OPS_REQUEST_BRIBE ON OPS_REQUEST_BRIBE_REWARD.BRIBE_REWARD_ID = OPS_REQUEST_BRIBE.BRIBE_REWARD_ID LEFT JOIN OPS_REQUEST_BRIBE_STAFF ON OPS_REQUEST_BRIBE.BRIBE_ID = OPS_REQUEST_BRIBE_STAFF.BRIBE_ID ");

        if (req.getBRIBE_ID() != null && !"".equals(req.getBRIBE_ID())) {

            sqlBuilder.append(" WHERE OPS_REQUEST_BRIBE.BRIBE_ID = " + req.getBRIBE_ID() + " ");

        }

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RequestBribeStaff> requestBribeStaffs = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper<RequestBribeStaff>() {

            public RequestBribeStaff mapRow(ResultSet rs, int rowNum) throws SQLException {

                RequestBribeStaff item = new RequestBribeStaff();

                item.setSTAFF_ID(rs.getString("STAFF_ID"));
                item.setBRIBE_ID(rs.getString("BRIBE_ID"));
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
                item.setCONTRIBUTOR_ID(rs.getString("CONTRIBUTOR_ID"));
                item.setIS_ACTIVE(rs.getString("IS_ACTIVE"));

                return item;

            }

        });

        data.getRequestBribeStaff().addAll(requestBribeStaffs);

        return data;

    }

    @Override
    public RequestBribeinsAllRes RequestBribeinsAll(RequestBribeinsAllReq req) {

        RequestBribeinsAllRes res = new RequestBribeinsAllRes();

        try {

            req.setBRIBE_ID(getSequences(" SELECT OPS_REQUEST_BRIBE_SEQ.NEXTVAL FROM DUAL "));

            StringBuilder sqlBuilder = new StringBuilder()
                    .append(" INSERT INTO OPS_REQUEST_BRIBE (BRIBE_ID, BRIBE_REWARD_ID, BRIBE_OFFICE_ID, BRIBE_CODE, BRIBE_DATE, BRIBE_OFFICE_CODE, BRIBE_OFFICE_NAME, INFORMER_INFO, BRIBE_MONEY, BRIBE_REMAINDER, AUTHORITY_DESC, APPROVE_PAYMENT_DATE, IS_PAY, IS_ACTIVE, COMMAND_DETAIL_ID) VALUES ('" + req.getBRIBE_ID() + "', '" + req.getBRIBE_REWARD_ID() + "', '" + req.getBRIBE_OFFICE_ID() + "', '" + req.getBRIBE_CODE() + "', TO_DATE('" + req.getBRIBE_DATE() + "', 'YYYY-MM-DD HH24:MI:SS'), '" + req.getBRIBE_OFFICE_CODE() + "', '" + req.getBRIBE_OFFICE_NAME() + "', '" + req.getINFORMER_INFO() + "', '" + req.getBRIBE_MONEY() + "', '" + req.getBRIBE_REMAINDER() + "', '" + req.getAUTHORITY_DESC() + "', TO_DATE('" + req.getAPPROVE_PAYMENT_DATE() + "', 'YYYY-MM-DD HH24:MI:SS'), '" + req.getIS_PAY() + "', '" + req.getIS_ACTIVE() + "', '" + req.getCOMMAND_DETAIL_ID() + "') ");

            log.info("[SQL] : "+sqlBuilder.toString().replaceAll("'null'", "null"));

            getJdbcTemplate().update(sqlBuilder.toString().replaceAll("'null'", "null"), new Object[] {});

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);
            res.setBRIBE_ID(req.getBRIBE_ID());

            return res;

        } catch (Exception e) {

            e.printStackTrace();

            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setBRIBE_ID("0");

            return res;

        }

    }

    @Override
    public RequestBribeupdByConRes RequestBribeupdByCon(RequestBribeupdByConReq req) {

        RequestBribeupdByConRes res = new RequestBribeupdByConRes();

        try {

            StringBuilder sqlBuilder = new StringBuilder()
                    .append(" UPDATE OPS_REQUEST_BRIBE SET BRIBE_REWARD_ID = '" + req.getBRIBE_REWARD_ID() + "', BRIBE_OFFICE_ID = '" + req.getBRIBE_OFFICE_ID() + "', BRIBE_CODE = '" + req.getBRIBE_CODE() + "', BRIBE_DATE = TO_DATE('" + req.getBRIBE_DATE() + "', 'YYYY-MM-DD HH24:MI:SS'), BRIBE_OFFICE_CODE = '" + req.getBRIBE_OFFICE_CODE() + "', BRIBE_OFFICE_NAME = '" + req.getBRIBE_OFFICE_NAME() + "', INFORMER_INFO = '" + req.getINFORMER_INFO() + "', BRIBE_MONEY = '" + req.getBRIBE_MONEY() + "', BRIBE_REMAINDER = '" + req.getBRIBE_REMAINDER() + "', AUTHORITY_DESC = '" + req.getAUTHORITY_DESC() + "', APPROVE_PAYMENT_DATE = TO_DATE('" + req.getAPPROVE_PAYMENT_DATE() + "', 'YYYY-MM-DD HH24:MI:SS'), IS_PAY = '" + req.getIS_PAY() + "', IS_ACTIVE = '" + req.getIS_ACTIVE() + "', COMMAND_DETAIL_ID = '" + req.getCOMMAND_DETAIL_ID() + "' WHERE BRIBE_ID = '" + req.getBRIBE_ID() + "' ");

            log.info("[SQL] : "+sqlBuilder.toString().replaceAll("'null'", "null"));

            getJdbcTemplate().update(sqlBuilder.toString().replaceAll("'null'", "null"), new Object[] {});

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);
            res.setBRIBE_ID(req.getBRIBE_ID());

            return res;

        } catch (Exception e) {

            e.printStackTrace();

            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setBRIBE_ID("0");

            return res;

        }

    }

    @Override
    public RequestBribeupdDeleteRes RequestBribeupdDelete(RequestBribeupdDeleteReq req) {

        RequestBribeupdDeleteRes res = new RequestBribeupdDeleteRes();

        try {

            StringBuilder sqlBuilder = new StringBuilder()
                    .append(" UPDATE OPS_REQUEST_BRIBE SET IS_ACTIVE = '0' WHERE BRIBE_ID = '" + req.getBRIBE_ID() + "' ");

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