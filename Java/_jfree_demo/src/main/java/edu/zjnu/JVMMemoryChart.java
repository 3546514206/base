package edu.zjnu;


/**
 * JVMMemoryChart
 *
 * @Date 2024-09-19 14:24
 * @Author 杨海波
 **/

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class JVMMemoryChart extends JFrame {

    public JVMMemoryChart(String title) {
        super(title);

        // 创建数据集
        XYSeries memorySeries = new XYSeries("Memory Usage (MB)");
        memorySeries.add(512, 400);
        memorySeries.add(1024, 900);
        memorySeries.add(2048, 1800);
        memorySeries.add(4096, 3600);
        memorySeries.add(8192, 7200);

        XYSeries cpuSeries = new XYSeries("CPU Usage (%)");
        cpuSeries.add(512, 15);
        cpuSeries.add(1024, 35);
        cpuSeries.add(2048, 55);
        cpuSeries.add(4096, 75);
        cpuSeries.add(8192, 85);

        XYSeriesCollection memoryDataset = new XYSeriesCollection();
        memoryDataset.addSeries(memorySeries);

        XYSeriesCollection cpuDataset = new XYSeriesCollection();
        cpuDataset.addSeries(cpuSeries);

        // 创建图表对象
        JFreeChart chart = ChartFactory.createXYLineChart(
                "JVM Resource Consumption by Memory Size",
                "JVM Memory Size (MB)",
                "Memory Usage (MB)",
                memoryDataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        XYPlot plot = chart.getXYPlot();
        // 使用不同的 Y 轴为 CPU 使用率设置右轴
        NumberAxis secondaryAxis = new NumberAxis("CPU Usage (%)");
        plot.setRangeAxis(1, secondaryAxis);
        plot.setDataset(1, cpuDataset);
        plot.mapDatasetToRangeAxis(1, 1);

        // 设置渲染器
        XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer();
        renderer1.setSeriesPaint(0, Color.BLUE);
        plot.setRenderer(0, renderer1);

        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
        renderer2.setSeriesPaint(0, Color.RED);
        plot.setRenderer(1, renderer2);

        // 将图表添加到面板
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JVMMemoryChart example = new JVMMemoryChart("JVM 资源对比表");
            example.setSize(800, 600);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}
