package com.xcs.requestbribe.p2.controller;

import com.xcs.requestbribe.p2.constant.Message;
import com.xcs.requestbribe.p2.dao.RequestBribeStaffDAO;
import com.xcs.requestbribe.p2.dao.RequestListDAO;
import com.xcs.requestbribe.p2.request.RequestBribeStaffinsAllReq;
import com.xcs.requestbribe.p2.response.MessageResponse;
import com.xcs.requestbribe.p2.response.RequestBribeStaffinsAllRes;
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
public class RequestBribeStaffController {

    private static final Logger log = LoggerFactory.getLogger(RequestBribeStaffController.class);

    @Autowired
    private RequestBribeStaffDAO requestBribeStaffDAO;

    @PostMapping(value = "/RequestBribeStaffinsAll")
    public ResponseEntity RequestBribeStaffinsAll(@RequestBody RequestBribeStaffinsAllReq req) {

        log.info("============= Start API RequestBribeStaffinsAll ================");

        MessageResponse msg = new MessageResponse();
        RequestBribeStaffinsAllRes res = null;
        Boolean checkType = true;

        try {

            res = requestBribeStaffDAO.RequestBribeStaffinsAll(req);

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestBribeStaffinsAll =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

}
