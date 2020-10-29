package com.xcs.requestbribe.p2.controller;

import com.xcs.requestbribe.p2.constant.Message;
import com.xcs.requestbribe.p2.dao.RequestArrestLawsuitProveCompareDAO;
import com.xcs.requestbribe.p2.dao.RequestListDAO;
import com.xcs.requestbribe.p2.model.RequestArrestLawsuitProveCompare;
import com.xcs.requestbribe.p2.model.RequestList;
import com.xcs.requestbribe.p2.request.RequestArrestLawsuitProveComparegetByConReq;
import com.xcs.requestbribe.p2.request.RequestListgetByConAdvReq;
import com.xcs.requestbribe.p2.response.MessageResponse;
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
public class RequestArrestLawsuitProveCompareController {

    private static final Logger log = LoggerFactory.getLogger(RequestArrestLawsuitProveCompareController.class);

    @Autowired
    private RequestArrestLawsuitProveCompareDAO requestArrestLawsuitProveCompareDAO;

    @PostMapping(value = "/RequestArrestLawsuitProveComparegetByCon")
    public ResponseEntity RequestArrestLawsuitProveComparegetByCon(@RequestBody RequestArrestLawsuitProveComparegetByConReq req) {

        log.info("============= Start API RequestArrestLawsuitProveComparegetByCon ================");

        MessageResponse msg = new MessageResponse();
        List<RequestArrestLawsuitProveCompare> res = null;
        Boolean checkType = true;

        try {

            res = requestArrestLawsuitProveCompareDAO.RequestArrestLawsuitProveComparegetByCon(req);

        } catch (Exception e) {

            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }

        log.info("============= End API RequestArrestLawsuitProveComparegetByCon =================");

        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);

    }

}
