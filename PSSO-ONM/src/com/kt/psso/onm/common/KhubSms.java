package com.kt.psso.onm.common;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ktf.khub.kic.KicClient;
import com.ktf.khub.kic._ParamSet;


public class KhubSms
{
	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());
	
    public static final int    PACK_MAX_SIZE       = 100000;
    public static final int    BUFF_MIN_SIZE       = 256;
    
    public static final String OBJECT_AUTH_KEY     = "V8CF90A66A0BD1DD";      // KIC Server인증 Key 상용검증키V8CF90A66A0BD1DD  상용키 : S8CE6CDA5210A83E
    public static final String KIC_SERVER_IP       = "221.148.240.66";       	// KIC Server Ip Address 상용검증(221.148.240.66), 상용(221.148.240.27)
    public static final int    KIC_SERVER_PORTNO   = 18000;                   // KIC Server Port No
    
    //
    /**
	 * @uml.property  name="oBJECT_ID"
	 */
    public String OBJECT_ID = "";
    /**
	 * @uml.property  name="g3Ind"
	 */
    public String g3Ind = "";

    public int checkValidUser(String recvrTel)
    {
    	int idx;
        int iRtn;
        _ParamSet[] inSet    = new _ParamSet[5];
        _ParamSet[] outSet   = new _ParamSet[4];
        KicClient   KicApi   = new KicClient();
        
        OBJECT_ID = "AUTH_REQVALIDUSR";	// 정상사용자조회
        
        // 정상사용자 - OPCODE Name, Value 설정
        inSet[0] = new _ParamSet();
        inSet[0].strName  = "OPCODE";
        inSet[0].strValue = "001"; 
    
        // 정상사용자 - CALL_CTN Name, Value 설정
        inSet[1] = new _ParamSet();
        inSet[1].strName  = "CALL_CTN";
        inSet[1].strValue = recvrTel; // 016XXXXXXXX
    
        // 정상사용자 - SSN Name, Value 설정
        inSet[2] = new _ParamSet();
        inSet[2].strName  = "SSN";
        inSet[2].strValue = "XXXXXXXXXXXXX"; 
    
        // 정상사용자 - SSN_TYPE Name, Value 설정
        inSet[3] = new _ParamSet();
        inSet[3].strName  = "SSN_TYPE";
        inSet[3].strValue = "1"; 

        // KIC Server API 함수 호출 - KicClient.class
        iRtn = KicApi.Invoke_KICApi (OBJECT_ID, OBJECT_AUTH_KEY, KIC_SERVER_IP, KIC_SERVER_PORTNO,
                                     inSet, outSet);
    
        if (iRtn == 1) {
            // outSet정보 Display
            for (idx = 0; outSet[idx] != null; idx++) {
                LOG.info("[" + outSet[idx].strName + "] = " + outSet[idx].strValue);
            }
        } else {
            LOG.error("AUTH_REQVALIDUSR : 오류코드 : " + iRtn);
        }
        
        return iRtn;
    }
    
    public int checkReqUser(String senderTel)
    {
    	int idx;
        int iRtn;
        _ParamSet[] inSet    = new _ParamSet[3];
        _ParamSet[] outSet   = new _ParamSet[10];
        KicClient   KicApi   = new KicClient();
        
        OBJECT_ID = "AUTH_REQCUSTOMER";	// 정상사용자조회
        
        // 
        inSet[0] = new _ParamSet();
        inSet[0].strName  = "OPCODE";
        inSet[0].strValue = "023"; 
    
        // 
        inSet[1] = new _ParamSet();
        inSet[1].strName  = "CALL_CTN";
        inSet[1].strValue = senderTel;
    

        // KIC Server API 함수 호출 - KicClient.class
        iRtn = KicApi.Invoke_KICApi (OBJECT_ID, OBJECT_AUTH_KEY, KIC_SERVER_IP, KIC_SERVER_PORTNO,
                                     inSet, outSet);
    
        if (iRtn == 1) {
            // outSet정보 Display
            for (idx = 0; outSet[idx] != null; idx++) {
                LOG.info("[" + outSet[idx].strName + "] = " + outSet[idx].strValue);
            }
        } else {
            LOG.error("AUTH_REQCUSTOMER : 오류코드 : " + iRtn);
        }
        
        return iRtn;
    }
    
    public int getWcdmInfo(String recvrTel)
    {
    	int idx;
        int iRtn;
        _ParamSet[] inSet    = new _ParamSet[3];
        _ParamSet[] outSet   = new _ParamSet[12];
        KicClient   KicApi   = new KicClient();
        
    	OBJECT_ID = "AUTH_JOININFOWCDM";
    	g3Ind = "";
    	
    	// 
        inSet[0] = new _ParamSet();
        inSet[0].strName  = "OPCODE";
        inSet[0].strValue = "027"; 
    
        // 
        inSet[1] = new _ParamSet();
        inSet[1].strName  = "CALL_CTN";
        inSet[1].strValue = recvrTel; // 016XXXXXXXX

        // KIC Server API 함수 호출 - KicClient.class
        iRtn = KicApi.Invoke_KICApi (OBJECT_ID, OBJECT_AUTH_KEY, KIC_SERVER_IP, KIC_SERVER_PORTNO,
                                     inSet, outSet);
    
        if (iRtn == 1) {
        	LOG.info("AUTH_JOININFOWCDM : 실행 성공 : " + iRtn);
        } else {
            LOG.error("AUTH_JOININFOWCDM : 실행 오류코드 : " + iRtn);
        }
        
        // outSet정보 Check
        for (idx = 0; outSet[idx] != null; idx++) {
            LOG.info("[" + outSet[idx].strName + "] = " + outSet[idx].strValue);
            if(outSet[idx].strName == "G3_IND") {
            	if(outSet[idx].strValue == "3")
            		g3Ind = "01029000000"; // 3G
            	else
            		g3Ind = "01690000000"; // 2G
            }
            else if(outSet[idx].strName == "RT") {
            	if(outSet[idx].strValue == "SANTA1051") {
            		LOG.error("AUTH_JOININFOWCDM : RT : SANTA1051 (일시정지) 상태");
            		iRtn = -9998;
            	}
            }
            else if(outSet[idx].strName == "NCN") {
            	if(outSet[idx].strValue.isEmpty()) {
            		LOG.error("AUTH_JOININFOWCDM : NCN : Empty 상태");
            		iRtn = -9999;
            	}
            }
        }
        
    	return iRtn;
    }
    
    public int sendSmsMsgByKhub(String recvrTel, String message)
    {
    	int idx;
        int iRtn;
        _ParamSet[] inSet    = new _ParamSet[21];
        _ParamSet[] outSet   = new _ParamSet[4];
        KicClient   KicApi   = new KicClient();
        
        OBJECT_ID = "MESG_MTSMSGE";
        
        // 
        inSet[0] = new _ParamSet();
        inSet[0].strName  = "MSG_TYPE";
        inSet[0].strValue = "S001";
    
        // 
        inSet[1] = new _ParamSet();
        inSet[1].strName  = "CALLBACK_CTN";
        inSet[1].strValue = "";
    
        // 
        inSet[2] = new _ParamSet();
        inSet[2].strName  = "CALLBACK_URL";
        inSet[2].strValue = "";
    
        // 
        inSet[3] = new _ParamSet();
        inSet[3].strName  = "MSG_CONTENT";
        inSet[3].strValue = message;

        inSet[4] = new _ParamSet();
        inSet[4].strName  = "PFORM_TYPE";
        inSet[4].strValue = "1";

        inSet[5] = new _ParamSet();
        inSet[5].strName  = "SERVICE_TYPE";
        inSet[5].strValue = "F";

        inSet[6] = new _ParamSet();
        inSet[6].strName  = "CALL_CTN";
        inSet[6].strValue = recvrTel;

        inSet[7] = new _ParamSet();
        inSet[7].strName  = "RCV_CTN";
        inSet[7].strValue = recvrTel;

        inSet[8] = new _ParamSet();
        inSet[8].strName  = "BILL_URL";
        inSet[8].strValue = "";

        inSet[9] = new _ParamSet();
        inSet[9].strName  = "APP_ID";
        inSet[9].strValue = "";

        inSet[10] = new _ParamSet();
        inSet[10].strName  = "BILLING_TYPE";
        inSet[10].strValue = "";

        inSet[11] = new _ParamSet();
        inSet[11].strName  = "REPORT_URL";
        inSet[11].strValue = "";

        inSet[12] = new _ParamSet();
        inSet[12].strName  = "RECEIVE_PHONE_RD";
        inSet[12].strValue = g3Ind;

        inSet[13] = new _ParamSet();
        inSet[13].strName  = "ROUTE_IND";
        inSet[13].strValue = "0";

        inSet[14] = new _ParamSet();
        inSet[14].strName  = "DEST_IND";
        inSet[14].strValue = "1";

        inSet[15] = new _ParamSet();
        inSet[15].strName  = "NW_IND";
        inSet[15].strValue = "N";

        inSet[16] = new _ParamSet();
        inSet[16].strName  = "PREPAID";
        inSet[16].strValue = "3";

        inSet[17] = new _ParamSet();
        inSet[17].strName  = "INBH_REQ_SVCID";
        inSet[17].strValue = "";

        inSet[18] = new _ParamSet();
        inSet[18].strName  = "INBH_REQ_AMT";
        inSet[18].strValue = "";

        inSet[19] = new _ParamSet();
        inSet[19].strName  = "INBH_CHARGE_TYPE";
        inSet[19].strValue = "";

        // KIC Server API 함수 호출 - KicClient.class
        iRtn = KicApi.Invoke_KICApi (OBJECT_ID, OBJECT_AUTH_KEY, KIC_SERVER_IP, KIC_SERVER_PORTNO,
                                     inSet, outSet);
    
        if (iRtn == 1) {
            // outSet정보 Display
            for (idx = 0; outSet[idx] != null; idx++) {
                LOG.info("[" + outSet[idx].strName + "] = " + outSet[idx].strValue);
            }
        } else {
            LOG.error("MESG_MTSMSGE : 오류코드 : " + iRtn);
        }
    	
    	return iRtn;
    }
    
    public void sendSms(String recvrTel, String senderTel, String message)
    {
    	//if(checkValidUser(recvrTel) != 1) return;
        
        //if(checkReqUser(senderTel) != 1) return;
        
        if(getWcdmInfo(recvrTel) != 1) return;
        
        sendSmsMsgByKhub(recvrTel, message);
    }
    
}   // End of KhubSms