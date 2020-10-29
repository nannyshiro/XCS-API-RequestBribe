package com.xcs.requestbribe.p2.controller;

import com.xcs.requestbribe.p2.constant.Message;
import com.xcs.requestbribe.p2.dao.*;
import com.xcs.requestbribe.p2.model.RequestBribe;
import com.xcs.requestbribe.p2.request.RequestBribeStaffinsAllReq;
import com.xcs.requestbribe.p2.request.RequestBribegetByConReq;
import com.xcs.requestbribe.p2.request.RequestRewardStaffinsAllReq;
import com.xcs.requestbribe.p2.request.RequestinsAllreq;
import com.xcs.requestbribe.p2.response.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {

    private static final Logger log = LoggerFactory.getLogger(RequestController.class);

    @Autowired
    private RequestBribeDAO requestBribeDAO;

    @Autowired
    private RequestBribeDetailDAO requestBribeDetailDAO;

    @Autowired
    private RequestBribeStaffDAO requestBribeStaffDAO;

    @Autowired
    private RequestRewardDAO requestRewardDAO;

    @Autowired
    private RequestRewardDetailDAO requestRewardDetailDAO;

    @Autowired
    private RequestRewardStaffDAO requestRewardStaffDAO;

    @Autowired
    private RequestBribeRewardDAO requestBribeRewardDAO;

    @PostMapping(value = "/RequestinsAll")
    @Transactional
    public ResponseEntity RequestinsAll(@RequestBody RequestinsAllreq req) {

        log.info("============= Start API RequestinsAll ================");

        MessageResponse msg = new MessageResponse();
        RequestBribe res = null;
        Boolean checkType = true;

        try {

            requestBribeRewardDAO.RequestBribeRewardinsAll(req.getRequestBribeRewardinsAllReq());

            req.getRequestBribeinsAllReq().setBRIBE_REWARD_ID(req.getRequestBribeRewardinsAllReq().getBRIBE_REWARD_ID());

            requestBribeDAO.RequestBribeinsAll(req.getRequestBribeinsAllReq());

            req.getRequestBribeDetailinsAllReq().setBRIBE_ID(req.getRequestBribeinsAllReq().getBRIBE_ID());

            requestBribeDetailDAO.RequestBribeDetailinsAll(req.getRequestBribeDetailinsAllReq());

            for (RequestBribeStaffinsAllReq requestBribeStaffinsAllReq : req.getRequestBribeStaffinsAllReqs()) {

                requestBribeStaffinsAllReq.setBRIBE_ID(req.getRequestBribeinsAllReq().getBRIBE_ID());

                requestBribeStaffDAO.RequestBribeStaffinsAll(requestBribeStaffinsAllReq);

            }

            req.getRequestRewardinsAllReq().setBRIBE_REWARD_ID(req.getRequestBribeRewardinsAllReq().getBRIBE_REWARD_ID());

            requestRewardDAO.RequestRewardinsAll(req.getRequestRewardinsAllReq());

            req.getRequestRewardDetailinsAllReq().setREWARD_ID(req.getRequestRewardinsAllReq().getREWARD_ID());

            requestRewardDetailDAO.RequestRewardDetailinsAll(req.getRequestRewardDetailinsAllReq());

            for (RequestRewardStaffinsAllReq requestRewardStaffinsAllReq : req.getRequestRewardStaffinsAllReq()) {

                requestRewardStaffinsAllReq.setREWARD_ID(req.getRequestRewardinsAllReq().getREWARD_ID());

                requestRewardStaffDAO.RequestRewardStaffinsAll(requestRewardStaffinsAllReq);

            }

//            res = requestBribeDAO.RequestBribegetByCon(req);

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

        log.info("============= End API RequestinsAll =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

}
