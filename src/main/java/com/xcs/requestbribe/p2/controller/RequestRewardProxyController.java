package com.xcs.requestbribe.p2.controller;

import com.xcs.requestbribe.p2.constant.Message;
import com.xcs.requestbribe.p2.dao.RequestListDAO;
import com.xcs.requestbribe.p2.dao.RequestRewardProxyDAO;
import com.xcs.requestbribe.p2.model.RequestRewardProxy;
import com.xcs.requestbribe.p2.request.RequestRewardProxygetByConReq;
import com.xcs.requestbribe.p2.request.RequestRewardProxyinsAllReq;
import com.xcs.requestbribe.p2.response.MessageResponse;
import com.xcs.requestbribe.p2.response.RequestRewardProxyinsAllRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RequestRewardProxyController {

    private static final Logger log = LoggerFactory.getLogger(RequestRewardProxyController.class);

    @Autowired
    private RequestRewardProxyDAO requestRewardProxyDAO;

    @PostMapping(value = "/RequestRewardProxygetByCon")
    public ResponseEntity RequestRewardProxygetByCon(@RequestBody RequestRewardProxygetByConReq req) {

        log.info("============= Start API RequestRewardProxy ================");

        MessageResponse msg = new MessageResponse();
        RequestRewardProxy res = null;
        Boolean checkType = true;

        try {

            res = requestRewardProxyDAO.RequestRewardProxygetByCon(req);

        } catch (EmptyResultDataAccessException e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg("Not Found Record.");

        } catch (IncorrectResultSizeDataAccessException e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg("Found Multiple Records.");

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestRewardProxy =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

    @PostMapping(value = "/RequestRewardProxyinsAll")
    public ResponseEntity RequestRewardProxyinsAll(@RequestBody RequestRewardProxyinsAllReq req) {

        log.info("============= Start API RequestRewardProxyinsAll ================");

        MessageResponse msg = new MessageResponse();
        RequestRewardProxyinsAllRes res = null;
        Boolean checkType = true;

        try {

            res = requestRewardProxyDAO.RequestRewardProxyinsAll(req);

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestRewardProxyinsAll =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

}
