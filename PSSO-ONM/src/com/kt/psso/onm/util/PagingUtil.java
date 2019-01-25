package com.kt.psso.onm.util;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class PagingUtil {
	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());

	/**
	 * @uml.property  name="pageNo"
	 */
	private int pageNo;			// 현재 페이지
	/**
	 * @uml.property  name="pageCount"
	 */
	private int pageCount;		// 페이지당 rows
	
	/**
	 * @uml.property  name="count"
	 */
	private int count;			// 전체 rows
	/**
	 * @uml.property  name="blockCount"
	 */
	private int blockCount = 10;
	/**
	 * @uml.property  name="blockStart"
	 */
	private int blockStart;
	/**
	 * @uml.property  name="blockStop"
	 */
	private int blockStop;
	
	/**
	 * @uml.property  name="first"
	 */
	private String first;		// <<
	/**
	 * @uml.property  name="last"
	 */
	private String last;		// >>
	/**
	 * @uml.property  name="prev"
	 */
	private String prev;		// <
	/**
	 * @uml.property  name="next"
	 */
	private String next;		// >
	/**
	 * @uml.property  name="pages"
	 */
	private String[] pages;		// [1,2,3,4,5,6,7,8,9,10]

	/**
	 * @param count	전체 rows
	 * @param pn	현재 page
	 * @param pc	페이지당 rows
	 */
	public PagingUtil(int count, String pn, String pc) {
		this.count		= count;
		this.pageNo		= pn == null ? 1 : Integer.parseInt(pn);
		this.pageCount	= pc == null ? 20 : Integer.parseInt(pc);
		
		init();
	}

	private void init() {
		int block = (pageNo - 1) / blockCount; // block은 0에서 시작
		int lastPageNo = count / pageCount + (count % pageCount == 0 ? 0 : 1); 
		blockStart = block * blockCount + 1;
		blockStop = blockStart + blockCount - 1;
		blockStop = blockStop > lastPageNo ? lastPageNo : blockStop;
		
		first = pageNo != 1 ? "1" : null;
		last = pageNo != lastPageNo ? "" + lastPageNo : null; 
		prev = blockStart > 1 ? "" + (blockStart - 1) : null;
		next = blockStop < lastPageNo ? "" + (blockStop + 1) : null;
		pages = new String[blockStop - blockStart + 1];
		for (int i = 0; i < pages.length; i++) {
			pages[i] = "" + (blockStart + i);
		}
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("pagingUtil = " + toString());
		}
	}

	@Override
	public String toString() {
		return "PagingUtil [pageNo=" + pageNo + ", pageCount="
				+ pageCount + ", count=" + count + ", blockCount=" + blockCount
				+ ", blockStart=" + blockStart + ", blockStop=" + blockStop
				+ ", first=" + first + ", last=" + last + ", prev=" + prev
				+ ", next=" + next + ", pages=" + Arrays.toString(pages) + "]";
	}

	//-------------------- Getter & Setter --------------------//

	/**
	 * @return
	 * @uml.property  name="pageNo"
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * @return
	 * @uml.property  name="pageCount"
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * @return
	 * @uml.property  name="count"
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @return
	 * @uml.property  name="first"
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * @return
	 * @uml.property  name="last"
	 */
	public String getLast() {
		return last;
	}

	/**
	 * @return
	 * @uml.property  name="prev"
	 */
	public String getPrev() {
		return prev;
	}

	/**
	 * @return
	 * @uml.property  name="next"
	 */
	public String getNext() {
		return next;
	}

	/**
	 * @return
	 * @uml.property  name="pages"
	 */
	public String[] getPages() {
		return pages;
	}

}
