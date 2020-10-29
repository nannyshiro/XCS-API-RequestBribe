package com.xcs.requestbribe.p2.controller;

import com.xcs.requestbribe.p2.constant.Message;
import com.xcs.requestbribe.p2.dao.RequestBribeDetailDAO;
import com.xcs.requestbribe.p2.dao.RequestListDAO;
import com.xcs.requestbribe.p2.request.RequestBribeDetailinsAllReq;
import com.xcs.requestbribe.p2.request.RequestBribeDetailupdDeleteReq;
import com.xcs.requestbribe.p2.response.MessageResponse;
import com.xcs.requestbribe.p2.response.RequestBribeDetailinsAllRes;
import com.xcs.requestbribe.p2.response.RequestBribeDetailupdDeleteRes;
import com.xcs.requestbribe.p2.response.RequestBribeinsAllRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestBribeDetailController {

    private static final Logger log = LoggerFactory.getLogger(RequestBribeDetailController.class);

    @Autowired
    private RequestBribeDetailDAO requestBribeDetailDAO;

    @PostMapping(value = "/RequestBribeDetailinsAll")
    public ResponseEntity RequestBribeDetailinsAll(@RequestBody RequestBribeDetailinsAllReq req) {

        log.info("============= Start API RequestBribeDetailinsAll ================");

        MessageResponse msg = new MessageResponse();
        RequestBribeDetailinsAllRes res = null;
        Boolean checkType = true;

        try {

            res = requestBribeDetailDAO.RequestBribeDetailinsAll(req);

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestBribeDetailinsAll =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

    @PostMapping(value = "/RequestBribeDetailupdDelete")
    public ResponseEntity RequestBribeDetailupdDelete(@RequestBody RequestBribeDetailupdDeleteReq req) {

        log.info("============= Start API RequestBribeDetailupdDelete ================");

        MessageResponse msg = new MessageResponse();
        RequestBribeDetailupdDeleteRes res = null;
        Boolean checkType = true;

        try {

            res = requestBribeDetailDAO.RequestBribeDetailupdDelete(req);

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestBribeDetailupdDelete =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

}
