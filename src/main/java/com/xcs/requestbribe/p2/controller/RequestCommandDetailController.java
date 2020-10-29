package com.xcs.requestbribe.p2.controller;

import com.xcs.requestbribe.p2.constant.Message;
import com.xcs.requestbribe.p2.dao.RequestCommandDAO;
import com.xcs.requestbribe.p2.dao.RequestCommandDetailDAO;
import com.xcs.requestbribe.p2.request.RequestCommandDetailinsAllupdByConReq;
import com.xcs.requestbribe.p2.request.RequestCommandDetailupdDeleteReq;
import com.xcs.requestbribe.p2.request.RequestCommandinsAllupdByConReq;
import com.xcs.requestbribe.p2.request.RequestCommandupdDeleteReq;
import com.xcs.requestbribe.p2.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestCommandDetailController {

    private static final Logger log = LoggerFactory.getLogger(RequestCommandDetailController.class);

    @Autowired
    private RequestCommandDetailDAO requestCommandDetailDAO;

//    @PostMapping(value = "/RequestCommandgetByCon")
//    public ResponseEntity RequestCommandgetByCon(@RequestBody RequestCommandgetByConReq req) {
//
//        log.info("============= Start API RequestCommandgetByCon ================");
//
//        MessageResponse msg = new MessageResponse();
//        RequestCommand res = null;
//        Boolean checkType = true;
//
//        try {
//
//            res = requestCommandDAO.RequestCommandgetByCon(req);
//
//        } catch (EmptyResultDataAccessException e) {
//
//            checkType = false;
//            msg.setIsSuccess(Message.FALSE);
//            msg.setMsg("Not Found Record.");
//
//        } catch (IncorrectResultSizeDataAccessException e) {
//
//            checkType = false;
//            msg.setIsSuccess(Message.FALSE);
//            msg.setMsg("Found Multiple Records.");
//
//        } catch (Exception e) {
//
//            checkType = false;
//            msg.setIsSuccess(Message.FALSE);
//            msg.setMsg(e.getMessage());
//
//        }
//
//        log.info("============= End API RequestCommandgetByCon =================");
//
//        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
//
//    }

    @PostMapping(value = "/RequestCommandDetailinsAll")
    public ResponseEntity RequestCommandDetailinsAll(@RequestBody RequestCommandDetailinsAllupdByConReq req) {

        log.info("============= Start API RequestCommandDetailinsAll ================");

        MessageResponse msg = new MessageResponse();
        RequestCommandDetailinsAllupdByConRes res = null;
        Boolean checkType = true;

        try {

            res = requestCommandDetailDAO.RequestCommandDetailinsAll(req);

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestCommandDetailinsAll =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

    @PostMapping(value = "/RequestCommandDetailupdByCon")
    public ResponseEntity RequestCommandDetailupdByCon(@RequestBody RequestCommandDetailinsAllupdByConReq req) {

        log.info("============= Start API RequestCommandDetailupdByCon ================");

        MessageResponse msg = new MessageResponse();
        RequestCommandDetailinsAllupdByConRes res = null;
        Boolean checkType = true;

        try {

            res = requestCommandDetailDAO.RequestCommandDetailupdByCon(req);

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestCommandDetailupdByCon =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

    @PostMapping(value = "/RequestCommandDetailupdDelete")
    public ResponseEntity RequestCommandDetailupdDelete(@RequestBody RequestCommandDetailupdDeleteReq req) {

        log.info("============= Start API RequestCommandDetailupdDelete ================");

        MessageResponse msg = new MessageResponse();
        RequestCommandDetailupdDeleteRes res = null;
        Boolean checkType = true;

        try {

            res = requestCommandDetailDAO.RequestCommandDetailupdDelete(req);

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestCommandDetailupdDelete =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

}
