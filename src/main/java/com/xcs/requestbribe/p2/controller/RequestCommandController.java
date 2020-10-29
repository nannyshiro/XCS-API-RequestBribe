package com.xcs.requestbribe.p2.controller;

import com.xcs.requestbribe.p2.constant.Message;
import com.xcs.requestbribe.p2.dao.*;
import com.xcs.requestbribe.p2.model.RequestBribe;
import com.xcs.requestbribe.p2.model.RequestCommand;
import com.xcs.requestbribe.p2.model.RequestCommandDetail;
import com.xcs.requestbribe.p2.request.*;
import com.xcs.requestbribe.p2.response.*;
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

import java.util.ArrayList;
import java.util.List;

@RestController
public class RequestCommandController {

    private static final Logger log = LoggerFactory.getLogger(RequestCommandController.class);

    @Autowired
    private RequestCommandDAO requestCommandDAO;

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

    @PostMapping(value = "/RequestCommandinsAll")
    public ResponseEntity RequestCommandinsAll(@RequestBody RequestCommandinsAllupdByConReq req) {

        log.info("============= Start API RequestCommandinsAll ================");

        MessageResponse msg = new MessageResponse();
        RequestCommandinsAllupdByConRes res = null;
        Boolean checkType = true;

        try {

            res = requestCommandDAO.RequestCommandinsAll(req);

            for (RequestCommandDetailinsAllupdByConReq requestCommandDetail : req.getRequestCommandDetail()) {

                requestCommandDetail.setCOMMAND_ID(req.getCOMMAND_ID());

                RequestCommandDetailinsAllupdByConRes resDetail = requestCommandDetailDAO.RequestCommandDetailinsAll(requestCommandDetail);

                res.getCOMMAND_DETAIL_ID().add(resDetail.getCOMMAND_DETAIL_ID());

            }

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestCommandinsAll =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

    @PostMapping(value = "/RequestCommandupdByCon")
    public ResponseEntity RequestCommandupdByCon(@RequestBody RequestCommandinsAllupdByConReq req) {

        log.info("============= Start API RequestCommandupdByCon ================");

        MessageResponse msg = new MessageResponse();
        RequestCommandinsAllupdByConRes res = null;
        Boolean checkType = true;

        try {

            res = requestCommandDAO.RequestCommandupdByCon(req);

            for (RequestCommandDetailinsAllupdByConReq requestCommandDetail : req.getRequestCommandDetail()) {

                if (StringUtils.isEmpty(requestCommandDetail.getCOMMAND_DETAIL_ID())) {

                    requestCommandDetail.setCOMMAND_ID(req.getCOMMAND_ID());

                    RequestCommandDetailinsAllupdByConRes resDetail = requestCommandDetailDAO.RequestCommandDetailinsAll(requestCommandDetail);

                    res.getCOMMAND_DETAIL_ID().add(resDetail.getCOMMAND_DETAIL_ID());

                } else {

                    RequestCommandDetailinsAllupdByConRes resDetail = requestCommandDetailDAO.RequestCommandDetailupdByCon(requestCommandDetail);

                    res.getCOMMAND_DETAIL_ID().add(resDetail.getCOMMAND_DETAIL_ID());

                }

            }

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestCommandupdByCon =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

    @PostMapping(value = "/RequestCommandupdDelete")
    public ResponseEntity RequestCommandupdDelete(@RequestBody RequestCommandupdDeleteReq req) {

        log.info("============= Start API RequestCommandupdDelete ================");

        MessageResponse msg = new MessageResponse();
        RequestCommandupdDeleteRes res = null;
        Boolean checkType = true;

        try {

            res = requestCommandDAO.RequestCommandupdDelete(req);

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestCommandupdDelete =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

}
