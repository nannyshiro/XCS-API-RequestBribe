package com.xcs.requestbribe.p2.dao;

import com.xcs.requestbribe.p2.constant.Message;
import com.xcs.requestbribe.p2.model.RequestBribeReward;
import com.xcs.requestbribe.p2.request.RequestBribeRewardgetByIndictmentIDReq;
import com.xcs.requestbribe.p2.request.RequestBribeRewardinsAllReq;
import com.xcs.requestbribe.p2.request.RequestBribeRewardupdDeleteReq;
import com.xcs.requestbribe.p2.response.RequestBribeRewardinsAllRes;
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
public class RequestBribeRewardDAOImpl extends RequestBribeExt implements RequestBribeRewardDAO{
	
	private static final Logger log = LoggerFactory.getLogger(RequestBribeRewardDAOImpl.class);

	@Override
	public RequestBribeRewardinsAllRes RequestBribeRewardinsAll(RequestBribeRewardinsAllReq req) {

		RequestBribeRewardinsAllRes res = new RequestBribeRewardinsAllRes();

		try {

			req.setBRIBE_REWARD_ID(getSequences(" SELECT OPS_REQUEST_BRIBE_REWARD_SEQ.NEXTVAL FROM DUAL "));

			StringBuilder sqlBuilder = new StringBuilder()
					.append(" INSERT INTO OPS_REQUEST_BRIBE_REWARD (BRIBE_REWARD_ID, INDICTMENT_ID, HAVE_NOTICE, IS_ACTIVE) VALUES ('" + req.getBRIBE_REWARD_ID() + "', '" + req.getINDICTMENT_ID() + "', '" + req.getHAVE_NOTICE() + "', '" + req.getIS_ACTIVE() + "') ");

			log.info("[SQL] : "+sqlBuilder.toString());

			getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});

			res.setIsSuccess(Message.TRUE);
			res.setMsg(Message.COMPLETE);
			res.setBRIBE_REWARD_ID(req.getBRIBE_REWARD_ID());

			return res;

		} catch (Exception e) {

			e.printStackTrace();

			res.setIsSuccess(Message.FALSE);
			res.setMsg(e.getMessage());
			res.setBRIBE_REWARD_ID("0");

			return res;

		}

	}

}