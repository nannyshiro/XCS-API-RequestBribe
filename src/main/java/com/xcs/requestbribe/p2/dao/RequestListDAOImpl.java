package com.xcs.requestbribe.p2.dao;

import com.xcs.requestbribe.p2.constant.Pattern;
import com.xcs.requestbribe.p2.model.RequestList;
import com.xcs.requestbribe.p2.model.RequestListConAdv;
import com.xcs.requestbribe.p2.request.RequestListgetByConAdvReq;
import com.xcs.requestbribe.p2.request.RequestListgetByKeywordReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class RequestListDAOImpl extends RequestBribeExt implements RequestListDAO {

    private static final Logger log = LoggerFactory.getLogger(RequestListDAOImpl.class);

    @Override
    public List<RequestList> RequestListgetByKeyword(RequestListgetByKeywordReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append(" SELECT DISTINCT OPS_NOTICE.NOTICE_ID, "
                		+ "OPS_NOTICE.ARREST_ID AS NOTICE_ARREST_ID, "
                		+ "OPS_NOTICE.NOTICE_CODE, "
                		+ "OPS_NOTICE.NOTICE_DATE, "
                		+ "OPS_NOTICE.IS_ACTIVE AS NOTICE_IS_ACTIVE, "
                		+ "OPS_ARREST.ARREST_ID AS ARREST_ARREST_ID, "
                		+ "OPS_ARREST_INDICTMENT.INDICTMENT_ID AS ARREST_INDICTMENT_ID, "
//                		+ "OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID AS ARREST_INDICTMENT_DETAIL_ID, "
                		+ "OPS_ARREST.ARREST_CODE, OPS_ARREST.ARREST_DATE, "
                		+ "OPS_ARREST.OCCURRENCE_DATE, "
                		+ "CASE WHEN OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH IS NULL OR OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH = 'null' THEN OPS_ARREST_STAFF.TITLE_NAME_TH ||''|| OPS_ARREST_STAFF.FIRST_NAME ||' '|| OPS_ARREST_STAFF.LAST_NAME "
                		+ "		ELSE OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH ||''|| OPS_ARREST_STAFF.FIRST_NAME ||' '|| OPS_ARREST_STAFF.LAST_NAME END AS ARREST_STAFF_NAME, "
                		+ "OPS_ARREST_STAFF.OPREATION_POS_NAME AS ARREST_OPREATION_POS_NAME, "
                		+ "OPS_ARREST_STAFF.OPERATION_OFFICE_NAME AS ARREST_OPERATION_OFFICE_NAME, "
                		+ "OPS_ARREST_STAFF.OPERATION_OFFICE_SHORT_NAME AS ARREST_OPER_OFFICE_SHORT_NAME, "
                		+ "OPS_ARREST.OFFICE_NAME ARREST_OFFICE_NAME, "
                		+ "MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_TH, "
                		+ "MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_EN, "
                		+ "MAS_DISTRICT.DISTRICT_NAME_TH, "
                		+ "MAS_DISTRICT.DISTRICT_NAME_EN, "
                		+ "MAS_PROVINCE.PROVINCE_NAME_TH, "
                		+ "MAS_PROVINCE.PROVINCE_NAME_EN, "
                		+ "OPS_ARREST.IS_ACTIVE AS ARREST_IS_ACTIVE, "
                		+ "OPS_LAWSUIT.LAWSUIT_ID AS LAWSUIT_LAWSUIT_ID, "
                		+ "OPS_LAWSUIT.INDICTMENT_ID AS LAWSUIT_INDICTMENT_ID, "
//                		+ "OPS_LAWSUIT_DETAIL.LAWSUIT_DETAIL_ID, "
//                		+ "OPS_LAWSUIT_DETAIL.INDICTMENT_DETAIL_ID AS LAWSUIT_INDICTMENT_DETAIL_ID, "
                		+ "OPS_LAWSUIT.IS_OUTSIDE LAWSUIT_IS_OUTSIDE, "
                		+ "CASE WHEN OPS_LAWSUIT.IS_OUTSIDE = '1' THEN 'น. ' END || OPS_LAWSUIT.LAWSUIT_NO || "
                		+ "		CASE WHEN OPS_LAWSUIT.LAWSUIT_NO IS NOT NULL THEN '/' END || TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS LAWSUIT_NO, "
                		+ "TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR,'YYYY','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS LAWSUIT_NO_YEAR, "
                		+ "OPS_LAWSUIT.LAWSUIT_DATE, "
                		+ "CASE WHEN OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_TH IS NULL OR OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_TH = 'null' THEN OPS_LAWSUIT_STAFF.TITLE_NAME_TH ||''|| OPS_LAWSUIT_STAFF.FIRST_NAME ||' '|| OPS_LAWSUIT_STAFF.LAST_NAME "
                		+ "		ELSE OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_TH ||''|| OPS_LAWSUIT_STAFF.FIRST_NAME ||' '|| OPS_LAWSUIT_STAFF.LAST_NAME END AS LAWSUIT_STAF_NAME, "
                		+ "OPS_LAWSUIT_STAFF.OPREATION_POS_NAME AS LAWSUIT_OPREATION_POS_NAME, "
                		+ "OPS_LAWSUIT_STAFF.OPERATION_OFFICE_NAME AS LAWSUIT_OPERATION_OFFICE_NAME, "
                		+ "OPS_LAWSUIT_STAFF.OPERATION_OFFICE_SHORT_NAME AS LAWSUIT_OPER_OFFICE_SHORT_NAME, "
                		+ "OPS_LAWSUIT.OFFICE_NAME LAWSUIT_OFFICE_NAME, "
                		+ "OPS_LAWSUIT_DETAIL.LAWSUIT_TYPE, "
                		+ "OPS_LAWSUIT_DETAIL.LAWSUIT_END, "
                		+ "OPS_LAWSUIT.IS_ACTIVE AS LAWSUIT_IS_ACTIVE, "
                		+ "OPS_COMPARE.COMPARE_ID, "
                		+ "OPS_COMPARE.LAWSUIT_ID AS COMPARE_LAWSUIT_ID, "
                		+ "CASE WHEN OPS_COMPARE_STAFF.TITLE_SHORT_NAME_TH IS NULL OR OPS_COMPARE_STAFF.TITLE_SHORT_NAME_TH = 'null' THEN OPS_COMPARE_STAFF.TITLE_NAME_TH ||''|| OPS_COMPARE_STAFF.FIRST_NAME ||' '|| OPS_COMPARE_STAFF.LAST_NAME ELSE OPS_COMPARE_STAFF.TITLE_SHORT_NAME_TH ||''|| OPS_COMPARE_STAFF.FIRST_NAME ||' '|| OPS_COMPARE_STAFF.LAST_NAME END AS COMPARE_STAFF_NAME, "
                		+ "OPS_COMPARE_STAFF.OPREATION_POS_NAME AS COMPARE_OPREATION_POS_NAME, "
                		+ "OPS_COMPARE_STAFF.OPERATION_OFFICE_NAME AS COMPARE_OPERATION_OFFICE_NAME, "
                		+ "OPS_COMPARE_STAFF.OPERATION_OFFICE_SHORT_NAME AS COMPARE_OPER_OFFICE_SHORT_NAME, "
                		+ "OPS_COMPARE.IS_OUTSIDE COMPARE_IS_OUTSIDE, "
                		+ "CASE WHEN OPS_COMPARE.IS_OUTSIDE = '1' THEN 'น. ' END || OPS_COMPARE.COMPARE_NO || CASE WHEN OPS_COMPARE.COMPARE_NO IS NOT NULL THEN '/' END || TO_CHAR(OPS_COMPARE.COMPARE_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS COMPARE_NO, TO_CHAR(OPS_COMPARE.COMPARE_NO_YEAR,'YYYY','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS COMPARE_NO_YEAR, "
                		+ "OPS_COMPARE.COMPARE_DATE, "
                		+ "OPS_COMPARE.OFFICE_NAME COMPARE_OFFICE_NAME, "
                		+ "OPS_COMPARE.IS_ACTIVE AS COMPARE_IS_ACTIVE, "
                		+ "CASE WHEN OPS_REQUEST_BRIBE.BRIBE_CODE IS NULL AND OPS_REQUEST_REWARD.REWARD_CODE IS NULL THEN 'ยังไม่แบ่งเงิน' ELSE 'แบ่งเงินแล้ว' END AS BRIBE_REWARD_STATUS, "
                		+ "CASE WHEN OPS_REQUEST_BRIBE.IS_PAY =1 AND OPS_REQUEST_REWARD.IS_PAY =1 THEN 'จ่ายแล้ว' ELSE 'ยังไม่จ่าย' END AS BRIBE_REWARD_PAY, "
                		+ "OPS_REQUEST_BRIBE.BRIBE_ID, OPS_REQUEST_REWARD.REWARD_ID, OPS_REQUEST_BRIBE.BRIBE_CODE, OPS_REQUEST_REWARD.REWARD_CODE "
                		+ "FROM OPS_LAWSUIT "
                		+ "INNER JOIN OPS_LAWSUIT_STAFF ON OPS_LAWSUIT.LAWSUIT_ID = OPS_LAWSUIT_STAFF.LAWSUIT_ID "
                		+ "AND OPS_LAWSUIT_STAFF.CONTRIBUTOR_ID=16 "
                		+ "INNER JOIN OPS_ARREST_INDICTMENT ON OPS_LAWSUIT.INDICTMENT_ID = OPS_ARREST_INDICTMENT.INDICTMENT_ID "
                		+ "INNER JOIN OPS_ARREST_INDICTMENT_DETAIL ON OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID "
                		+ "AND OPS_ARREST_INDICTMENT_DETAIL.IS_ACTIVE = 1 "
                		+ "INNER JOIN OPS_LAWSUIT_DETAIL ON OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID = OPS_LAWSUIT_DETAIL.INDICTMENT_DETAIL_ID "
                		+ "AND OPS_LAWSUIT_DETAIL.IS_ACTIVE = 1 "
                		+ "INNER JOIN OPS_ARREST ON OPS_ARREST_INDICTMENT.ARREST_ID = OPS_ARREST.ARREST_ID "
                		+ "AND OPS_ARREST.IS_ACTIVE = 1 "
                		+ "LEFT JOIN OPS_NOTICE ON OPS_ARREST.ARREST_ID = OPS_NOTICE.ARREST_ID "
                		+ "INNER JOIN OPS_ARREST_STAFF ON OPS_ARREST.ARREST_ID = OPS_ARREST_STAFF.ARREST_ID "
                		+ "AND OPS_ARREST_STAFF.CONTRIBUTOR_ID=14 "
                		+ "INNER JOIN OPS_ARREST_LOCALE ON OPS_ARREST.ARREST_ID = OPS_ARREST_LOCALE.ARREST_ID "
                		+ "INNER JOIN MAS_SUB_DISTRICT ON OPS_ARREST_LOCALE.SUB_DISTRICT_ID = MAS_SUB_DISTRICT.SUB_DISTRICT_ID "
                		+ "INNER JOIN MAS_DISTRICT ON MAS_SUB_DISTRICT.DISTRICT_ID = MAS_DISTRICT.DISTRICT_ID "
                		+ "INNER JOIN MAS_PROVINCE ON MAS_DISTRICT.PROVINCE_ID = MAS_PROVINCE.PROVINCE_ID "
                		+ "INNER JOIN OPS_COMPARE ON OPS_LAWSUIT_DETAIL.LAWSUIT_ID = OPS_COMPARE.LAWSUIT_ID "
                		+ "INNER JOIN OPS_COMPARE_MAPPING ON OPS_COMPARE.COMPARE_ID = OPS_COMPARE_MAPPING.COMPARE_ID "
                		+ "INNER JOIN OPS_COMPARE_DETAIL ON OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID = OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID "
                		+ "INNER JOIN OPS_COMPARE_STAFF ON OPS_COMPARE.COMPARE_ID = OPS_COMPARE_STAFF.COMPARE_ID "
                		+ "AND OPS_COMPARE_STAFF.CONTRIBUTOR_ID =27 "
                		+ "LEFT JOIN OPS_REVENUE_DETAIL ON OPS_COMPARE_DETAIL.COMPARE_DETAIL_ID = OPS_REVENUE_DETAIL.COMPARE_DETAIL_ID "
                		+ "LEFT JOIN OPS_REVENUE ON OPS_REVENUE_DETAIL.REVENUE_ID=OPS_REVENUE.REVENUE_ID "
                		+ "LEFT JOIN OPS_REQUEST_BRIBE_REWARD ON OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_REQUEST_BRIBE_REWARD.INDICTMENT_ID "
                		+ "LEFT JOIN OPS_REQUEST_BRIBE ON OPS_REQUEST_BRIBE_REWARD.BRIBE_REWARD_ID = OPS_REQUEST_BRIBE.BRIBE_REWARD_ID "
                		+ "LEFT JOIN OPS_REQUEST_REWARD ON OPS_REQUEST_BRIBE_REWARD.BRIBE_REWARD_ID = OPS_REQUEST_REWARD.BRIBE_REWARD_ID "
                		+ "WHERE OPS_ARREST.IS_ACTIVE=1 AND "
                		+ "OPS_ARREST_STAFF.IS_ACTIVE = 1 "
                		+ "AND OPS_ARREST_INDICTMENT.IS_ACTIVE = 1 "
                		+ "AND OPS_LAWSUIT.IS_ACTIVE = 1 "
                		+ "AND OPS_COMPARE.IS_ACTIVE = 1 "
                		+ "AND OPS_REVENUE.REVENUE_STATUS = 2 ");

        if (req.getTEXT_SEARCH() != null && !"".equals(req.getTEXT_SEARCH())) {

            sqlBuilder.append(" AND ( ");
            sqlBuilder.append(" LOWER(OPS_NOTICE.NOTICE_CODE)LIKE LOWER(REPLACE ('%" + req.getTEXT_SEARCH() + "%',' ','')) ");
            sqlBuilder.append(" OR LOWER(OPS_ARREST.ARREST_CODE)LIKE LOWER(REPLACE ('%" + req.getTEXT_SEARCH() + "%',' ','')) ");
            sqlBuilder.append(" OR LOWER(OPS_ARREST.OFFICE_NAME) LIKE LOWER('%" + req.getTEXT_SEARCH() + "%') ");
            sqlBuilder.append(" OR LOWER(OPS_ARREST_STAFF.TITLE_NAME_TH||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      	OR LOWER(OPS_ARREST_STAFF.TITLE_NAME_EN||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      	OR LOWER(OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      	OR LOWER(OPS_ARREST_STAFF.TITLE_SHORT_NAME_EN||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))");
            sqlBuilder.append(" OR LOWER(OPS_LAWSUIT_STAFF.TITLE_NAME_TH||OPS_LAWSUIT_STAFF.FIRST_NAME||OPS_LAWSUIT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      	OR LOWER(OPS_LAWSUIT_STAFF.TITLE_NAME_EN||OPS_LAWSUIT_STAFF.FIRST_NAME||OPS_LAWSUIT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      	OR LOWER(OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_TH||OPS_LAWSUIT_STAFF.FIRST_NAME||OPS_LAWSUIT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      	OR LOWER(OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_EN||OPS_LAWSUIT_STAFF.FIRST_NAME||OPS_LAWSUIT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))");
            sqlBuilder.append(" OR LOWER(OPS_COMPARE_STAFF.TITLE_NAME_TH||OPS_COMPARE_STAFF.FIRST_NAME||OPS_COMPARE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      	OR LOWER(OPS_COMPARE_STAFF.TITLE_NAME_EN||OPS_COMPARE_STAFF.FIRST_NAME||OPS_COMPARE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      	OR LOWER(OPS_COMPARE_STAFF.TITLE_SHORT_NAME_TH||OPS_COMPARE_STAFF.FIRST_NAME||OPS_COMPARE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      	OR LOWER(OPS_COMPARE_STAFF.TITLE_SHORT_NAME_EN||OPS_COMPARE_STAFF.FIRST_NAME||OPS_COMPARE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))");
            sqlBuilder.append(" OR LOWER(MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_TH||MAS_DISTRICT.DISTRICT_NAME_TH||MAS_PROVINCE.PROVINCE_NAME_TH) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))");
            sqlBuilder.append(" ) ");

        }

        if (req.getACCOUNT_OFFICE_CODE() != null && !"".equals(req.getACCOUNT_OFFICE_CODE()) && req.getACCOUNT_OFFICE_CODE().length() == 6) {

            if (req.getACCOUNT_OFFICE_CODE().substring(2).equals("0000")) {

                sqlBuilder.append(" AND ( ");
                sqlBuilder.append(" SUBSTR(MAS_SUB_DISTRICT.OFFICE_CODE,1,2) = SUBSTR('" + req.getACCOUNT_OFFICE_CODE() + "',1,2)  ");
                sqlBuilder.append(" OR SUBSTR(OPS_LAWSUIT_STAFF.OPERATION_OFFICE_CODE,1,2) = SUBSTR('" + req.getACCOUNT_OFFICE_CODE() + "',1,2) ");
                sqlBuilder.append(" OR SUBSTR(OPS_LAWSUIT_STAFF.MANAGEMENT_OFFICE_CODE,1,2) = SUBSTR('" + req.getACCOUNT_OFFICE_CODE() + "',1,2) ");
                sqlBuilder.append(" OR SUBSTR(OPS_LAWSUIT_STAFF.REPRESENT_OFFICE_CODE,1,2) = SUBSTR('" + req.getACCOUNT_OFFICE_CODE() + "',1,2) ");
                sqlBuilder.append(" ) ");

            } else if (req.getACCOUNT_OFFICE_CODE().substring(4).equals("00")) {

                sqlBuilder.append(" AND ( ");
                sqlBuilder.append(" SUBSTR(MAS_SUB_DISTRICT.OFFICE_CODE,1,4) = SUBSTR('" + req.getACCOUNT_OFFICE_CODE() + "',1,4)  ");
                sqlBuilder.append(" OR SUBSTR(OPS_LAWSUIT_STAFF.OPERATION_OFFICE_CODE,1,4) = SUBSTR('" + req.getACCOUNT_OFFICE_CODE() + "',1,4) ");
                sqlBuilder.append(" OR SUBSTR(OPS_LAWSUIT_STAFF.MANAGEMENT_OFFICE_CODE,1,4) = SUBSTR('" + req.getACCOUNT_OFFICE_CODE() + "',1,4) ");
                sqlBuilder.append(" OR SUBSTR(OPS_LAWSUIT_STAFF.REPRESENT_OFFICE_CODE,1,4) = SUBSTR('" + req.getACCOUNT_OFFICE_CODE() + "',1,4) ");
                sqlBuilder.append(" ) ");

            } else {

                sqlBuilder.append(" AND ( ");
                sqlBuilder.append(" MAS_SUB_DISTRICT.OFFICE_CODE = '" + req.getACCOUNT_OFFICE_CODE() + "' ");
                sqlBuilder.append(" OR OPS_LAWSUIT_STAFF.OPERATION_OFFICE_CODE = '" + req.getACCOUNT_OFFICE_CODE() + "' ");
                sqlBuilder.append(" OR OPS_LAWSUIT_STAFF.MANAGEMENT_OFFICE_CODE = '" + req.getACCOUNT_OFFICE_CODE() + "' ");
                sqlBuilder.append(" OR OPS_LAWSUIT_STAFF.REPRESENT_OFFICE_CODE = '" + req.getACCOUNT_OFFICE_CODE() + "' ");
                sqlBuilder.append(" ) ");

            }

        }

        sqlBuilder.append(" ORDER BY OPS_ARREST.OCCURRENCE_DATE DESC ");

        log.info("[SQL RequestListgetByKeyword]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RequestList> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper<RequestList>() {

            public RequestList mapRow(ResultSet rs, int rowNum) throws SQLException {

                RequestList item = new RequestList();

                item.setNOTICE_ID(rs.getString("NOTICE_ID"));
                item.setNOTICE_ARREST_ID(rs.getString("NOTICE_ARREST_ID"));
                item.setNOTICE_CODE(rs.getString("NOTICE_CODE"));
                item.setNOTICE_DATE(rs.getString("NOTICE_DATE"));
                item.setNOTICE_IS_ACTIVE(rs.getString("NOTICE_IS_ACTIVE"));
                item.setARREST_ARREST_ID(rs.getString("ARREST_ARREST_ID"));
                item.setARREST_INDICTMENT_ID(rs.getString("ARREST_INDICTMENT_ID"));
//                item.setARREST_INDICTMENT_DETAIL_ID(rs.getString("ARREST_INDICTMENT_DETAIL_ID"));
                item.setARREST_CODE(rs.getString("ARREST_CODE"));
                item.setARREST_DATE(rs.getString("ARREST_DATE"));
                item.setOCCURRENCE_DATE(rs.getString("OCCURRENCE_DATE"));
                item.setARREST_STAFF_NAME(rs.getString("ARREST_STAFF_NAME"));
                item.setARREST_OPREATION_POS_NAME(rs.getString("ARREST_OPREATION_POS_NAME"));
                item.setARREST_OPERATION_OFFICE_NAME(rs.getString("ARREST_OPERATION_OFFICE_NAME"));
                item.setARREST_OPERATION_OFFICE_SHORT_NAME(rs.getString("ARREST_OPER_OFFICE_SHORT_NAME"));
                item.setARREST_OFFICE_NAME(rs.getString("ARREST_OFFICE_NAME"));
                item.setSUB_DISTRICT_NAME_TH(rs.getString("SUB_DISTRICT_NAME_TH"));
                item.setSUB_DISTRICT_NAME_EN(rs.getString("SUB_DISTRICT_NAME_EN"));
                item.setDISTRICT_NAME_TH(rs.getString("DISTRICT_NAME_TH"));
                item.setDISTRICT_NAME_EN(rs.getString("DISTRICT_NAME_EN"));
                item.setPROVINCE_NAME_TH(rs.getString("PROVINCE_NAME_TH"));
                item.setPROVINCE_NAME_EN(rs.getString("PROVINCE_NAME_EN"));
                item.setARREST_IS_ACTIVE(rs.getString("ARREST_IS_ACTIVE"));
                item.setLAWSUIT_LAWSUIT_ID(rs.getString("LAWSUIT_LAWSUIT_ID"));
//                item.setLAWSUIT_INDICTMENT_ID(rs.getString("LAWSUIT_INDICTMENT_ID"));
//                item.setLAWSUIT_DETAIL_ID(rs.getString("LAWSUIT_DETAIL_ID"));
//                item.setLAWSUIT_INDICTMENT_DETAIL_ID(rs.getString("LAWSUIT_INDICTMENT_DETAIL_ID"));
                item.setLAWSUIT_IS_OUTSIDE(rs.getString("LAWSUIT_IS_OUTSIDE"));
                item.setLAWSUIT_NO(rs.getString("LAWSUIT_NO"));
                item.setLAWSUIT_NO_YEAR(rs.getString("LAWSUIT_NO_YEAR"));
                item.setLAWSUIT_DATE(rs.getString("LAWSUIT_DATE"));
                item.setLAWSUIT_STAF_NAME(rs.getString("LAWSUIT_STAF_NAME"));
                item.setLAWSUIT_OPREATION_POS_NAME(rs.getString("LAWSUIT_OPREATION_POS_NAME"));
                item.setLAWSUIT_OPERATION_OFFICE_NAME(rs.getString("LAWSUIT_OPERATION_OFFICE_NAME"));
                item.setLAWSUIT_OPERATION_OFFICE_SHORT_NAME(rs.getString("LAWSUIT_OPER_OFFICE_SHORT_NAME"));
                item.setLAWSUIT_OFFICE_NAME(rs.getString("LAWSUIT_OFFICE_NAME"));
                item.setLAWSUIT_TYPE(rs.getString("LAWSUIT_TYPE"));
                item.setLAWSUIT_END(rs.getString("LAWSUIT_END"));
                item.setLAWSUIT_IS_ACTIVE(rs.getString("LAWSUIT_IS_ACTIVE"));
                item.setCOMPARE_ID(rs.getString("COMPARE_ID"));
                item.setCOMPARE_LAWSUIT_ID(rs.getString("COMPARE_LAWSUIT_ID"));
                item.setCOMPARE_STAFF_NAME(rs.getString("COMPARE_STAFF_NAME"));
                item.setCOMPARE_OPREATION_POS_NAME(rs.getString("COMPARE_OPREATION_POS_NAME"));
                item.setCOMPARE_OPERATION_OFFICE_NAME(rs.getString("COMPARE_OPERATION_OFFICE_NAME"));
                item.setCOMPARE_OPERATION_OFFICE_SHORT_NAME(rs.getString("COMPARE_OPER_OFFICE_SHORT_NAME"));
                item.setCOMPARE_IS_OUTSIDE(rs.getString("COMPARE_IS_OUTSIDE"));
                item.setCOMPARE_NO(rs.getString("COMPARE_NO"));
                item.setCOMPARE_NO_YEAR(rs.getString("COMPARE_NO_YEAR"));
                item.setCOMPARE_DATE(rs.getString("COMPARE_DATE"));
                item.setCOMPARE_OFFICE_NAME(rs.getString("COMPARE_OFFICE_NAME"));
                item.setCOMPARE_IS_ACTIVE(rs.getString("COMPARE_IS_ACTIVE"));
                item.setBRIBE_REWARD_STATUS(rs.getString("BRIBE_REWARD_STATUS"));
                item.setBRIBE_REWARD_PAY(rs.getString("BRIBE_REWARD_PAY"));

                item.setBRIBE_ID(rs.getString("BRIBE_ID"));
                item.setREWARD_ID(rs.getString("REWARD_ID"));
                item.setBRIBE_CODE(rs.getString("BRIBE_CODE"));
                item.setREWARD_CODE(rs.getString("REWARD_CODE"));

                return item;

            }

        });

        return dataList;

    }

    @Override
    public List<RequestListConAdv> RequestListgetByConAdv(RequestListgetByConAdvReq req) {

    	String tempNOTICE_DATE_FROM   = "";
		String tempNOTICE_DATE_TO = "";
		String tempARREST_DATE_FROM   = "";
		String tempARREST_DATE_TO = "";
		String tempLAWSUIT_DATE_FROM   = "";
		String tempLAWSUIT_DATE_TO = "";
		String tempCOMPARE_DATE_FROM = "";
		String tempCOMPARE_DATE_TO   = "";
		 
		 if(!"".equals(req.getNOTICE_DATE_FROM())) {
			 tempNOTICE_DATE_FROM = String.format("%s %s", req.getNOTICE_DATE_FROM(), Pattern.TIME_FROM);
		 }
		 if(!"".equals(req.getNOTICE_DATE_TO())) {
			 tempNOTICE_DATE_TO = String.format("%s %s", req.getNOTICE_DATE_TO(),Pattern.TIME_TO);
		 }
		 
		 if(!"".equals(req.getARREST_DATE_FROM())) {
			 tempARREST_DATE_FROM = String.format("%s %s", req.getARREST_DATE_FROM(), Pattern.TIME_FROM);
		 }
		 if(!"".equals(req.getARREST_DATE_TO())) {
			 tempARREST_DATE_TO = String.format("%s %s", req.getARREST_DATE_TO(),Pattern.TIME_TO);
		 }
		 
		 if(!"".equals(req.getLAWSUIT_DATE_FROM())) {
			 tempLAWSUIT_DATE_FROM = String.format("%s %s", req.getLAWSUIT_DATE_FROM(), Pattern.TIME_FROM);
		 }
		 if(!"".equals(req.getLAWSUIT_DATE_TO())) {
			 tempLAWSUIT_DATE_TO = String.format("%s %s", req.getLAWSUIT_DATE_TO(),Pattern.TIME_TO);
		 }
		 
		 if(!"".equals(req.getCOMPARE_DATE_FROM())) {
			 tempCOMPARE_DATE_FROM = String.format("%s %s", req.getCOMPARE_DATE_FROM(), Pattern.TIME_FROM);
		 }
		 if(!"".equals(req.getCOMPARE_DATE_TO())) {
			 tempCOMPARE_DATE_TO = String.format("%s %s", req.getCOMPARE_DATE_TO(),Pattern.TIME_TO);
		 }
		 
        StringBuilder sqlBuilder = new StringBuilder()
                .append(" SELECT DISTINCT OPS_NOTICE.NOTICE_ID, "
                		+ "OPS_NOTICE.ARREST_ID AS NOTICE_ARREST_ID, "
                		+ "OPS_NOTICE.NOTICE_CODE, OPS_NOTICE.NOTICE_DATE, "
                		+ "OPS_NOTICE.IS_ACTIVE AS NOTICE_IS_ACTIVE, "
                		+ "OPS_ARREST.ARREST_ID AS ARREST_ARREST_ID, "
                		+ "OPS_ARREST_INDICTMENT.INDICTMENT_ID AS ARREST_INDICTMENT_ID, "
//                		+ "OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID AS ARREST_INDICTMENT_DETAIL_ID, "
                		+ "OPS_ARREST.ARREST_CODE, "
                		+ "OPS_ARREST.ARREST_DATE, "
                		+ "OPS_ARREST.OCCURRENCE_DATE, "
                		+ "CASE WHEN OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH IS NULL OR OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH = 'null' THEN OPS_ARREST_STAFF.TITLE_NAME_TH ||''|| OPS_ARREST_STAFF.FIRST_NAME ||' '|| OPS_ARREST_STAFF.LAST_NAME ELSE OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH ||''|| OPS_ARREST_STAFF.FIRST_NAME ||' '|| OPS_ARREST_STAFF.LAST_NAME END AS ARREST_STAFF_NAME, "
                		+ "OPS_ARREST_STAFF.OPREATION_POS_NAME AS ARREST_OPREATION_POS_NAME, "
                		+ "OPS_ARREST_STAFF.OPERATION_OFFICE_NAME AS ARREST_OPERATION_OFFICE_NAME, "
                		+ "OPS_ARREST_STAFF.OPERATION_OFFICE_SHORT_NAME AS ARREST_OPER_OFFICE_SHORT_NAME, "
                		+ "OPS_ARREST.OFFICE_NAME ARREST_OFFICE_NAME, "
                		+ "MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_TH, MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_EN, "
                		+ "MAS_DISTRICT.DISTRICT_NAME_TH, "
                		+ "MAS_DISTRICT.DISTRICT_NAME_EN, "
                		+ "MAS_PROVINCE.PROVINCE_NAME_TH, "
                		+ "MAS_PROVINCE.PROVINCE_NAME_EN, "
                		+ "OPS_ARREST.IS_ACTIVE AS ARREST_IS_ACTIVE, "
                		+ "OPS_LAWSUIT.LAWSUIT_ID AS LAWSUIT_LAWSUIT_ID, "
                		+ "OPS_LAWSUIT.INDICTMENT_ID AS LAWSUIT_INDICTMENT_ID, "
//                		+ "OPS_LAWSUIT_DETAIL.LAWSUIT_DETAIL_ID, "
//                		+ "OPS_LAWSUIT_DETAIL.INDICTMENT_DETAIL_ID AS LAWSUIT_INDICTMENT_DETAIL_ID, "
                		+ "OPS_LAWSUIT.IS_OUTSIDE LAWSUIT_IS_OUTSIDE, CASE WHEN OPS_LAWSUIT.IS_OUTSIDE = '1' THEN 'น. ' END || OPS_LAWSUIT.LAWSUIT_NO || "
                		+ "		CASE WHEN OPS_LAWSUIT.LAWSUIT_NO IS NOT NULL THEN '/' END || TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS LAWSUIT_NO, "
                		+ "TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR,'YYYY','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS LAWSUIT_NO_YEAR, "
                		+ "OPS_LAWSUIT.LAWSUIT_DATE, "
                		+ "CASE WHEN OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_TH IS NULL OR OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_TH = 'null' THEN OPS_LAWSUIT_STAFF.TITLE_NAME_TH ||''|| OPS_LAWSUIT_STAFF.FIRST_NAME ||' '|| OPS_LAWSUIT_STAFF.LAST_NAME ELSE OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_TH ||''|| OPS_LAWSUIT_STAFF.FIRST_NAME ||' '|| OPS_LAWSUIT_STAFF.LAST_NAME END AS LAWSUIT_STAF_NAME, "
                		+ "OPS_LAWSUIT_STAFF.OPREATION_POS_NAME AS LAWSUIT_OPREATION_POS_NAME, "
                		+ "OPS_LAWSUIT_STAFF.OPERATION_OFFICE_NAME AS LAWSUIT_OPERATION_OFFICE_NAME, "
                		+ "OPS_LAWSUIT_STAFF.OPERATION_OFFICE_SHORT_NAME AS LAWSUIT_OPER_OFFICE_SHORT_NAME, "
                		+ "OPS_LAWSUIT.OFFICE_NAME LAWSUIT_OFFICE_NAME, OPS_LAWSUIT_DETAIL.LAWSUIT_TYPE, OPS_LAWSUIT_DETAIL.LAWSUIT_END, OPS_LAWSUIT.IS_ACTIVE AS LAWSUIT_IS_ACTIVE, OPS_COMPARE.COMPARE_ID, OPS_COMPARE.LAWSUIT_ID AS COMPARE_LAWSUIT_ID, "
                		+ "CASE WHEN OPS_COMPARE_STAFF.TITLE_SHORT_NAME_TH IS NULL OR OPS_COMPARE_STAFF.TITLE_SHORT_NAME_TH = 'null' THEN OPS_COMPARE_STAFF.TITLE_NAME_TH ||''|| OPS_COMPARE_STAFF.FIRST_NAME ||' '|| OPS_COMPARE_STAFF.LAST_NAME ELSE OPS_COMPARE_STAFF.TITLE_SHORT_NAME_TH ||''|| OPS_COMPARE_STAFF.FIRST_NAME ||' '|| OPS_COMPARE_STAFF.LAST_NAME END AS COMPARE_STAFF_NAME, "
                		+ "OPS_COMPARE_STAFF.OPREATION_POS_NAME AS COMPARE_OPREATION_POS_NAME, "
                		+ "OPS_COMPARE_STAFF.OPERATION_OFFICE_NAME AS COMPARE_OPERATION_OFFICE_NAME, "
                		+ "OPS_COMPARE_STAFF.OPERATION_OFFICE_SHORT_NAME AS COMPARE_OPER_OFFICE_SHORT_NAME, "
                		+ "OPS_COMPARE.IS_OUTSIDE COMPARE_IS_OUTSIDE, "
                		+ "CASE WHEN OPS_COMPARE.IS_OUTSIDE = '1' THEN 'น. ' END || OPS_COMPARE.COMPARE_NO || "
                		+ "		CASE WHEN OPS_COMPARE.COMPARE_NO IS NOT NULL THEN '/' END || TO_CHAR(OPS_COMPARE.COMPARE_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS COMPARE_NO, "
                		+ "TO_CHAR(OPS_COMPARE.COMPARE_NO_YEAR,'YYYY','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS COMPARE_NO_YEAR, "
                		+ "OPS_COMPARE.COMPARE_DATE, OPS_COMPARE.OFFICE_NAME COMPARE_OFFICE_NAME, "
                		+ "OPS_COMPARE.IS_ACTIVE AS COMPARE_IS_ACTIVE, "
                		+ "CASE WHEN OPS_REQUEST_BRIBE.BRIBE_CODE IS NULL AND OPS_REQUEST_REWARD.REWARD_CODE IS NULL THEN 'ยังไม่แบ่งเงิน' ELSE 'แบ่งเงินแล้ว' END AS BRIBE_REWARD_STATUS, "
                		+ "CASE WHEN OPS_REQUEST_BRIBE.IS_PAY =1 AND OPS_REQUEST_REWARD.IS_PAY =1 THEN 'จ่ายแล้ว' ELSE 'ยังไม่จ่าย' END AS BRIBE_REWARD_PAY, "
                		+ "OPS_REQUEST_BRIBE.BRIBE_ID, "
                		+ "OPS_REQUEST_REWARD.REWARD_ID, "
                		+ "OPS_REQUEST_BRIBE.BRIBE_CODE, "
                		+ "OPS_REQUEST_REWARD.REWARD_CODE FROM OPS_LAWSUIT "
                		+ "INNER JOIN OPS_LAWSUIT_STAFF ON OPS_LAWSUIT.LAWSUIT_ID = OPS_LAWSUIT_STAFF.LAWSUIT_ID AND OPS_LAWSUIT_STAFF.CONTRIBUTOR_ID=16 "
                		+ "INNER JOIN OPS_ARREST_INDICTMENT ON OPS_LAWSUIT.INDICTMENT_ID = OPS_ARREST_INDICTMENT.INDICTMENT_ID "
                		+ "INNER JOIN OPS_ARREST_INDICTMENT_DETAIL ON OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID AND OPS_ARREST_INDICTMENT_DETAIL.IS_ACTIVE = 1 "
                		+ "INNER JOIN OPS_LAWSUIT_DETAIL ON OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID = OPS_LAWSUIT_DETAIL.INDICTMENT_DETAIL_ID AND OPS_LAWSUIT_DETAIL.IS_ACTIVE = 1 "
                		+ "INNER JOIN OPS_ARREST ON OPS_ARREST_INDICTMENT.ARREST_ID = OPS_ARREST.ARREST_ID AND OPS_ARREST.IS_ACTIVE = 1 LEFT JOIN OPS_NOTICE ON OPS_ARREST.ARREST_ID = OPS_NOTICE.ARREST_ID "
                		+ "INNER JOIN OPS_ARREST_STAFF ON OPS_ARREST.ARREST_ID = OPS_ARREST_STAFF.ARREST_ID AND OPS_ARREST_STAFF.CONTRIBUTOR_ID=14 "
                		+ "INNER JOIN OPS_ARREST_LOCALE ON OPS_ARREST.ARREST_ID = OPS_ARREST_LOCALE.ARREST_ID "
                		+ "INNER JOIN MAS_SUB_DISTRICT ON OPS_ARREST_LOCALE.SUB_DISTRICT_ID = MAS_SUB_DISTRICT.SUB_DISTRICT_ID INNER JOIN MAS_DISTRICT ON MAS_SUB_DISTRICT.DISTRICT_ID = MAS_DISTRICT.DISTRICT_ID "
                		+ "INNER JOIN MAS_PROVINCE ON MAS_DISTRICT.PROVINCE_ID = MAS_PROVINCE.PROVINCE_ID "
                		+ "INNER JOIN OPS_COMPARE ON OPS_LAWSUIT_DETAIL.LAWSUIT_ID = OPS_COMPARE.LAWSUIT_ID INNER JOIN OPS_COMPARE_MAPPING ON OPS_COMPARE.COMPARE_ID = OPS_COMPARE_MAPPING.COMPARE_ID "
                		+ "INNER JOIN OPS_COMPARE_DETAIL ON OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID = OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID "
                		+ "INNER JOIN OPS_COMPARE_STAFF ON OPS_COMPARE.COMPARE_ID = OPS_COMPARE_STAFF.COMPARE_ID AND OPS_COMPARE_STAFF.CONTRIBUTOR_ID =27 "
                		+ "LEFT JOIN OPS_REVENUE_DETAIL ON OPS_COMPARE_DETAIL.COMPARE_DETAIL_ID = OPS_REVENUE_DETAIL.COMPARE_DETAIL_ID "
                		+ "LEFT JOIN OPS_REVENUE ON OPS_REVENUE_DETAIL.REVENUE_ID=OPS_REVENUE.REVENUE_ID "
                		+ "LEFT JOIN OPS_REQUEST_BRIBE_REWARD ON OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_REQUEST_BRIBE_REWARD.INDICTMENT_ID "
                		+ "LEFT JOIN OPS_REQUEST_BRIBE ON OPS_REQUEST_BRIBE_REWARD.BRIBE_REWARD_ID = OPS_REQUEST_BRIBE.BRIBE_REWARD_ID "
                		+ "LEFT JOIN OPS_REQUEST_REWARD ON OPS_REQUEST_BRIBE_REWARD.BRIBE_REWARD_ID = OPS_REQUEST_REWARD.BRIBE_REWARD_ID "
                		+ "WHERE OPS_ARREST.IS_ACTIVE=1 "
                		+ "AND OPS_ARREST_STAFF.IS_ACTIVE = 1 "
                		+ "AND OPS_ARREST_INDICTMENT.IS_ACTIVE = 1 "
                		+ "AND OPS_LAWSUIT.IS_ACTIVE = 1 "
                		+ "AND OPS_COMPARE.IS_ACTIVE = 1 "
                		+ "AND OPS_REVENUE.REVENUE_STATUS = 2 ");

        if (req.getNOTICE_CODE() != null && !"".equals(req.getNOTICE_CODE())) {

            sqlBuilder.append(" AND LOWER(OPS_NOTICE.NOTICE_CODE)LIKE LOWER(REPLACE('%" + req.getNOTICE_CODE() + "%',' ','')) ");

        }

        if (req.getNOTICE_DATE_FROM() != null && !"".equals(req.getNOTICE_DATE_FROM()) && req.getNOTICE_DATE_TO() != null && !"".equals(req.getNOTICE_DATE_TO())) {

            sqlBuilder.append(" AND OPS_NOTICE.NOTICE_DATE BETWEEN  to_date(nvl('"+tempNOTICE_DATE_FROM+"','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('"+tempNOTICE_DATE_TO+"','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI') ");

        }

        if (req.getARREST_CODE() != null && !"".equals(req.getARREST_CODE())) {

            sqlBuilder.append(" AND LOWER(OPS_ARREST.ARREST_CODE)LIKE LOWER(REPLACE ('%" + req.getARREST_CODE() + "%',' ','')) ");

        }

        if (req.getARREST_DATE_FROM() != null && !"".equals(req.getARREST_DATE_FROM()) && req.getARREST_DATE_TO() != null && !"".equals(req.getARREST_DATE_TO())) {

            sqlBuilder.append(" AND OPS_ARREST.OCCURRENCE_DATE BETWEEN TO_DATE(NVL('" +tempARREST_DATE_FROM + "','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') AND TO_DATE(NVL('" + tempARREST_DATE_TO + "','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI') ");

        }

        if (req.getOFFICE_NAME() != null && !"".equals(req.getOFFICE_NAME())) {

            sqlBuilder.append(" AND LOWER(MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_TH||MAS_DISTRICT.DISTRICT_NAME_TH||MAS_PROVINCE.PROVINCE_NAME_TH) LIKE LOWER(REPLACE('%"+req.getOFFICE_NAME()+"%',' ','')) ");

        }

        if (req.getARREST_STAFF() != null && !"".equals(req.getARREST_STAFF())) {

            sqlBuilder.append(" AND ( ");
            sqlBuilder.append(" LOWER(OPS_ARREST_STAFF.TITLE_NAME_TH||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getARREST_STAFF()+"%',' ',''))" +
						"      	OR LOWER(OPS_ARREST_STAFF.TITLE_NAME_EN||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getARREST_STAFF()+"%',' ',''))" +
						"      	OR LOWER(OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getARREST_STAFF()+"%',' ',''))" +
						"      	OR LOWER(OPS_ARREST_STAFF.TITLE_SHORT_NAME_EN||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getARREST_STAFF()+"%',' ','')) "); 
            sqlBuilder.append(" ) ");

        }

        if (req.getLAWSUIT_NO() != null && !"".equals(req.getLAWSUIT_NO())) {

            sqlBuilder.append(" AND OPS_LAWSUIT.LAWSUIT_NO = '" + req.getLAWSUIT_NO() + "' ");

        }

        if (req.getLAWSUIT_NO_YEAR() != null && !"".equals(req.getLAWSUIT_NO_YEAR())) {

            sqlBuilder.append(" AND (TO_NUMBER(TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) >= '" + req.getLAWSUIT_NO_YEAR() + "' AND TO_NUMBER(TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) <='" + req.getLAWSUIT_NO_YEAR() + "') ");

        }

        if (req.getIS_OUTSIDE() != null && !"".equals(req.getIS_OUTSIDE())) {

            sqlBuilder.append(" AND OPS_LAWSUIT.IS_OUTSIDE = '" + req.getIS_OUTSIDE() + "' ");

        }

        if (req.getLAWSUIT_DATE_FROM() != null && !"".equals(req.getLAWSUIT_DATE_FROM()) && req.getLAWSUIT_DATE_TO() != null && !"".equals(req.getLAWSUIT_DATE_TO())) {

            sqlBuilder.append(" AND OPS_LAWSUIT.LAWSUIT_DATE BETWEEN TO_DATE(NVL('" + tempLAWSUIT_DATE_FROM + "','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') AND TO_DATE(NVL('" + tempLAWSUIT_DATE_TO + "','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI') ");

        }

        if (req.getLAWSUIT_TYPE() != null && !"".equals(req.getLAWSUIT_TYPE())) {

            sqlBuilder.append(" AND OPS_LAWSUIT_DETAIL.LAWSUIT_TYPE = '" + req.getLAWSUIT_TYPE() + "' ");

        }

        if (req.getLAWSUIT_STAFF() != null && !"".equals(req.getLAWSUIT_STAFF())) {

            sqlBuilder.append(" AND ( ");
            sqlBuilder.append(" LOWER(OPS_LAWSUIT_STAFF.TITLE_NAME_TH||OPS_LAWSUIT_STAFF.FIRST_NAME||OPS_LAWSUIT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getLAWSUIT_STAFF()+"%',' ',''))" +
						"      	OR LOWER(OPS_LAWSUIT_STAFF.TITLE_NAME_EN||OPS_LAWSUIT_STAFF.FIRST_NAME||OPS_LAWSUIT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getLAWSUIT_STAFF()+"%',' ',''))" +
						"      	OR LOWER(OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_TH||OPS_LAWSUIT_STAFF.FIRST_NAME||OPS_LAWSUIT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getLAWSUIT_STAFF()+"%',' ',''))" +
						"      	OR LOWER(OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_EN||OPS_LAWSUIT_STAFF.FIRST_NAME||OPS_LAWSUIT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getLAWSUIT_STAFF()+"%',' ','')) ");
            sqlBuilder.append(" ) ");

        }

        if (req.getCOMPARE_NO() != null && !"".equals(req.getCOMPARE_NO())) {

            sqlBuilder.append(" AND OPS_COMPARE.COMPARE_NO = '" + req.getCOMPARE_NO() + "' ");

        }

        if (req.getCOMPARE_NO_YEAR() != null && !"".equals(req.getCOMPARE_NO_YEAR())) {

            sqlBuilder.append(" AND (TO_NUMBER(TO_CHAR(OPS_COMPARE.COMPARE_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) >= '" + req.getCOMPARE_NO_YEAR() + "' AND TO_NUMBER(TO_CHAR(OPS_COMPARE.COMPARE_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) <='" + req.getCOMPARE_NO_YEAR() + "') ");

        }

        if (req.getCOMPARE_DATE_FROM() != null && !"".equals(req.getCOMPARE_DATE_FROM()) && req.getCOMPARE_DATE_TO() != null && !"".equals(req.getCOMPARE_DATE_TO())) {

            sqlBuilder.append(" AND OPS_COMPARE.COMPARE_DATE BETWEEN TO_DATE(NVL('" + tempCOMPARE_DATE_FROM + "','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') AND TO_DATE(NVL('" + tempCOMPARE_DATE_TO + "','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI') ");

        }

        if (req.getCOMPARE_STAFF() != null && !"".equals(req.getCOMPARE_STAFF())) {

            sqlBuilder.append(" AND ( ");
            sqlBuilder.append(" LOWER(OPS_COMPARE_STAFF.TITLE_NAME_TH||OPS_COMPARE_STAFF.FIRST_NAME||OPS_COMPARE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getCOMPARE_STAFF()+"%',' ',''))" +
						"      	OR LOWER(OPS_COMPARE_STAFF.TITLE_NAME_EN||OPS_COMPARE_STAFF.FIRST_NAME||OPS_COMPARE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getCOMPARE_STAFF()+"%',' ',''))" +
						"      	OR LOWER(OPS_COMPARE_STAFF.TITLE_SHORT_NAME_TH||OPS_COMPARE_STAFF.FIRST_NAME||OPS_COMPARE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getCOMPARE_STAFF()+"%',' ',''))" +
						"      	OR LOWER(OPS_COMPARE_STAFF.TITLE_SHORT_NAME_EN||OPS_COMPARE_STAFF.FIRST_NAME||OPS_COMPARE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getCOMPARE_STAFF()+"%',' ','')) ");
            sqlBuilder.append(" ) ");

        }

        if (req.getBRIBE_REWARD_STATUS() != null && !"".equals(req.getBRIBE_REWARD_STATUS())) {

            if (req.getBRIBE_REWARD_STATUS().equals("0")) {

                sqlBuilder.append(" AND (OPS_REQUEST_BRIBE.BRIBE_CODE IS NULL OR OPS_REQUEST_REWARD.REWARD_CODE IS NULL) ");

            } else if (req.getBRIBE_REWARD_STATUS().equals("1")) {

                sqlBuilder.append(" AND (OPS_REQUEST_BRIBE.BRIBE_CODE IS NOT NULL OR OPS_REQUEST_REWARD.REWARD_CODE IS NOT NULL) ");

            }

        }

        if (req.getBRIBE_REWARD_PAY() != null && !"".equals(req.getBRIBE_REWARD_PAY())) {

            if (req.getBRIBE_REWARD_PAY().equals("0")) {

                sqlBuilder.append(" AND OPS_REQUEST_BRIBE.IS_PAY = '0' AND OPS_REQUEST_REWARD.IS_PAY = '0' ");

            } else if (req.getBRIBE_REWARD_PAY().equals("1")) {

                sqlBuilder.append(" AND OPS_REQUEST_BRIBE.IS_PAY = '1' AND OPS_REQUEST_REWARD.IS_PAY = '1' ");

            }

        }

        if (req.getACCOUNT_OFFICE_CODE() != null && !"".equals(req.getACCOUNT_OFFICE_CODE()) && req.getACCOUNT_OFFICE_CODE().length() == 6) {

            if (req.getACCOUNT_OFFICE_CODE().substring(2).equals("0000")) {

                sqlBuilder.append(" AND ( ");
                sqlBuilder.append(" SUBSTR(MAS_SUB_DISTRICT.OFFICE_CODE,1,2) = SUBSTR('" + req.getACCOUNT_OFFICE_CODE() + "',1,2)  ");
                sqlBuilder.append(" OR SUBSTR(OPS_LAWSUIT_STAFF.OPERATION_OFFICE_CODE,1,2) = SUBSTR('" + req.getACCOUNT_OFFICE_CODE() + "',1,2) ");
                sqlBuilder.append(" OR SUBSTR(OPS_LAWSUIT_STAFF.MANAGEMENT_OFFICE_CODE,1,2) = SUBSTR('" + req.getACCOUNT_OFFICE_CODE() + "',1,2) ");
                sqlBuilder.append(" OR SUBSTR(OPS_LAWSUIT_STAFF.REPRESENT_OFFICE_CODE,1,2) = SUBSTR('" + req.getACCOUNT_OFFICE_CODE() + "',1,2) ");
                sqlBuilder.append(" ) ");

            } else if (req.getACCOUNT_OFFICE_CODE().substring(4).equals("00")) {

                sqlBuilder.append(" AND ( ");
                sqlBuilder.append(" SUBSTR(MAS_SUB_DISTRICT.OFFICE_CODE,1,4) = SUBSTR('" + req.getACCOUNT_OFFICE_CODE() + "',1,4)  ");
                sqlBuilder.append(" OR SUBSTR(OPS_LAWSUIT_STAFF.OPERATION_OFFICE_CODE,1,4) = SUBSTR('" + req.getACCOUNT_OFFICE_CODE() + "',1,4) ");
                sqlBuilder.append(" OR SUBSTR(OPS_LAWSUIT_STAFF.MANAGEMENT_OFFICE_CODE,1,4) = SUBSTR('" + req.getACCOUNT_OFFICE_CODE() + "',1,4) ");
                sqlBuilder.append(" OR SUBSTR(OPS_LAWSUIT_STAFF.REPRESENT_OFFICE_CODE,1,4) = SUBSTR('" + req.getACCOUNT_OFFICE_CODE() + "',1,4) ");
                sqlBuilder.append(" ) ");

            } else {

                sqlBuilder.append(" AND ( ");
                sqlBuilder.append(" MAS_SUB_DISTRICT.OFFICE_CODE = '" + req.getACCOUNT_OFFICE_CODE() + "' ");
                sqlBuilder.append(" OR OPS_LAWSUIT_STAFF.OPERATION_OFFICE_CODE = '" + req.getACCOUNT_OFFICE_CODE() + "' ");
                sqlBuilder.append(" OR OPS_LAWSUIT_STAFF.MANAGEMENT_OFFICE_CODE = '" + req.getACCOUNT_OFFICE_CODE() + "' ");
                sqlBuilder.append(" OR OPS_LAWSUIT_STAFF.REPRESENT_OFFICE_CODE = '" + req.getACCOUNT_OFFICE_CODE() + "' ");
                sqlBuilder.append(" ) ");

            }

        }

        sqlBuilder.append(" ORDER BY OPS_ARREST.OCCURRENCE_DATE DESC ");

        log.info("[SQL RequestListgetByConAdv]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RequestListConAdv> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper<RequestListConAdv>() {

            public RequestListConAdv mapRow(ResultSet rs, int rowNum) throws SQLException {

                RequestListConAdv item = new RequestListConAdv();

                item.setNOTICE_ID(rs.getString("NOTICE_ID"));
                item.setNOTICE_ARREST_ID(rs.getString("NOTICE_ARREST_ID"));
                item.setNOTICE_CODE(rs.getString("NOTICE_CODE"));
                item.setNOTICE_DATE(rs.getString("NOTICE_DATE"));
                item.setNOTICE_IS_ACTIVE(rs.getString("NOTICE_IS_ACTIVE"));
                item.setARREST_ARREST_ID(rs.getString("ARREST_ARREST_ID"));
                item.setARREST_INDICTMENT_ID(rs.getString("ARREST_INDICTMENT_ID"));
//                item.setARREST_INDICTMENT_DETAIL_ID(rs.getString("ARREST_INDICTMENT_DETAIL_ID"));
                item.setARREST_CODE(rs.getString("ARREST_CODE"));
                item.setARREST_DATE(rs.getString("ARREST_DATE"));
                item.setOCCURRENCE_DATE(rs.getString("OCCURRENCE_DATE"));
                item.setARREST_STAFF_NAME(rs.getString("ARREST_STAFF_NAME"));
                item.setARREST_OPREATION_POS_NAME(rs.getString("ARREST_OPREATION_POS_NAME"));
                item.setARREST_OPERATION_OFFICE_NAME(rs.getString("ARREST_OPERATION_OFFICE_NAME"));
                item.setARREST_OPERATION_OFFICE_SHORT_NAME(rs.getString("ARREST_OPER_OFFICE_SHORT_NAME"));
                item.setARREST_OFFICE_NAME(rs.getString("ARREST_OFFICE_NAME"));
                item.setSUB_DISTRICT_NAME_TH(rs.getString("SUB_DISTRICT_NAME_TH"));
                item.setSUB_DISTRICT_NAME_EN(rs.getString("SUB_DISTRICT_NAME_EN"));
                item.setDISTRICT_NAME_TH(rs.getString("DISTRICT_NAME_TH"));
                item.setDISTRICT_NAME_EN(rs.getString("DISTRICT_NAME_EN"));
                item.setPROVINCE_NAME_TH(rs.getString("PROVINCE_NAME_TH"));
                item.setPROVINCE_NAME_EN(rs.getString("PROVINCE_NAME_EN"));
                item.setARREST_IS_ACTIVE(rs.getString("ARREST_IS_ACTIVE"));
                item.setLAWSUIT_LAWSUIT_ID(rs.getString("LAWSUIT_LAWSUIT_ID"));
//                item.setLAWSUIT_INDICTMENT_ID(rs.getString("LAWSUIT_INDICTMENT_ID"));
//                item.setLAWSUIT_DETAIL_ID(rs.getString("LAWSUIT_DETAIL_ID"));
//                item.setLAWSUIT_INDICTMENT_DETAIL_ID(rs.getString("LAWSUIT_INDICTMENT_DETAIL_ID"));
                item.setLAWSUIT_IS_OUTSIDE(rs.getString("LAWSUIT_IS_OUTSIDE"));
                item.setLAWSUIT_NO(rs.getString("LAWSUIT_NO"));
                item.setLAWSUIT_NO_YEAR(rs.getString("LAWSUIT_NO_YEAR"));
                item.setLAWSUIT_DATE(rs.getString("LAWSUIT_DATE"));
                item.setLAWSUIT_STAF_NAME(rs.getString("LAWSUIT_STAF_NAME"));
                item.setLAWSUIT_OPREATION_POS_NAME(rs.getString("LAWSUIT_OPREATION_POS_NAME"));
                item.setLAWSUIT_OPERATION_OFFICE_NAME(rs.getString("LAWSUIT_OPERATION_OFFICE_NAME"));
                item.setLAWSUIT_OPERATION_OFFICE_SHORT_NAME(rs.getString("LAWSUIT_OPER_OFFICE_SHORT_NAME"));
                item.setLAWSUIT_OFFICE_NAME(rs.getString("LAWSUIT_OFFICE_NAME"));
                item.setLAWSUIT_TYPE(rs.getString("LAWSUIT_TYPE"));
                item.setLAWSUIT_END(rs.getString("LAWSUIT_END"));
                item.setLAWSUIT_IS_ACTIVE(rs.getString("LAWSUIT_IS_ACTIVE"));
                item.setCOMPARE_ID(rs.getString("COMPARE_ID"));
                item.setCOMPARE_LAWSUIT_ID(rs.getString("COMPARE_LAWSUIT_ID"));
                item.setCOMPARE_STAFF_NAME(rs.getString("COMPARE_STAFF_NAME"));
                item.setCOMPARE_OPREATION_POS_NAME(rs.getString("COMPARE_OPREATION_POS_NAME"));
                item.setCOMPARE_OPERATION_OFFICE_NAME(rs.getString("COMPARE_OPERATION_OFFICE_NAME"));
                item.setCOMPARE_OPERATION_OFFICE_SHORT_NAME(rs.getString("COMPARE_OPER_OFFICE_SHORT_NAME"));
                item.setCOMPARE_IS_OUTSIDE(rs.getString("COMPARE_IS_OUTSIDE"));
                item.setCOMPARE_NO(rs.getString("COMPARE_NO"));
                item.setCOMPARE_NO_YEAR(rs.getString("COMPARE_NO_YEAR"));
                item.setCOMPARE_DATE(rs.getString("COMPARE_DATE"));
                item.setCOMPARE_OFFICE_NAME(rs.getString("COMPARE_OFFICE_NAME"));
                item.setCOMPARE_IS_ACTIVE(rs.getString("COMPARE_IS_ACTIVE"));
                item.setBRIBE_REWARD_STATUS(rs.getString("BRIBE_REWARD_STATUS"));
                item.setBRIBE_REWARD_PAY(rs.getString("BRIBE_REWARD_PAY"));

                item.setBRIBE_ID(rs.getString("BRIBE_ID"));
                item.setREWARD_ID(rs.getString("REWARD_ID"));
                item.setBRIBE_CODE(rs.getString("BRIBE_CODE"));
                item.setREWARD_CODE(rs.getString("REWARD_CODE"));

                return item;

            }

        });

        return dataList;

    }

}