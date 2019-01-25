package com.kt.psso.onm.chart;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Service;

import com.kt.psso.onm.eventstat.EventStatSearchVo;
import com.kt.psso.onm.scheduler.eventstat.EventStatVo;

@Service
public class ChartService {

	public CategoryDataset createDataset(List<EventStatVo> listEventStat) {
        String series1 = "Period";

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (EventStatVo eventStatVo : listEventStat) {
			dataset.addValue(eventStatVo.getCnt(), series1, eventStatVo.getCategory());
		}
		return dataset;
	}

	private JFreeChart createBarChart(CategoryDataset dataset, String chartTitle, String domainAxisLabel, String rangeAxisLabel) {
		// create the chart...
        final JFreeChart chart = ChartFactory.createBarChart(
            chartTitle,
            domainAxisLabel,
            rangeAxisLabel,
            dataset,
            PlotOrientation.VERTICAL, // orientation
            false,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.red);
        plot.setRangeGridlinePaint(Color.red);

        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        renderer.setSeriesPaint(0, new Color(0x00e42028));

        BarRenderer barRenderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
        barRenderer.setMaximumBarWidth(0.1);

        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        // OPTIONAL CUSTOMISATION COMPLETED.
        
        return chart;
	}

	public void writeBarChart(ServletOutputStream os, CategoryDataset dataset, EventStatSearchVo searchVo) throws IOException {
		String eventType = searchVo.getpEvent_type();
		String period	= searchVo.getpPeriod();
		String year		= searchVo.getpYear();
		String month	= searchVo.getpMonth();
		String day		= searchVo.getpDay();
		String year2	= searchVo.getpYear2();
		String month2	= searchVo.getpMonth2();
		String day2		= searchVo.getpDay2();
		
		String chartTitle = eventType + ", " + period + ", ";
		if ("MONTH".equals(period)) {
			chartTitle += year + "." + month + " - " + year2 + "." + month2;
		} else if ("DAY".equals(period)) {
			chartTitle += year + "." + month + "." + day + " - " + year2 + "." + month2 + "." + day2;
		} else if ("HOUR".equals(period)) {
			chartTitle += year + "." + month + "." + day + " - " + year2 + "." + month2 + "." + day2;
		}
		final JFreeChart chart = createBarChart(dataset, chartTitle, period, "Count");
		ChartUtilities.writeChartAsJPEG(os, chart, 800, 400);
	}

}
