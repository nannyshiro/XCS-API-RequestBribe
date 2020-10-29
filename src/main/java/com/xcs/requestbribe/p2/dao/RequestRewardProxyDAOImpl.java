package com.xcs.requestbribe.p2.dao;

import com.xcs.requestbribe.p2.constant.Message;
import com.xcs.requestbribe.p2.model.RequestBribe;
import com.xcs.requestbribe.p2.model.RequestList;
import com.xcs.requestbribe.p2.model.RequestRewardProxy;
import com.xcs.requestbribe.p2.request.*;
import com.xcs.requestbribe.p2.response.RequestBribeinsAllRes;
import com.xcs.requestbribe.p2.response.RequestRewardProxyinsAllRes;
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
public class RequestRewardProxyDAOImpl extends RequestBribeExt implements RequestRewardProxyDAO {

    private static final Logger log = LoggerFactory.getLogger(RequestRewardProxyDAOImpl.class);

    @Override
    public RequestRewardProxy RequestRewardProxygetByCon(RequestRewardProxygetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append(" SELECT DISTINCT OPS_REQUEST_REWARD_PROXY.*, OPS_LAWSUIT_STAFF.* FROM OPS_REQUEST_REWARD_PROXY LEFT JOIN OPS_REQUEST_REWARD ON OPS_REQUEST_REWARD_PROXY.REWARD_ID = OPS_REQUEST_REWARD.REWARD_ID LEFT JOIN OPS_REQUEST_BRIBE_REWARD ON OPS_REQUEST_REWARD.BRIBE_REWARD_ID = OPS_REQUEST_BRIBE_REWARD.BRIBE_REWARD_ID LEFT JOIN OPS_ARREST_INDICTMENT ON OPS_REQUEST_BRIBE_REWARD.INDICTMENT_ID = OPS_ARREST_INDICTMENT.INDICTMENT_ID LEFT JOIN OPS_LAWSUIT ON OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_LAWSUIT.INDICTMENT_ID LEFT JOIN OPS_LAWSUIT_STAFF ON OPS_LAWSUIT.LAWSUIT_ID = OPS_LAWSUIT_STAFF.LAWSUIT_ID WHERE OPS_REQUEST_REWARD_PROXY.IS_ACTIVE = 1 ");

        if (req.getPROXY_ID() != null && !"".equals(req.getPROXY_ID())) {

            sqlBuilder.append(" AND OPS_REQUEST_REWARD_PROXY.PROXY_ID = " + req.getPROXY_ID() + " ");

        }

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        RequestRewardProxy data = getJdbcTemplate().queryForObject(sqlBuilder.toString(), new RowMapper<RequestRewardProxy>() {

            public RequestRewardProxy mapRow(ResultSet rs, int rowNum) throws SQLException {

                RequestRewardProxy item = new RequestRewardProxy();

                item.setPROXY_ID(rs.getString("PROXY_ID"));
                item.setREWARD_ID(rs.getString("REWARD_ID"));
                item.setPROXY_STAFF_ID(rs.getString("PROXY_STAFF_ID"));
                item.setATTORNEY_STAFF_ID(rs.getString("ATTORNEY_STAFF_ID"));
                item.setPROXY_NO_1(rs.getString("PROXY_NO_1"));
                item.setPROXY_NO_2(rs.getString("PROXY_NO_2"));
                item.setPROXY_DATE(rs.getString("PROXY_DATE"));
                item.setPROXY_DESC(rs.getString("PROXY_DESC"));
                item.setPAYMENT_TYPE(rs.getString("PAYMENT_TYPE"));
                item.setPAYMENT_NAME(rs.getString("PAYMENT_NAME"));
                item.setAPPROVE_PAYMENT_DATE(rs.getString("APPROVE_PAYMENT_DATE"));
                item.setIS_ACTIVE(rs.getString("IS_ACTIVE"));

                return item;

            }

        });

        return data;

    }

    @Override
    public RequestRewardProxyinsAllRes RequestRewardProxyinsAll(RequestRewardProxyinsAllReq req) {

        RequestRewardProxyinsAllRes res = new RequestRewardProxyinsAllRes();

        try {

            req.setPROXY_ID(getSequences(" SELECT OPS_REQUEST_REWARD_PROXY_SEQ.NEXTVAL FROM DUAL "));

            StringBuilder sqlBuilder = new StringBuilder()
                    .append(" INSERT INTO OPS_REQUEST_REWARD_PROXY (PROXY_ID, REWARD_ID, PROXY_STAFF_ID, ATTORNEY_STAFF_ID, PROXY_NO_1, PROXY_NO_2, PROXY_DATE, PROXY_DESC, PAYMENT_TYPE, PAYMENT_NAME, APPROVE_PAYMENT_DATE, IS_ACTIVE) VALUES ('" + req.getPROXY_ID() + "', '" + req.getREWARD_ID() + "', '" + req.getPROXY_STAFF_ID() + "', '" + req.getATTORNEY_STAFF_ID() + "', '" + req.getPROXY_NO_1() + "', '" + req.getPROXY_NO_2() + "', TO_DATE('" + req.getPROXY_DATE() + "', 'YYYY-MM-DD HH24:MI:SS'), '" + req.getPROXY_DESC() + "', '" + req.getPAYMENT_TYPE() + "', '" + req.getPAYMENT_NAME() + "', TO_DATE('" + req.getAPPROVE_PAYMENT_DATE() + "', 'YYYY-MM-DD HH24:MI:SS'), '" + req.getIS_ACTIVE() + "') ");

            log.info("[SQL] : "+sqlBuilder.toString());

            getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);
            res.setPROXY_ID(req.getPROXY_ID());

            return res;

        } catch (Exception e) {

            e.printStackTrace();

            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setPROXY_ID("0");

            return res;

        }

    }

}