package com.kt.psso.onm.eventstat;

import com.kt.psso.onm.scheduler.eventstat.EventStatVo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.web.servlet.view.document.AbstractJExcelView;

public class EventStatExcel extends AbstractJExcelView
{
  protected void buildExcelDocument(Map<String, Object> model, WritableWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String fileName = createFileName();
    setFileNameToResponse(request, response, fileName);

    List listEventStat = (List)model.get("eventStatsVo");
    WritableSheet sheet = workbook.createSheet("PSSO_모니터링", 0);

    sheet.addCell(new Label(0, 0, "일자"));
    sheet.addCell(new Label(1, 0, "도메인"));
    sheet.addCell(new Label(2, 0, "건수"));

    int row = 1;
    for (Iterator iterator = listEventStat.iterator(); iterator.hasNext(); ) {
      EventStatVo eventStatVo = (EventStatVo)iterator.next();
      sheet.addCell(new Label(0, row, eventStatVo.getDay()));
      sheet.addCell(new Label(1, row, eventStatVo.getDomain()));
      sheet.addCell(new Number(2, row, eventStatVo.getCnt()));
      row++;
    }
  }

  private void setFileNameToResponse(HttpServletRequest request, HttpServletResponse response, String fileName)
  {
    String userAgent = request.getHeader("User-Agent");
    if (userAgent.indexOf("MSIE 5.5") >= 0) {
      response.setContentType("doesn/matter");
      response.setHeader("Content-Disposition", "filename=\"" + fileName + "\"");
    } else {
      response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
    }
  }

  private String createFileName() {
    SimpleDateFormat fileFormat = new SimpleDateFormat("yyyyMMdd");
    return "PSSO" + 
      "_" + fileFormat.format(new Date()) + ".xls";
  }
}