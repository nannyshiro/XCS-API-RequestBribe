package com.xcs.requestbribe.p2.controller;

import com.xcs.requestbribe.p2.constant.Message;
import com.xcs.requestbribe.p2.dao.RequestListDAO;
import com.xcs.requestbribe.p2.dao.RequestRewardDetailDAO;
import com.xcs.requestbribe.p2.model.RequestList;
import com.xcs.requestbribe.p2.request.RequestRewardDetailinsAllReq;
import com.xcs.requestbribe.p2.request.RequestRewardDetailupdDeleteReq;
import com.xcs.requestbribe.p2.response.MessageResponse;
import com.xcs.requestbribe.p2.response.RequestRewardDetailinsAllRes;
import com.xcs.requestbribe.p2.response.RequestRewardDetailupdDeleteRes;
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
public class RequestRewardDetailController {

    private static final Logger log = LoggerFactory.getLogger(RequestRewardDetailController.class);

    @Autowired
    private RequestRewardDetailDAO requestRewardDetailDAO;

    @PostMapping(value = "/RequestRewardDetailinsAll")
    public ResponseEntity RequestRewardDetailinsAll(@RequestBody RequestRewardDetailinsAllReq req) {

        log.info("============= Start API RequestRewardDetailinsAll ================");

        MessageResponse msg = new MessageResponse();
        RequestRewardDetailinsAllRes res = null;
        Boolean checkType = true;

        try {

            res = requestRewardDetailDAO.RequestRewardDetailinsAll(req);

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestRewardDetailinsAll =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

    @PostMapping(value = "/RequestRewardDetailupdDelete")
    public ResponseEntity RequestRewardDetailupdDelete(@RequestBody RequestRewardDetailupdDeleteReq req) {

        log.info("============= Start API RequestRewardDetailupdDelete ================");

        MessageResponse msg = new MessageResponse();
        RequestRewardDetailupdDeleteRes res = null;
        Boolean checkType = true;

        try {

            res = requestRewardDetailDAO.RequestRewardDetailupdDelete(req);

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestRewardDetailupdDelete =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

}
