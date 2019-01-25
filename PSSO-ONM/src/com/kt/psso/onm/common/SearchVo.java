package com.kt.psso.onm.common;

public class SearchVo {
	// paging
	/**
	 * @uml.property  name="pPageNo"
	 */
	private String pPageNo;
	/**
	 * @uml.property  name="pPageCount"
	 */
	private String pPageCount;
	// member
	/**
	 * @uml.property  name="pId"
	 */
	private String pId;
	/**
	 * @uml.property  name="pName"
	 */
	private String pName;
	/**
	 * @uml.property  name="pTel"
	 */
	private String pTel;
	// customer
	/**
	 * @uml.property  name="pCn"
	 */
	private String pCn;
	/**
	 * @uml.property  name="pBday"
	 */
	private String pBday;
	/**
	 * @uml.property  name="pRrn7"
	 */
	private String pRrn7;
	/**
	 * @uml.property  name="pDate"
	 */
	private String pDate;
	//private String pEncncid;
	// seceder
	/**
	 * @uml.property  name="pDateFlag"
	 */
	private String pDateFlag;
	/**
	 * @uml.property  name="pEncrrn"
	 */
	private String pEncrrn;
	// eventlog
	/**
	 * @uml.property  name="pEditorId"
	 */
	private String pEditorId;
	/**
	 * @uml.property  name="pUserId"
	 */
	private String pUserId;
	//chkNum
	/**
	 * @uml.property  name="pEmail"
	 */
	private String pEmail;
	/**
	 * @uml.property  name="pMobile"
	 */
	private String pMobile;
	
	public int getOffset() {
		int pageNo = (pPageNo == null || pPageNo.equals("")) ? 1 : Integer.parseInt(pPageNo);
		int pageCount = (pPageCount == null || pPageCount.equals("")) ? 20 : Integer.parseInt(pPageCount);
		return (pageNo - 1) * pageCount;
	}
	public int getLimit() {
		int pageCount = (pPageCount == null || pPageCount.equals("")) ? 20 : Integer.parseInt(pPageCount);
		return pageCount;
	}
	
	@Override
	public String toString() {
		return "SearchVo [pPageNo=" + pPageNo + ", pPageCount=" + pPageCount
				+ ", pId=" + pId + ", pName=" + pName + ", pTel=" + pTel
				+ ", pCn=" + pCn + ", pBday=" + pBday + ", pDateFlag="
				+ pDateFlag + ", pEncrrn=" + pEncrrn + ", pEditorId="
				+ pEditorId + ", pUserId=" + pUserId +  ", pDate=" +pDate +"]";
	}

	//-------------------- Getter & Setter --------------------//

	/**
	 * @return
	 * @uml.property  name="pPageNo"
	 */
	public String getpPageNo() {
		return pPageNo;
	}
	/**
	 * @param pPageNo
	 * @uml.property  name="pPageNo"
	 */
	public void setpPageNo(String pPageNo) {
		this.pPageNo = pPageNo;
	}
	/**
	 * @return
	 * @uml.property  name="pPageCount"
	 */
	public String getpPageCount() {
		return pPageCount;
	}
	/**
	 * @param pPageCount
	 * @uml.property  name="pPageCount"
	 */
	public void setpPageCount(String pPageCount) {
		this.pPageCount = pPageCount;
	}
	/**
	 * @return
	 * @uml.property  name="pId"
	 */
	public String getpId() {
		return pId;
	}
	/**
	 * @param pId
	 * @uml.property  name="pId"
	 */
	public void setpId(String pId) {
		this.pId = pId;
	}
	/**
	 * @return
	 * @uml.property  name="pName"
	 */
	public String getpName() {
		return pName;
	}
	/**
	 * @param pName
	 * @uml.property  name="pName"
	 */
	public void setpName(String pName) {
		this.pName = pName;
	}
	/**
	 * @return
	 * @uml.property  name="pTel"
	 */
	public String getpTel() {
		return pTel;
	}
	/**
	 * @param pTel
	 * @uml.property  name="pTel"
	 */
	public void setpTel(String pTel) {
		this.pTel = pTel;
	}
	/**
	 * @return
	 * @uml.property  name="pCn"
	 */
	public String getpCn() {
		return pCn;
	}
	/**
	 * @param pCn
	 * @uml.property  name="pCn"
	 */
	public void setpCn(String pCn) {
		this.pCn = pCn;
	}
	/**
	 * @return
	 * @uml.property  name="pBday"
	 */
	public String getpBday() {
		return pBday;
	}
	/**
	 * @param pBday
	 * @uml.property  name="pBday"
	 */
	public void setpBday(String pBday) {
		this.pBday = pBday;
	}
	/**
	 * @return
	 * @uml.property  name="pRrn7"
	 */
	public String getpRrn7() {
		return pRrn7;
	}
	/**
	 * @param pRrn7
	 * @uml.property  name="pRrn7"
	 */
	public void setpRrn7(String pRrn7) {
		this.pRrn7 = pRrn7;
	}
	/*public String getpEncncid() {
		return pEncncid;
	}
	public void setpEncncid(String pEncncid) {
		this.pEncncid = pEncncid;
	}*/
	/**
	 * @return
	 * @uml.property  name="pDateFlag"
	 */
	public String getpDateFlag() {
		return pDateFlag;
	}
	/**
	 * @param pDateFlag
	 * @uml.property  name="pDateFlag"
	 */
	public void setpDateFlag(String pDateFlag) {
		this.pDateFlag = pDateFlag;
	}
	/**
	 * @return
	 * @uml.property  name="pEncrrn"
	 */
	public String getpEncrrn() {
		return pEncrrn;
	}
	/**
	 * @param pEncrrn
	 * @uml.property  name="pEncrrn"
	 */
	public void setpEncrrn(String pEncrrn) {
		this.pEncrrn = pEncrrn;
	}
	/**
	 * @return
	 * @uml.property  name="pEditorId"
	 */
	public String getpEditorId() {
		return pEditorId;
	}
	/**
	 * @param pEditorId
	 * @uml.property  name="pEditorId"
	 */
	public void setpEditorId(String pEditorId) {
		this.pEditorId = pEditorId;
	}
	/**
	 * @return
	 * @uml.property  name="pUserId"
	 */
	public String getpUserId() {
		return pUserId;
	}
	/**
	 * @param pUserId
	 * @uml.property  name="pUserId"
	 */
	public void setpUserId(String pUserId) {
		this.pUserId = pUserId;
	}
	/**
	 * @return
	 * @uml.property  name="pEmail"
	 */
	public String getpEmail() {
		return pEmail;
	}
	/**
	 * @param pEmail
	 * @uml.property  name="pEmail"
	 */
	public void setpEmail(String pEmail) {
		this.pEmail = pEmail;
	}
	/**
	 * @return
	 * @uml.property  name="pMobile"
	 */
	public String getpMobile() {
		return pMobile;
	}
	/**
	 * @param pMobile
	 * @uml.property  name="pMobile"
	 */
	public void setpMobile(String pMobile) {
		this.pMobile = pMobile;
	}
	/**
	 * @return
	 * @uml.property  name="pDate"
	 */
	public String getpDate() {
		return pDate;
	}
	/**
	 * @param pDate
	 * @uml.property  name="pDate"
	 */
	public void setpDate(String pDate) {
		this.pDate = pDate;
	}
	
	
}
