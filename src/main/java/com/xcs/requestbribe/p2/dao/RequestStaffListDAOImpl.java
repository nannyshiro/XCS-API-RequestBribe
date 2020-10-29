package com.xcs.requestbribe.p2.dao;

import com.xcs.requestbribe.p2.model.*;
import com.xcs.requestbribe.p2.request.RequestListgetByConAdvReq;
import com.xcs.requestbribe.p2.request.RequestListgetByKeywordReq;
import com.xcs.requestbribe.p2.request.RequestStaffListgetByConReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class RequestStaffListDAOImpl extends RequestBribeExt implements RequestStaffListDAO {

    private static final Logger log = LoggerFactory.getLogger(RequestStaffListDAOImpl.class);

    @Override
    public RequestStaffList RequestStaffListgetByCon(RequestStaffListgetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append(" SELECT DISTINCT OPS_ARREST_INDICTMENT.INDICTMENT_ID, OPS_NOTICE.NOTICE_ID, CASE WHEN OPS_NOTICE_INFORMER.INFORMER_STATUS=0 THEN N'สายลับ(ขอปิดนาม)' WHEN OPS_NOTICE_INFORMER.INFORMER_STATUS=1 THEN OPS_NOTICE_INFORMER.TITLE_SHORT_NAME_TH ||''||OPS_NOTICE_INFORMER.FIRST_NAME||''||OPS_NOTICE_INFORMER.LAST_NAME END AS NOTICE_INFORMER, OPS_NOTICE_INFORMER.POSITION, OPS_NOTICE_STAFF.STAFF_ID AS NOTICE_STAFF_ID, OPS_NOTICE_STAFF.TITLE_ID AS NOTICE_TITLE_ID, OPS_NOTICE_STAFF.TITLE_SHORT_NAME_TH AS NOTICE_TITLE_SHORT_NAME_TH, OPS_NOTICE_STAFF.FIRST_NAME || ' ' || OPS_NOTICE_STAFF.LAST_NAME AS NOTICE_STAFF, OPS_NOTICE_STAFF.MANAGEMENT_POS_NAME AS NOTICE_MANAGEMENT_POS_NAME, OPS_NOTICE_STAFF.MANAGEMENT_POS_LEVEL AS NOTICE_MANAGEMENT_POS_LEVEL, OPS_NOTICE_STAFF.MANAGEMENT_POS_LEVEL_NAME AS NOTICE_MANAGEMENT_POS_LEVEL_NAME, OPS_NOTICE_STAFF.OPREATION_POS_NAME AS NOTICE_OPREATION_POS_NAME, OPS_NOTICE_STAFF.OPREATION_POS_LEVEL AS NOTICE_OPREATION_POS_LEVEL, OPS_NOTICE_STAFF.OPERATION_POS_LEVEL_NAME AS NOTICE_OPERATION_POS_LEVEL_NAME, OPS_NOTICE_STAFF.CONTRIBUTOR_ID AS NOTICE_CONTRIBUTOR_ID, MCNOTICE.CONTRIBUTOR_NAME AS NOTICE_CONTRIBUTOR, MRSDNOTICE.PART AS NOTICE_PART, OPS_ARREST_STAFF.STAFF_ID AS ARREST_STAFF_ID, OPS_ARREST_STAFF.TITLE_ID AS ARREST_TITLE_ID, OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH AS ARREST_TITLE_SHORT_NAME_TH, OPS_ARREST_STAFF.FIRST_NAME ||' '|| OPS_ARREST_STAFF.LAST_NAME AS ARREST_STAFF, OPS_ARREST_STAFF.MANAGEMENT_POS_NAME AS ARREST_MANAGEMENT_POS_NAME, OPS_ARREST_STAFF.MANAGEMENT_POS_LEVEL AS ARREST_MANAGEMENT_POS_LEVEL, OPS_ARREST_STAFF.MANAGEMENT_POS_LEVEL_NAME AS ARREST_MANAGEMENT_POS_LEVEL_NAME, OPS_ARREST_STAFF.OPREATION_POS_NAME AS ARREST_OPREATION_POS_NAME, OPS_ARREST_STAFF.OPREATION_POS_LEVEL AS ARREST_OPREATION_POS_LEVEL, OPS_ARREST_STAFF.OPERATION_POS_LEVEL_NAME AS ARREST_OPERATION_POS_LEVEL_NAME, OPS_ARREST_STAFF.CONTRIBUTOR_ID AS ARREST_CONTRIBUTOR_ID, MCARREST.CONTRIBUTOR_NAME AS ARREST_CONTRIBUTOR, CASE WHEN OPS_ARREST_STAFF.CONTRIBUTOR_ID ='14' OR OPS_ARREST_STAFF.CONTRIBUTOR_ID = '15' THEN (SELECT FIRST_PART FROM MAS_REWARD_DIVISION WHERE MAS_REWARD_DIVISION.REWARD_DIVISION_ID = '1' AND MAS_REWARD_DIVISION.IS_ACTIVE = '1') ELSE 0 END AS ARREST_PART_I, MRSDARREST.PART AS ARREST_PART_II, OPS_LAWSUIT_STAFF.STAFF_ID AS LAWSUIT_STAFF_ID, OPS_LAWSUIT_STAFF.TITLE_ID AS LAWSUIT_TITLE_ID, OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_TH AS LAWSUIT_TITLE_SHORT_NAME_TH, OPS_LAWSUIT_STAFF.FIRST_NAME ||' '|| OPS_LAWSUIT_STAFF.LAST_NAME AS LAWSUIT_STAFF, OPS_LAWSUIT_STAFF.MANAGEMENT_POS_NAME AS LAWSUIT_MANAGEMENT_POS_NAME, OPS_LAWSUIT_STAFF.MANAGEMENT_POS_LEVEL AS LAWSUIT_MANAGEMENT_POS_LEVEL, OPS_LAWSUIT_STAFF.MANAGEMENT_POS_LEVEL_NAME AS LAWSUIT_MANAGEMENT_POS_LEVEL_NAME, OPS_LAWSUIT_STAFF.OPREATION_POS_NAME AS LAWSUIT_OPREATION_POS_NAME, OPS_LAWSUIT_STAFF.OPREATION_POS_LEVEL AS LAWSUIT_OPREATION_POS_LEVEL, OPS_LAWSUIT_STAFF.OPERATION_POS_LEVEL_NAME AS LAWSUIT_OPERATION_POS_LEVEL_NAME, OPS_LAWSUIT_STAFF.CONTRIBUTOR_ID AS LAWSUIT_CONTRIBUTOR_ID, MCLAWSUIT.CONTRIBUTOR_NAME AS LAWSUIT_CONTRIBUTOR, MRSDLAWSUIT.PART AS LAWSUIT_PART, OPS_PROVE_STAFF.STAFF_ID AS PROVE_STAFF_ID, OPS_PROVE_STAFF.TITLE_ID AS PROVE_TITLE_ID, OPS_PROVE_STAFF.TITLE_SHORT_NAME_TH AS PROVE_TITLE_SHORT_NAME_TH, OPS_PROVE_STAFF.FIRST_NAME ||' '|| OPS_PROVE_STAFF.LAST_NAME AS PROVE_STAFF, OPS_PROVE_STAFF.MANAGEMENT_POS_NAME AS PROVE_MANAGEMENT_POS_NAME, OPS_PROVE_STAFF.MANAGEMENT_POS_LEVEL AS PROVE_MANAGEMENT_POS_LEVEL, OPS_PROVE_STAFF.MANAGEMENT_POS_LEVEL_NAME AS PROVE_MANAGEMENT_POS_LEVEL_NAME, OPS_PROVE_STAFF.OPREATION_POS_NAME AS PROVE_OPREATION_POS_NAME, OPS_PROVE_STAFF.OPREATION_POS_LEVEL AS PROVE_OPREATION_POS_LEVEL, OPS_PROVE_STAFF.OPERATION_POS_LEVEL_NAME AS PROVE_OPERATION_POS_LEVEL_NAME, OPS_PROVE_STAFF.CONTRIBUTOR_ID AS PROVE_CONTRIBUTOR_ID, MCPROVE.CONTRIBUTOR_NAME AS PROVE_CONTRIBUTOR, MRSDPROVE.PART AS PROVE_PART, OPS_COMPARE_STAFF.STAFF_ID AS COMPARE_STAFF_ID, OPS_COMPARE_STAFF.TITLE_ID AS COMPARE_TITLE_ID, OPS_COMPARE_STAFF.TITLE_SHORT_NAME_TH AS COMPARE_TITLE_SHORT_NAME_TH, OPS_COMPARE_STAFF.FIRST_NAME ||' '|| OPS_COMPARE_STAFF.LAST_NAME AS COMPARE_STAFF, OPS_COMPARE_STAFF.MANAGEMENT_POS_NAME AS COMPARE_MANAGEMENT_POS_NAME, OPS_COMPARE_STAFF.MANAGEMENT_POS_LEVEL AS COMPARE_MANAGEMENT_POS_LEVEL, OPS_COMPARE_STAFF.MANAGEMENT_POS_LEVEL_NAME AS COMPARE_MANAGEMENT_POS_LEVEL_NAME, OPS_COMPARE_STAFF.OPREATION_POS_NAME AS COMPARE_OPREATION_POS_NAME, OPS_COMPARE_STAFF.OPREATION_POS_LEVEL AS COMPARE_OPREATION_POS_LEVEL, OPS_COMPARE_STAFF.OPERATION_POS_LEVEL_NAME AS COMPARE_OPERATION_POS_LEVEL_NAME, OPS_COMPARE_STAFF.CONTRIBUTOR_ID AS COMPARE_CONTRIBUTOR_ID, MCCOMPARE.CONTRIBUTOR_NAME AS COMPARE_CONTRIBUTOR, MRSDCOMPARE.PART AS COMPARE_PART, OPS_REVENUE_STAFF.STAFF_ID AS REVENUE_STAFF_ID, OPS_REVENUE_STAFF.TITLE_ID AS REVENUE_TITLE_ID, OPS_REVENUE_STAFF.TITLE_SHORT_NAME_TH AS REVENUE_TITLE_SHORT_NAME_TH, OPS_REVENUE_STAFF.FIRST_NAME ||' '|| OPS_REVENUE_STAFF.LAST_NAME AS REVENUE_STAFF, OPS_REVENUE_STAFF.MANAGEMENT_POS_NAME AS REVENUE_MANAGEMENT_POS_NAME, OPS_REVENUE_STAFF.MANAGEMENT_POS_LEVEL AS REVENUE_MANAGEMENT_POS_LEVEL, OPS_REVENUE_STAFF.MANAGEMENT_POS_LEVEL_NAME AS REVENUE_MANAGEMENT_POS_LEVEL_NAME, OPS_REVENUE_STAFF.OPREATION_POS_NAME AS REVENUE_OPREATION_POS_NAME, OPS_REVENUE_STAFF.OPREATION_POS_LEVEL AS REVENUE_OPREATION_POS_LEVEL, OPS_REVENUE_STAFF.OPERATION_POS_LEVEL_NAME AS REVENUE_OPERATION_POS_LEVEL_NAME, OPS_REVENUE_STAFF.CONTRIBUTOR_ID AS REVENUE_CONTRIBUTOR_ID, MCREVENUE.CONTRIBUTOR_NAME AS REVENUE_CONTRIBUTOR, MRSDREVENUE.PART AS REVENUE_PART FROM OPS_ARREST LEFT JOIN OPS_ARREST_INDICTMENT ON OPS_ARREST.ARREST_ID=OPS_ARREST_INDICTMENT.ARREST_ID AND OPS_ARREST.IS_ACTIVE = 1 AND OPS_ARREST_INDICTMENT.IS_ACTIVE = 1 AND OPS_ARREST_INDICTMENT.IS_LAWSUIT_COMPLETE = 1 LEFT JOIN OPS_ARREST_STAFF ON OPS_ARREST_INDICTMENT.ARREST_ID = OPS_ARREST_STAFF.ARREST_ID AND OPS_ARREST_STAFF.IS_ACTIVE = 1 AND OPS_ARREST_STAFF.CONTRIBUTOR_ID IN ('14','15') LEFT JOIN MAS_CONTRIBUTOR MCARREST ON OPS_ARREST_STAFF.CONTRIBUTOR_ID = MCARREST.CONTRIBUTOR_ID AND MCARREST.IS_ACTIVE = 1 LEFT JOIN MAS_REWARD_SECOND_DIVISION MRSDARREST ON OPS_ARREST_STAFF.OPREATION_POS_LEVEL = MRSDARREST.POS_LEVEL AND MRSDARREST.IS_ACTIVE = 1 LEFT JOIN OPS_NOTICE ON OPS_ARREST.ARREST_ID = OPS_NOTICE.ARREST_ID AND OPS_NOTICE.IS_ACTIVE = 1 LEFT JOIN OPS_NOTICE_STAFF ON OPS_NOTICE.NOTICE_ID = OPS_NOTICE_STAFF.NOTICE_ID AND OPS_NOTICE_STAFF.IS_ACTIVE = 1 AND OPS_NOTICE_STAFF.CONTRIBUTOR_ID IN ('7','8','9') LEFT JOIN MAS_CONTRIBUTOR MCNOTICE ON OPS_NOTICE_STAFF.CONTRIBUTOR_ID = MCNOTICE.CONTRIBUTOR_ID AND MCNOTICE.IS_ACTIVE = 1 LEFT JOIN MAS_REWARD_SECOND_DIVISION MRSDNOTICE ON OPS_NOTICE_STAFF.OPREATION_POS_LEVEL = MRSDNOTICE.POS_LEVEL AND MRSDNOTICE.IS_ACTIVE = 1 LEFT JOIN OPS_NOTICE_INFORMER ON OPS_NOTICE.NOTICE_ID = OPS_NOTICE_INFORMER.NOTICE_ID AND OPS_NOTICE_INFORMER.IS_ACTIVE = 1 LEFT JOIN OPS_LAWSUIT ON OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_LAWSUIT.INDICTMENT_ID AND OPS_LAWSUIT.IS_ACTIVE = 1 LEFT JOIN OPS_LAWSUIT_STAFF ON OPS_LAWSUIT.LAWSUIT_ID = OPS_LAWSUIT_STAFF.LAWSUIT_ID AND OPS_LAWSUIT_STAFF.IS_ACTIVE = 1 AND OPS_LAWSUIT_STAFF.CONTRIBUTOR_ID IN ('16','17','18','19','20','21') LEFT JOIN MAS_CONTRIBUTOR MCLAWSUIT ON OPS_LAWSUIT_STAFF.CONTRIBUTOR_ID = MCLAWSUIT.CONTRIBUTOR_ID AND MCLAWSUIT.IS_ACTIVE = 1 INNER JOIN MAS_REWARD_SECOND_DIVISION MRSDLAWSUIT ON OPS_LAWSUIT_STAFF.OPREATION_POS_LEVEL = MRSDLAWSUIT.POS_LEVEL AND MRSDLAWSUIT.IS_ACTIVE = 1 LEFT JOIN OPS_PROVE ON OPS_LAWSUIT.LAWSUIT_ID = OPS_PROVE.LAWSUIT_ID LEFT JOIN OPS_PROVE_STAFF ON OPS_PROVE.PROVE_ID = OPS_PROVE_STAFF.PROVE_ID AND OPS_PROVE_STAFF.IS_ACTIVE = 1 AND OPS_PROVE_STAFF.CONTRIBUTOR_ID IN ('22','23','24','25','26') LEFT JOIN MAS_CONTRIBUTOR MCPROVE ON OPS_PROVE_STAFF.CONTRIBUTOR_ID = MCPROVE.CONTRIBUTOR_ID AND MCPROVE.IS_ACTIVE = 1 LEFT JOIN MAS_REWARD_SECOND_DIVISION MRSDPROVE ON OPS_PROVE_STAFF.OPREATION_POS_LEVEL = MRSDPROVE.POS_LEVEL AND MRSDPROVE.IS_ACTIVE = 1 LEFT JOIN OPS_COMPARE ON OPS_LAWSUIT.LAWSUIT_ID = OPS_COMPARE.LAWSUIT_ID LEFT JOIN OPS_COMPARE_STAFF ON OPS_COMPARE.COMPARE_ID = OPS_COMPARE_STAFF.COMPARE_ID AND OPS_COMPARE_STAFF.IS_ACTIVE = 1 AND OPS_COMPARE_STAFF.CONTRIBUTOR_ID IN ('27','28','29','30','31','32','33','34') LEFT JOIN MAS_CONTRIBUTOR MCCOMPARE ON OPS_COMPARE_STAFF.CONTRIBUTOR_ID = MCCOMPARE.CONTRIBUTOR_ID AND MCCOMPARE.IS_ACTIVE = 1 LEFT JOIN MAS_REWARD_SECOND_DIVISION MRSDCOMPARE ON OPS_COMPARE_STAFF.OPREATION_POS_LEVEL = MRSDCOMPARE.POS_LEVEL AND MRSDCOMPARE.IS_ACTIVE = 1 LEFT JOIN OPS_COMPARE_MAPPING ON OPS_COMPARE.COMPARE_ID = OPS_COMPARE_MAPPING.COMPARE_ID AND OPS_COMPARE_MAPPING.IS_ACTIVE = 1 LEFT JOIN OPS_COMPARE_DETAIL ON OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID = OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID AND OPS_COMPARE_DETAIL.IS_REVENUE = 1 LEFT JOIN OPS_REVENUE_DETAIL ON OPS_COMPARE_DETAIL.COMPARE_DETAIL_ID = OPS_REVENUE_DETAIL.COMPARE_DETAIL_ID AND OPS_REVENUE_DETAIL.IS_ACTIVE = 1 LEFT JOIN OPS_REVENUE ON OPS_REVENUE_DETAIL.REVENUE_ID = OPS_REVENUE.REVENUE_ID AND OPS_REVENUE.IS_ACTIVE = 1 LEFT JOIN OPS_REVENUE_STAFF ON OPS_REVENUE.REVENUE_ID = OPS_REVENUE_STAFF.REVENUE_ID AND OPS_REVENUE_STAFF.IS_ACTIVE = 1 AND OPS_REVENUE_STAFF.CONTRIBUTOR_ID IN ('36','37') LEFT JOIN MAS_CONTRIBUTOR MCREVENUE ON OPS_REVENUE_STAFF.CONTRIBUTOR_ID = MCREVENUE.CONTRIBUTOR_ID AND MCREVENUE.IS_ACTIVE = 1 LEFT JOIN MAS_REWARD_SECOND_DIVISION MRSDREVENUE ON OPS_REVENUE_STAFF.OPREATION_POS_LEVEL = MRSDREVENUE.POS_LEVEL AND MRSDREVENUE.IS_ACTIVE = 1 ");

        if (req.getINDICTMENT_ID() != null && !"".equals(req.getINDICTMENT_ID())) {

            sqlBuilder.append(" WHERE OPS_ARREST_INDICTMENT.INDICTMENT_ID = '" + req.getINDICTMENT_ID() + "' ");

        }

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RequestStaffListResultSet> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper<RequestStaffListResultSet>() {

            public RequestStaffListResultSet mapRow(ResultSet rs, int rowNum) throws SQLException {

                RequestStaffListResultSet item = new RequestStaffListResultSet();

                item.setINDICTMENT_ID(rs.getString("INDICTMENT_ID"));
                item.setNOTICE_ID(rs.getString("NOTICE_ID"));
                item.setNOTICE_INFORMER(rs.getString("NOTICE_INFORMER"));
                item.setPOSITION(rs.getString("POSITION"));
                item.setNOTICE_STAFF_ID(rs.getString("NOTICE_STAFF_ID"));
                item.setNOTICE_TITLE_ID(rs.getString("NOTICE_TITLE_ID"));
                item.setNOTICE_TITLE_SHORT_NAME_TH(rs.getString("NOTICE_TITLE_SHORT_NAME_TH"));
                item.setNOTICE_STAFF(rs.getString("NOTICE_STAFF"));
                item.setNOTICE_MANAGEMENT_POS_NAME(rs.getString("NOTICE_MANAGEMENT_POS_NAME"));
                item.setNOTICE_MANAGEMENT_POS_LEVEL(rs.getString("NOTICE_MANAGEMENT_POS_LEVEL"));
                item.setNOTICE_MANAGEMENT_POS_LEVEL_NAME(rs.getString("NOTICE_MANAGEMENT_POS_LEVEL_NAME"));
                item.setNOTICE_OPREATION_POS_NAME(rs.getString("NOTICE_OPREATION_POS_NAME"));
                item.setNOTICE_OPREATION_POS_LEVEL(rs.getString("NOTICE_OPREATION_POS_LEVEL"));
                item.setNOTICE_OPERATION_POS_LEVEL_NAME(rs.getString("NOTICE_OPERATION_POS_LEVEL_NAME"));
                item.setNOTICE_CONTRIBUTOR_ID(rs.getString("NOTICE_CONTRIBUTOR_ID"));
                item.setNOTICE_CONTRIBUTOR(rs.getString("NOTICE_CONTRIBUTOR"));
                item.setNOTICE_PART(rs.getString("NOTICE_PART"));
                item.setARREST_STAFF_ID(rs.getString("ARREST_STAFF_ID"));
                item.setARREST_TITLE_ID(rs.getString("ARREST_TITLE_ID"));
                item.setARREST_TITLE_SHORT_NAME_TH(rs.getString("ARREST_TITLE_SHORT_NAME_TH"));
                item.setARREST_STAFF(rs.getString("ARREST_STAFF"));
                item.setARREST_MANAGEMENT_POS_NAME(rs.getString("ARREST_MANAGEMENT_POS_NAME"));
                item.setARREST_MANAGEMENT_POS_LEVEL(rs.getString("ARREST_MANAGEMENT_POS_LEVEL"));
                item.setARREST_MANAGEMENT_POS_LEVEL_NAME(rs.getString("ARREST_MANAGEMENT_POS_LEVEL_NAME"));
                item.setARREST_OPREATION_POS_NAME(rs.getString("ARREST_OPREATION_POS_NAME"));
                item.setARREST_OPREATION_POS_LEVEL(rs.getString("ARREST_OPREATION_POS_LEVEL"));
                item.setARREST_OPERATION_POS_LEVEL_NAME(rs.getString("ARREST_OPERATION_POS_LEVEL_NAME"));
                item.setARREST_CONTRIBUTOR_ID(rs.getString("ARREST_CONTRIBUTOR_ID"));
                item.setARREST_CONTRIBUTOR(rs.getString("ARREST_CONTRIBUTOR"));
                item.setARREST_PART_I(rs.getString("ARREST_PART_I"));
                item.setARREST_PART_II(rs.getString("ARREST_PART_II"));
                item.setLAWSUIT_STAFF_ID(rs.getString("LAWSUIT_STAFF_ID"));
                item.setLAWSUIT_TITLE_ID(rs.getString("LAWSUIT_TITLE_ID"));
                item.setLAWSUIT_TITLE_SHORT_NAME_TH(rs.getString("LAWSUIT_TITLE_SHORT_NAME_TH"));
                item.setLAWSUIT_STAFF(rs.getString("LAWSUIT_STAFF"));
                item.setLAWSUIT_MANAGEMENT_POS_NAME(rs.getString("LAWSUIT_MANAGEMENT_POS_NAME"));
                item.setLAWSUIT_MANAGEMENT_POS_LEVEL(rs.getString("LAWSUIT_MANAGEMENT_POS_LEVEL"));
                item.setLAWSUIT_MANAGEMENT_POS_LEVEL_NAME(rs.getString("LAWSUIT_MANAGEMENT_POS_LEVEL_NAME"));
                item.setLAWSUIT_OPREATION_POS_NAME(rs.getString("LAWSUIT_OPREATION_POS_NAME"));
                item.setLAWSUIT_OPREATION_POS_LEVEL(rs.getString("LAWSUIT_OPREATION_POS_LEVEL"));
                item.setLAWSUIT_OPERATION_POS_LEVEL_NAME(rs.getString("LAWSUIT_OPERATION_POS_LEVEL_NAME"));
                item.setLAWSUIT_CONTRIBUTOR_ID(rs.getString("LAWSUIT_CONTRIBUTOR_ID"));
                item.setLAWSUIT_CONTRIBUTOR(rs.getString("LAWSUIT_CONTRIBUTOR"));
                item.setLAWSUIT_PART(rs.getString("LAWSUIT_PART"));
                item.setPROVE_STAFF_ID(rs.getString("PROVE_STAFF_ID"));
                item.setPROVE_TITLE_ID(rs.getString("PROVE_TITLE_ID"));
                item.setPROVE_TITLE_SHORT_NAME_TH(rs.getString("PROVE_TITLE_SHORT_NAME_TH"));
                item.setPROVE_STAFF(rs.getString("PROVE_STAFF"));
                item.setPROVE_MANAGEMENT_POS_NAME(rs.getString("PROVE_MANAGEMENT_POS_NAME"));
                item.setPROVE_MANAGEMENT_POS_LEVEL(rs.getString("PROVE_MANAGEMENT_POS_LEVEL"));
                item.setPROVE_MANAGEMENT_POS_LEVEL_NAME(rs.getString("PROVE_MANAGEMENT_POS_LEVEL_NAME"));
                item.setPROVE_OPREATION_POS_NAME(rs.getString("PROVE_OPREATION_POS_NAME"));
                item.setPROVE_OPREATION_POS_LEVEL(rs.getString("PROVE_OPREATION_POS_LEVEL"));
                item.setPROVE_OPERATION_POS_LEVEL_NAME(rs.getString("PROVE_OPERATION_POS_LEVEL_NAME"));
                item.setPROVE_CONTRIBUTOR_ID(rs.getString("PROVE_CONTRIBUTOR_ID"));
                item.setPROVE_CONTRIBUTOR(rs.getString("PROVE_CONTRIBUTOR"));
                item.setPROVE_PART(rs.getString("PROVE_PART"));
                item.setCOMPARE_STAFF_ID(rs.getString("COMPARE_STAFF_ID"));
                item.setCOMPARE_TITLE_ID(rs.getString("COMPARE_TITLE_ID"));
                item.setCOMPARE_TITLE_SHORT_NAME_TH(rs.getString("COMPARE_TITLE_SHORT_NAME_TH"));
                item.setCOMPARE_STAFF(rs.getString("COMPARE_STAFF"));
                item.setCOMPARE_MANAGEMENT_POS_NAME(rs.getString("COMPARE_MANAGEMENT_POS_NAME"));
                item.setCOMPARE_MANAGEMENT_POS_LEVEL(rs.getString("COMPARE_MANAGEMENT_POS_LEVEL"));
                item.setCOMPARE_MANAGEMENT_POS_LEVEL_NAME(rs.getString("COMPARE_MANAGEMENT_POS_LEVEL_NAME"));
                item.setCOMPARE_OPREATION_POS_NAME(rs.getString("COMPARE_OPREATION_POS_NAME"));
                item.setCOMPARE_OPREATION_POS_LEVEL(rs.getString("COMPARE_OPREATION_POS_LEVEL"));
                item.setCOMPARE_OPERATION_POS_LEVEL_NAME(rs.getString("COMPARE_OPERATION_POS_LEVEL_NAME"));
                item.setCOMPARE_CONTRIBUTOR_ID(rs.getString("COMPARE_CONTRIBUTOR_ID"));
                item.setCOMPARE_CONTRIBUTOR(rs.getString("COMPARE_CONTRIBUTOR"));
                item.setCOMPARE_PART(rs.getString("COMPARE_PART"));
                item.setREVENUE_STAFF_ID(rs.getString("REVENUE_STAFF_ID"));
                item.setREVENUE_TITLE_ID(rs.getString("REVENUE_TITLE_ID"));
                item.setREVENUE_TITLE_SHORT_NAME_TH(rs.getString("REVENUE_TITLE_SHORT_NAME_TH"));
                item.setREVENUE_STAFF(rs.getString("REVENUE_STAFF"));
                item.setREVENUE_MANAGEMENT_POS_NAME(rs.getString("REVENUE_MANAGEMENT_POS_NAME"));
                item.setREVENUE_MANAGEMENT_POS_LEVEL(rs.getString("REVENUE_MANAGEMENT_POS_LEVEL"));
                item.setREVENUE_MANAGEMENT_POS_LEVEL_NAME(rs.getString("REVENUE_MANAGEMENT_POS_LEVEL_NAME"));
                item.setREVENUE_OPREATION_POS_NAME(rs.getString("REVENUE_OPREATION_POS_NAME"));
                item.setREVENUE_OPREATION_POS_LEVEL(rs.getString("REVENUE_OPREATION_POS_LEVEL"));
                item.setREVENUE_OPERATION_POS_LEVEL_NAME(rs.getString("REVENUE_OPERATION_POS_LEVEL_NAME"));
                item.setREVENUE_CONTRIBUTOR_ID(rs.getString("REVENUE_CONTRIBUTOR_ID"));
                item.setREVENUE_CONTRIBUTOR(rs.getString("REVENUE_CONTRIBUTOR"));
                item.setREVENUE_PART(rs.getString("REVENUE_PART"));

                return item;

            }

        });

        RequestStaffList requestStaffList = new RequestStaffList();

        if (dataList.size() > 0) {

            requestStaffList.setINDICTMENT_ID(dataList.get(0).getINDICTMENT_ID());
            requestStaffList.setNOTICE_ID(dataList.get(0).getNOTICE_ID());
            requestStaffList.setNOTICE_INFORMER(dataList.get(0).getNOTICE_INFORMER());
            requestStaffList.setPOSITION(dataList.get(0).getPOSITION());

            for (RequestStaffListResultSet data : dataList) {

                boolean isNoticeStaffDup = false;
                for (RequestNoticeStaff requestNoticeStaffTmp : requestStaffList.getRequestNoticeStaffs()) {

                    if (requestNoticeStaffTmp.getNOTICE_STAFF_ID().equals(data.getNOTICE_STAFF_ID()) && requestNoticeStaffTmp.getNOTICE_CONTRIBUTOR_ID().equals(data.getNOTICE_CONTRIBUTOR_ID())) {

                        isNoticeStaffDup = true;

                        break;

                    }

                }

                if (!isNoticeStaffDup && data.getNOTICE_STAFF_ID() != null) {

                    RequestNoticeStaff requestNoticeStaff = new RequestNoticeStaff();
                    requestNoticeStaff.setNOTICE_STAFF_ID(data.getNOTICE_STAFF_ID());
                    requestNoticeStaff.setNOTICE_TITLE_ID(data.getNOTICE_TITLE_ID());
                    requestNoticeStaff.setNOTICE_TITLE_SHORT_NAME_TH(data.getNOTICE_TITLE_SHORT_NAME_TH());
                    requestNoticeStaff.setNOTICE_STAFF(data.getNOTICE_STAFF());
                    requestNoticeStaff.setNOTICE_MANAGEMENT_POS_NAME(data.getNOTICE_MANAGEMENT_POS_NAME());
                    requestNoticeStaff.setNOTICE_MANAGEMENT_POS_LEVEL(data.getNOTICE_MANAGEMENT_POS_LEVEL());
                    requestNoticeStaff.setNOTICE_MANAGEMENT_POS_LEVEL_NAME(data.getNOTICE_MANAGEMENT_POS_LEVEL_NAME());
                    requestNoticeStaff.setNOTICE_OPREATION_POS_NAME(data.getNOTICE_OPREATION_POS_NAME());
                    requestNoticeStaff.setNOTICE_OPREATION_POS_LEVEL(data.getNOTICE_OPREATION_POS_LEVEL());
                    requestNoticeStaff.setNOTICE_OPERATION_POS_LEVEL_NAME(data.getNOTICE_OPERATION_POS_LEVEL_NAME());
                    requestNoticeStaff.setNOTICE_CONTRIBUTOR_ID(data.getNOTICE_CONTRIBUTOR_ID());
                    requestNoticeStaff.setNOTICE_CONTRIBUTOR(data.getNOTICE_CONTRIBUTOR());
                    requestNoticeStaff.setNOTICE_PART(data.getNOTICE_PART());
                    requestStaffList.getRequestNoticeStaffs().add(requestNoticeStaff);

                }

                boolean isArrestStaffDup = false;
                for (RequestArrestStaff requestArrestStaffTmp : requestStaffList.getRequestArrestStaffs()) {

                    if (requestArrestStaffTmp.getARREST_STAFF_ID().equals(data.getARREST_STAFF_ID()) && requestArrestStaffTmp.getARREST_CONTRIBUTOR_ID().equals(data.getARREST_CONTRIBUTOR_ID())) {

                        isArrestStaffDup = true;

                        break;

                    }

                }

                if (!isArrestStaffDup && data.getARREST_STAFF_ID() != null) {

                    RequestArrestStaff requestArrestStaff = new RequestArrestStaff();
                    requestArrestStaff.setARREST_STAFF_ID(data.getARREST_STAFF_ID());
                    requestArrestStaff.setARREST_TITLE_ID(data.getARREST_TITLE_ID());
                    requestArrestStaff.setARREST_TITLE_SHORT_NAME_TH(data.getARREST_TITLE_SHORT_NAME_TH());
                    requestArrestStaff.setARREST_STAFF(data.getARREST_STAFF());
                    requestArrestStaff.setARREST_MANAGEMENT_POS_NAME(data.getARREST_MANAGEMENT_POS_NAME());
                    requestArrestStaff.setARREST_MANAGEMENT_POS_LEVEL(data.getARREST_MANAGEMENT_POS_LEVEL());
                    requestArrestStaff.setARREST_MANAGEMENT_POS_LEVEL_NAME(data.getARREST_MANAGEMENT_POS_LEVEL_NAME());
                    requestArrestStaff.setARREST_OPREATION_POS_NAME(data.getARREST_OPREATION_POS_NAME());
                    requestArrestStaff.setARREST_OPREATION_POS_LEVEL(data.getARREST_OPREATION_POS_LEVEL());
                    requestArrestStaff.setARREST_OPERATION_POS_LEVEL_NAME(data.getARREST_OPERATION_POS_LEVEL_NAME());
                    requestArrestStaff.setARREST_CONTRIBUTOR_ID(data.getARREST_CONTRIBUTOR_ID());
                    requestArrestStaff.setARREST_CONTRIBUTOR(data.getARREST_CONTRIBUTOR());
                    requestArrestStaff.setARREST_PART_I(data.getARREST_PART_I());
                    requestArrestStaff.setARREST_PART_II(data.getARREST_PART_II());
                    requestStaffList.getRequestArrestStaffs().add(requestArrestStaff);

                }


                boolean isLawsuitStaffDup = false;
                for (RequestLawsuitStaff requestLawsuitStaffTmp : requestStaffList.getRequestLawsuitStaffs()) {

                    if (requestLawsuitStaffTmp.getLAWSUIT_STAFF_ID().equals(data.getLAWSUIT_STAFF_ID()) && requestLawsuitStaffTmp.getLAWSUIT_CONTRIBUTOR_ID().equals(data.getLAWSUIT_CONTRIBUTOR_ID())) {

                        isLawsuitStaffDup = true;

                        break;

                    }

                }

                if (!isLawsuitStaffDup && data.getLAWSUIT_STAFF_ID() != null) {

                    RequestLawsuitStaff requestLawsuitStaff = new RequestLawsuitStaff();
                    requestLawsuitStaff.setLAWSUIT_STAFF_ID(data.getLAWSUIT_STAFF_ID());
                    requestLawsuitStaff.setLAWSUIT_TITLE_ID(data.getLAWSUIT_TITLE_ID());
                    requestLawsuitStaff.setLAWSUIT_TITLE_SHORT_NAME_TH(data.getLAWSUIT_TITLE_SHORT_NAME_TH());
                    requestLawsuitStaff.setLAWSUIT_STAFF(data.getLAWSUIT_STAFF());
                    requestLawsuitStaff.setLAWSUIT_MANAGEMENT_POS_NAME(data.getLAWSUIT_MANAGEMENT_POS_NAME());
                    requestLawsuitStaff.setLAWSUIT_MANAGEMENT_POS_LEVEL(data.getLAWSUIT_MANAGEMENT_POS_LEVEL());
                    requestLawsuitStaff.setLAWSUIT_MANAGEMENT_POS_LEVEL_NAME(data.getLAWSUIT_MANAGEMENT_POS_LEVEL_NAME());
                    requestLawsuitStaff.setLAWSUIT_OPREATION_POS_NAME(data.getLAWSUIT_OPREATION_POS_NAME());
                    requestLawsuitStaff.setLAWSUIT_OPREATION_POS_LEVEL(data.getLAWSUIT_OPREATION_POS_LEVEL());
                    requestLawsuitStaff.setLAWSUIT_OPERATION_POS_LEVEL_NAME(data.getLAWSUIT_OPERATION_POS_LEVEL_NAME());
                    requestLawsuitStaff.setLAWSUIT_CONTRIBUTOR_ID(data.getLAWSUIT_CONTRIBUTOR_ID());
                    requestLawsuitStaff.setLAWSUIT_CONTRIBUTOR(data.getLAWSUIT_CONTRIBUTOR());
                    requestLawsuitStaff.setLAWSUIT_PART(data.getLAWSUIT_PART());
                    requestStaffList.getRequestLawsuitStaffs().add(requestLawsuitStaff);

                }

                boolean isProveStaffDup = false;
                for (RequestProveStaff requestProveStaffTmp : requestStaffList.getRequestProveStaffs()) {

                    if (requestProveStaffTmp.getPROVE_STAFF_ID().equals(data.getPROVE_STAFF_ID()) && requestProveStaffTmp.getPROVE_CONTRIBUTOR_ID().equals(data.getPROVE_CONTRIBUTOR_ID())) {

                        isProveStaffDup = true;

                        break;

                    }

                }

                if (!isProveStaffDup && data.getPROVE_STAFF_ID() != null) {

                    RequestProveStaff requestProveStaff = new RequestProveStaff();
                    requestProveStaff.setPROVE_STAFF_ID(data.getPROVE_STAFF_ID());
                    requestProveStaff.setPROVE_TITLE_ID(data.getPROVE_TITLE_ID());
                    requestProveStaff.setPROVE_TITLE_SHORT_NAME_TH(data.getPROVE_TITLE_SHORT_NAME_TH());
                    requestProveStaff.setPROVE_STAFF(data.getPROVE_STAFF());
                    requestProveStaff.setPROVE_MANAGEMENT_POS_NAME(data.getPROVE_MANAGEMENT_POS_NAME());
                    requestProveStaff.setPROVE_MANAGEMENT_POS_LEVEL(data.getPROVE_MANAGEMENT_POS_LEVEL());
                    requestProveStaff.setPROVE_MANAGEMENT_POS_LEVEL_NAME(data.getPROVE_MANAGEMENT_POS_LEVEL_NAME());
                    requestProveStaff.setPROVE_OPREATION_POS_NAME(data.getPROVE_OPREATION_POS_NAME());
                    requestProveStaff.setPROVE_OPREATION_POS_LEVEL(data.getPROVE_OPREATION_POS_LEVEL());
                    requestProveStaff.setPROVE_OPERATION_POS_LEVEL_NAME(data.getPROVE_OPERATION_POS_LEVEL_NAME());
                    requestProveStaff.setPROVE_CONTRIBUTOR_ID(data.getPROVE_CONTRIBUTOR_ID());
                    requestProveStaff.setPROVE_CONTRIBUTOR(data.getPROVE_CONTRIBUTOR());
                    requestProveStaff.setPROVE_PART(data.getPROVE_PART());
                    requestStaffList.getRequestProveStaffs().add(requestProveStaff);

                }


                boolean isCompareStaffDup = false;
                for (RequestCompareStaff requestCompareStaffTmp : requestStaffList.getRequestCompareStaffs()) {

                    if (requestCompareStaffTmp.getCOMPARE_STAFF_ID().equals(data.getCOMPARE_STAFF_ID()) && requestCompareStaffTmp.getCOMPARE_CONTRIBUTOR_ID().equals(data.getCOMPARE_CONTRIBUTOR_ID())) {

                        isCompareStaffDup = true;

                        break;

                    }

                }

                if (!isCompareStaffDup && data.getCOMPARE_STAFF_ID() != null) {

                    RequestCompareStaff requestCompareStaff = new RequestCompareStaff();
                    requestCompareStaff.setCOMPARE_STAFF_ID(data.getCOMPARE_STAFF_ID());
                    requestCompareStaff.setCOMPARE_TITLE_ID(data.getCOMPARE_TITLE_ID());
                    requestCompareStaff.setCOMPARE_TITLE_SHORT_NAME_TH(data.getCOMPARE_TITLE_SHORT_NAME_TH());
                    requestCompareStaff.setCOMPARE_STAFF(data.getCOMPARE_STAFF());
                    requestCompareStaff.setCOMPARE_MANAGEMENT_POS_NAME(data.getCOMPARE_MANAGEMENT_POS_NAME());
                    requestCompareStaff.setCOMPARE_MANAGEMENT_POS_LEVEL(data.getCOMPARE_MANAGEMENT_POS_LEVEL());
                    requestCompareStaff.setCOMPARE_MANAGEMENT_POS_LEVEL_NAME(data.getCOMPARE_MANAGEMENT_POS_LEVEL_NAME());
                    requestCompareStaff.setCOMPARE_OPREATION_POS_NAME(data.getCOMPARE_OPREATION_POS_NAME());
                    requestCompareStaff.setCOMPARE_OPREATION_POS_LEVEL(data.getCOMPARE_OPREATION_POS_LEVEL());
                    requestCompareStaff.setCOMPARE_OPERATION_POS_LEVEL_NAME(data.getCOMPARE_OPERATION_POS_LEVEL_NAME());
                    requestCompareStaff.setCOMPARE_CONTRIBUTOR_ID(data.getCOMPARE_CONTRIBUTOR_ID());
                    requestCompareStaff.setCOMPARE_CONTRIBUTOR(data.getCOMPARE_CONTRIBUTOR());
                    requestCompareStaff.setCOMPARE_PART(data.getCOMPARE_PART());
                    requestStaffList.getRequestCompareStaffs().add(requestCompareStaff);

                }

                boolean isRevenueStaffDup = false;
                for (RequestRevenueStaff requestRevenueStaffTmp : requestStaffList.getRequestRevenueStaffs()) {

                    if (requestRevenueStaffTmp.getREVENUE_STAFF_ID().equals(data.getREVENUE_STAFF_ID()) && requestRevenueStaffTmp.getREVENUE_CONTRIBUTOR_ID().equals(data.getREVENUE_CONTRIBUTOR_ID())) {

                        isRevenueStaffDup = true;

                        break;

                    }

                }

                if (!isRevenueStaffDup && data.getREVENUE_STAFF_ID() != null) {

                    RequestRevenueStaff requestRevenueStaff = new RequestRevenueStaff();
                    requestRevenueStaff.setREVENUE_STAFF_ID(data.getREVENUE_STAFF_ID());
                    requestRevenueStaff.setREVENUE_TITLE_ID(data.getREVENUE_TITLE_ID());
                    requestRevenueStaff.setREVENUE_TITLE_SHORT_NAME_TH(data.getREVENUE_TITLE_SHORT_NAME_TH());
                    requestRevenueStaff.setREVENUE_STAFF(data.getREVENUE_STAFF());
                    requestRevenueStaff.setREVENUE_MANAGEMENT_POS_NAME(data.getREVENUE_MANAGEMENT_POS_NAME());
                    requestRevenueStaff.setREVENUE_MANAGEMENT_POS_LEVEL(data.getREVENUE_MANAGEMENT_POS_LEVEL());
                    requestRevenueStaff.setREVENUE_MANAGEMENT_POS_LEVEL_NAME(data.getREVENUE_MANAGEMENT_POS_LEVEL_NAME());
                    requestRevenueStaff.setREVENUE_OPREATION_POS_NAME(data.getREVENUE_OPREATION_POS_NAME());
                    requestRevenueStaff.setREVENUE_OPREATION_POS_LEVEL(data.getREVENUE_OPREATION_POS_LEVEL());
                    requestRevenueStaff.setREVENUE_OPERATION_POS_LEVEL_NAME(data.getREVENUE_OPERATION_POS_LEVEL_NAME());
                    requestRevenueStaff.setREVENUE_CONTRIBUTOR_ID(data.getREVENUE_CONTRIBUTOR_ID());
                    requestRevenueStaff.setREVENUE_CONTRIBUTOR(data.getREVENUE_CONTRIBUTOR());
                    requestRevenueStaff.setREVENUE_PART(data.getREVENUE_PART());
                    requestStaffList.getRequestRevenueStaffs().add(requestRevenueStaff);

                }

            }

        }

        return requestStaffList;

    }

}