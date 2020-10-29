package com.xcs.requestbribe.p2.controller;

import com.xcs.requestbribe.p2.constant.Message;
import com.xcs.requestbribe.p2.dao.RequestBribeRewardDAO;
import com.xcs.requestbribe.p2.model.RequestBribeReward;
import com.xcs.requestbribe.p2.request.RequestBribeRewardgetByIndictmentIDReq;
import com.xcs.requestbribe.p2.request.RequestBribeRewardinsAllReq;
import com.xcs.requestbribe.p2.request.RequestBribeRewardupdDeleteReq;
import com.xcs.requestbribe.p2.response.MessageResponse;
import com.xcs.requestbribe.p2.response.RequestBribeRewardinsAllRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RequestBribeRewardController {
	
	private static final Logger log = LoggerFactory.getLogger(RequestBribeRewardController.class);
	
	@Autowired
	protected RequestBribeRewardDAO requestBribeRewardDAO;
	
	@PostMapping(value = "/RequestBribeRewardinsAll")
	public ResponseEntity RequestBribeRewardinsAll(@RequestBody RequestBribeRewardinsAllReq req) {
		
		log.info("============= Start API RequestBribeRewardinsAll ================");

		MessageResponse msg = new MessageResponse();
		RequestBribeRewardinsAllRes res = null;
		Boolean checkType = true;

		try {
			
			res = requestBribeRewardDAO.RequestBribeRewardinsAll(req);
			
		} catch (Exception e) {

			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());

		}

		log.info("============= End API RequestBribeRewardinsAll =================");

		return new ResponseEntity(res, HttpStatus.OK);

	}

}
