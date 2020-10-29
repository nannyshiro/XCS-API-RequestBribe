package com.xcs.requestbribe.p2.controller;

import com.xcs.requestbribe.p2.constant.Message;
import com.xcs.requestbribe.p2.dao.RequestListDAO;
import com.xcs.requestbribe.p2.dao.RequestRewardDAO;
import com.xcs.requestbribe.p2.dao.RequestRewardDetailDAO;
import com.xcs.requestbribe.p2.dao.RequestRewardStaffDAO;
import com.xcs.requestbribe.p2.model.RequestReward;
import com.xcs.requestbribe.p2.request.*;
import com.xcs.requestbribe.p2.response.MessageResponse;
import com.xcs.requestbribe.p2.response.RequestRewardinsAllRes;
import com.xcs.requestbribe.p2.response.RequestRewardupdByConRes;
import com.xcs.requestbribe.p2.response.RequestRewardupdDeleteRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RequestRewardController {

    private static final Logger log = LoggerFactory.getLogger(RequestRewardController.class);

    @Autowired
    private RequestRewardDAO requestRewardDAO;

    @Autowired
    private RequestRewardDetailDAO requestRewardDetailDAO;

    @Autowired
    private RequestRewardStaffDAO requestRewardStaffDAO;

    @PostMapping(value = "/RequestRewardgetByCon")
    public ResponseEntity RequestRewardgetByCon(@RequestBody RequestRewardgetByConReq req) {

        log.info("============= Start API RequestRewardgetByCon ================");

        MessageResponse msg = new MessageResponse();
        RequestReward res = null;
        Boolean checkType = true;

        try {

            res = requestRewardDAO.RequestRewardgetByCon(req);

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestRewardgetByCon =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

    @PostMapping(value = "/RequestRewardinsAll")
    public ResponseEntity RequestRewardinsAll(@RequestBody RequestRewardinsAllReq req) {

        log.info("============= Start API RequestRewardinsAll ================");

        MessageResponse msg = new MessageResponse();
        RequestRewardinsAllRes res = null;
        Boolean checkType = true;

        try {

            res = requestRewardDAO.RequestRewardinsAll(req);

            for (RequestRewardDetailinsAllReq requestRewardDetail : req.getRequestRewardDetail()) {

                requestRewardDetail.setREWARD_ID(req.getREWARD_ID());

                requestRewardDetailDAO.RequestRewardDetailinsAll(requestRewardDetail);

            }

            for (RequestRewardStaffinsAllReq requestRewardStaff : req.getRequestRewardStaff()) {

                requestRewardStaff.setREWARD_ID(req.getREWARD_ID());

                requestRewardStaffDAO.RequestRewardStaffinsAll(requestRewardStaff);

            }

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestRewardinsAll =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

    @PostMapping(value = "/RequestRewardupdByCon")
    public ResponseEntity RequestRewardupdByCon(@RequestBody RequestRewardupdByConReq req) {

        log.info("============= Start API RequestRewardupdByCon ================");

        MessageResponse msg = new MessageResponse();
        RequestRewardupdByConRes res = null;
        Boolean checkType = true;

        try {

            res = requestRewardDAO.RequestRewardupdByCon(req);

            for (RequestRewardStaffupdByConReq requestRewardStaff : req.getRequestRewardStaff()) {

                if (StringUtils.isEmpty(requestRewardStaff.getSTAFF_ID())) {

                    RequestRewardStaffinsAllReq requestRewardStaffNew = new RequestRewardStaffinsAllReq();

                    BeanUtils.copyProperties(requestRewardStaff, requestRewardStaffNew);

                    requestRewardStaffNew.setREWARD_ID(req.getREWARD_ID());

                    requestRewardStaffDAO.RequestRewardStaffinsAll(requestRewardStaffNew);

                } else {

                    requestRewardStaffDAO.RequestRewardStaffupdByCon(requestRewardStaff);

                }

            }

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestRewardupdByCon =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

    @PostMapping(value = "/RequestRewardupdDelete")
    public ResponseEntity RequestRewardupdDelete(@RequestBody RequestRewardupdDeleteReq req) {

        log.info("============= Start API RequestRewardupdDelete ================");

        MessageResponse msg = new MessageResponse();
        RequestRewardupdDeleteRes res = null;
        Boolean checkType = true;

        try {

            res = requestRewardDAO.RequestRewardupdDelete(req);

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestRewardupdDelete =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

}
