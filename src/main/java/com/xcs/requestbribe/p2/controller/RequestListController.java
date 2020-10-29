package com.xcs.requestbribe.p2.controller;

import com.xcs.requestbribe.p2.constant.Message;
import com.xcs.requestbribe.p2.dao.RequestListDAO;
import com.xcs.requestbribe.p2.model.RequestList;
import com.xcs.requestbribe.p2.model.RequestListConAdv;
import com.xcs.requestbribe.p2.request.RequestListgetByConAdvReq;
import com.xcs.requestbribe.p2.request.RequestListgetByKeywordReq;
import com.xcs.requestbribe.p2.response.MessageResponse;
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
public class RequestListController {

    private static final Logger log = LoggerFactory.getLogger(RequestListController.class);

    @Autowired
    private RequestListDAO requestListDAO;

    @PostMapping(value = "/RequestListgetByKeyword")
    public ResponseEntity RequestListgetByKeyword(@RequestBody RequestListgetByKeywordReq req) {

        log.info("============= Start API RequestListgetByKeyword ================");

        MessageResponse msg = new MessageResponse();
        List<RequestList> res = null;
        Boolean checkType = true;

        try {

            res = requestListDAO.RequestListgetByKeyword(req);

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestListgetByKeyword =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

    @PostMapping(value = "/RequestListgetByConAdv")
    public ResponseEntity RequestListgetByConAdv(@RequestBody RequestListgetByConAdvReq req) {

        log.info("============= Start API RequestListgetByConAdv ================");

        MessageResponse msg = new MessageResponse();
        List<RequestListConAdv> res = null;
        Boolean checkType = true;

        try {

            res = requestListDAO.RequestListgetByConAdv(req);

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestListgetByConAdv =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

}
