package com.xcs.requestbribe.p2.controller;

import com.xcs.requestbribe.p2.constant.Message;
import com.xcs.requestbribe.p2.dao.RequestBribeDAO;
import com.xcs.requestbribe.p2.dao.RequestBribeDetailDAO;
import com.xcs.requestbribe.p2.dao.RequestBribeStaffDAO;
import com.xcs.requestbribe.p2.model.RequestBribe;
import com.xcs.requestbribe.p2.request.*;
import com.xcs.requestbribe.p2.response.MessageResponse;
import com.xcs.requestbribe.p2.response.RequestBribeinsAllRes;
import com.xcs.requestbribe.p2.response.RequestBribeupdByConRes;
import com.xcs.requestbribe.p2.response.RequestBribeupdDeleteRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestBribeController {

    private static final Logger log = LoggerFactory.getLogger(RequestBribeController.class);

    @Autowired
    private RequestBribeDAO requestBribeDAO;

    @Autowired
    private RequestBribeDetailDAO requestBribeDetailDAO;

    @Autowired
    private RequestBribeStaffDAO requestBribeStaffDAO;

    @PostMapping(value = "/RequestBribegetByCon")
    public ResponseEntity RequestBribegetByCon(@RequestBody RequestBribegetByConReq req) {

        log.info("============= Start API RequestBribegetByCon ================");

        MessageResponse msg = new MessageResponse();
        RequestBribe res = null;
        Boolean checkType = true;

        try {

            res = requestBribeDAO.RequestBribegetByCon(req);

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

        log.info("============= End API RequestBribegetByCon =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

    @PostMapping(value = "/RequestBribeinsAll")
    public ResponseEntity RequestBribeinsAll(@RequestBody RequestBribeinsAllReq req) {

        log.info("============= Start API RequestBribeinsAll ================");

        MessageResponse msg = new MessageResponse();
        RequestBribeinsAllRes res = null;
        Boolean checkType = true;

        try {

            res = requestBribeDAO.RequestBribeinsAll(req);

            for (RequestBribeDetailinsAllReq requestBribeDetail : req.getRequestBribeDetail()) {

                requestBribeDetail.setBRIBE_ID(req.getBRIBE_ID());

                requestBribeDetailDAO.RequestBribeDetailinsAll(requestBribeDetail);

            }

            for (RequestBribeStaffinsAllReq requestBribeStaff : req.getRequestBribeStaff()) {

                requestBribeStaff.setBRIBE_ID(req.getBRIBE_ID());

                requestBribeStaffDAO.RequestBribeStaffinsAll(requestBribeStaff);

            }

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestBribeinsAll =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

    @PostMapping(value = "/RequestBribeupdByCon")
    public ResponseEntity RequestBribeupdByCon(@RequestBody RequestBribeupdByConReq req) {

        log.info("============= Start API RequestBribeupdByCon ================");

        MessageResponse msg = new MessageResponse();
        RequestBribeupdByConRes res = null;
        Boolean checkType = true;

        try {

            res = requestBribeDAO.RequestBribeupdByCon(req);

            for (RequestBribeStaffupdByConReq requestBribeStaff : req.getRequestBribeStaff()) {

                if (StringUtils.isEmpty(requestBribeStaff.getSTAFF_ID())) {

                    RequestBribeStaffinsAllReq requestBribeStaffNew = new RequestBribeStaffinsAllReq();

                    BeanUtils.copyProperties(requestBribeStaff, requestBribeStaffNew);

                    requestBribeStaffNew.setBRIBE_ID(req.getBRIBE_ID());

                    requestBribeStaffDAO.RequestBribeStaffinsAll(requestBribeStaffNew);

                } else {

                    requestBribeStaffDAO.RequestBribeStaffupdByCon(requestBribeStaff);

                }

            }

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestBribeupdByCon =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

    @PostMapping(value = "/RequestBribeupdDelete")
    public ResponseEntity RequestBribeupdDelete(@RequestBody RequestBribeupdDeleteReq req) {

        log.info("============= Start API RequestBribeupdDelete ================");

        MessageResponse msg = new MessageResponse();
        RequestBribeupdDeleteRes res = null;
        Boolean checkType = true;

        try {

            res = requestBribeDAO.RequestBribeupdDelete(req);

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestBribeupdDelete =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

}
