package com.kt.psso.onm.test;

import java.awt.Color;
import java.awt.GradientPaint;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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

public class ChartTest {

	public static void main(String[] args) {
		final CategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        
//      response.setContentType("image/jpeg"); 
//	    ServletOutputStream os = response.getOutputStream();
        
	    OutputStream os = null;
		try {
			os = new FileOutputStream("C:/Temp/1.jpg");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    try {
			ChartUtilities.writeChartAsJPEG(os, chart, 800, 400);
//			ChartUtilities.writeChartAsPNG(os, chart, 600, 400);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	private static JFreeChart createChart(CategoryDataset dataset) {
		// create the chart...
        final JFreeChart chart = ChartFactory.createBarChart(
            "2011-12-31",
            "HOUR",
            "Count",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            false,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        chart.setBackgroundPaint(new Color(0x00FFFFFF));

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
        // test
        renderer.setAutoPopulateSeriesOutlinePaint(false);
        renderer.setAutoPopulateSeriesOutlineStroke(false);
        renderer.setAutoPopulateSeriesStroke(false);
//        renderer.setBaseSeriesVisible(false);
        renderer.setMaximumBarWidth(0.5);
        renderer.setDefaultShadowsVisible(false);

        BarRenderer barRenderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
        barRenderer.setMaximumBarWidth(0.1);

        // set up gradient paints for series...
        final Color cp0 = new Color(0x00e42028);
        final GradientPaint gp0 = new GradientPaint(
            0.0f, 0.0f, new Color(0x00878787), 
            0.0f, 0.0f, new Color(0x00cccccc)
        );
        final GradientPaint gp1 = new GradientPaint(
            0.0f, 0.0f, Color.green, 
            0.0f, 0.0f, Color.white
        );
        final GradientPaint gp2 = new GradientPaint(
            0.0f, 0.0f, Color.red, 
            0.0f, 0.0f, Color.white
        );
        
        renderer.setSeriesPaint(0, cp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);

        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        // OPTIONAL CUSTOMISATION COMPLETED.
        
        return chart;
	}

	private static CategoryDataset createDataset() {
		// row keys...
        final String series1 = "HOUR";

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(20, series1, "01");
        dataset.addValue(30, series1, "02");
        dataset.addValue(60, series1, "03");
//        dataset.addValue(20, series1, "04");
//        dataset.addValue(90, series1, "05");
//        dataset.addValue(10, series1, "06");
//        dataset.addValue(10, series1, "07");
//        dataset.addValue(60, series1, "08");
//        dataset.addValue(30, series1, "09");
//        dataset.addValue(40, series1, "10");
//        dataset.addValue(20, series1, "11");
//        dataset.addValue(90, series1, "12");
//        dataset.addValue(20, series1, "13");
//        dataset.addValue(30, series1, "14");
//        dataset.addValue(10, series1, "15");
//        dataset.addValue(30, series1, "16");
//        dataset.addValue(80, series1, "17");
//        dataset.addValue(70, series1, "18");
//        dataset.addValue(60, series1, "19");
//        dataset.addValue(90, series1, "20");
//        dataset.addValue(40, series1, "21");
//        dataset.addValue(20, series1, "22");
//        dataset.addValue(20, series1, "23");
//        dataset.addValue(30, series1, "24");
//        dataset.addValue(60, series1, "25");
//        dataset.addValue(70, series1, "26");
//        dataset.addValue(20, series1, "27");
//        dataset.addValue(90, series1, "28");
//        dataset.addValue(40, series1, "29");
//        dataset.addValue(20, series1, "30");
//        dataset.addValue(20, series1, "31");

        return dataset;
	}
}
