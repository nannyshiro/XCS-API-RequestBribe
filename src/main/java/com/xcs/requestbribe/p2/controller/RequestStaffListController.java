package com.xcs.requestbribe.p2.controller;

import com.xcs.requestbribe.p2.constant.Message;
import com.xcs.requestbribe.p2.dao.RequestStaffListDAO;
import com.xcs.requestbribe.p2.model.RequestStaffList;
import com.xcs.requestbribe.p2.request.RequestStaffListgetByConReq;
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
public class RequestStaffListController {

    private static final Logger log = LoggerFactory.getLogger(RequestStaffListController.class);

    @Autowired
    private RequestStaffListDAO requestStaffListDAO;

    @PostMapping(value = "/RequestStaffListgetByCon")
    public ResponseEntity RequestStaffListgetByCon(@RequestBody RequestStaffListgetByConReq req) {

        log.info("============= Start API RequestStaffListgetByCon ================");

        MessageResponse msg = new MessageResponse();
        RequestStaffList res = null;
        Boolean checkType = true;

        try {

            res = requestStaffListDAO.RequestStaffListgetByCon(req);

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestStaffListgetByCon =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

}
