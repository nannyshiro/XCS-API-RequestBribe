package com.xcs.requestbribe.p2.controller;

import com.xcs.requestbribe.p2.constant.Message;
import com.xcs.requestbribe.p2.dao.RequestListDAO;
import com.xcs.requestbribe.p2.dao.RequestRewardStaffDAO;
import com.xcs.requestbribe.p2.model.RequestList;
import com.xcs.requestbribe.p2.request.*;
import com.xcs.requestbribe.p2.response.MessageResponse;
import com.xcs.requestbribe.p2.response.RequestRewardStaffinsAllRes;
import com.xcs.requestbribe.p2.response.RequestRewardStaffupdByConRes;
import com.xcs.requestbribe.p2.response.RequestRewardStaffupdDeleteRes;
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
public class RequestRewardStaffController {

    private static final Logger log = LoggerFactory.getLogger(RequestRewardStaffController.class);

    @Autowired
    private RequestRewardStaffDAO requestRewardStaffDAO;

    @PostMapping(value = "/RequestRewardStaffinsAll")
    public ResponseEntity RequestRewardStaffinsAll(@RequestBody RequestRewardStaffinsAllReq req) {

        log.info("============= Start API RequestRewardStaffinsAll ================");

        MessageResponse msg = new MessageResponse();
        RequestRewardStaffinsAllRes res = null;
        Boolean checkType = true;

        try {

            res = requestRewardStaffDAO.RequestRewardStaffinsAll(req);

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestRewardStaffinsAll =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

    @PostMapping(value = "/RequestRewardStaffupdByCon")
    public ResponseEntity RequestRewardStaffupdByCon(@RequestBody RequestRewardStaffupdByConReq req) {

        log.info("============= Start API RequestRewardStaffupdByCon ================");

        MessageResponse msg = new MessageResponse();
        RequestRewardStaffupdByConRes res = null;
        Boolean checkType = true;

        try {

            res = requestRewardStaffDAO.RequestRewardStaffupdByCon(req);

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestRewardStaffupdByCon =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

    @PostMapping(value = "/RequestRewardStaffupdDelete")
    public ResponseEntity RequestRewardStaffupdDelete(@RequestBody RequestRewardStaffupdDeleteReq req) {

        log.info("============= Start API RequestRewardStaffupdDelete ================");

        MessageResponse msg = new MessageResponse();
        RequestRewardStaffupdDeleteRes res = null;
        Boolean checkType = true;

        try {

            res = requestRewardStaffDAO.RequestRewardStaffupdDelete(req);

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestRewardStaffupdDelete =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

}
