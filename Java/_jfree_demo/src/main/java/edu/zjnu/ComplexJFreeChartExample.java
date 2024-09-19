package edu.zjnu;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * ComplexJFreeChartExample
 *
 * @Date 2024-09-19 17:58
 * @Author 杨海波
 **/
public class ComplexJFreeChartExample extends JFrame {

    private static final int DATA_POINTS = 100;
    private XYSeries series1;
    private XYSeries series2;
    private DefaultCategoryDataset barDataset;
    private DefaultPieDataset pieDataset;
    private Timer timer;

    public ComplexJFreeChartExample(String title) {
        super(title);

        // 初始化数据集
        series1 = new XYSeries("Series 1");
        series2 = new XYSeries("Series 2");
        for (int i = 0; i < DATA_POINTS; i++) {
            series1.add(i, Math.sin(i * 0.1) * 100);
            series2.add(i, Math.cos(i * 0.1) * 100);
        }

        XYSeriesCollection xyDataset = new XYSeriesCollection();
        xyDataset.addSeries(series1);
        xyDataset.addSeries(series2);

        barDataset = new DefaultCategoryDataset();
        for (int i = 0; i < 10; i++) {
            barDataset.addValue(new Random().nextInt(100), "Category 1", "Item " + i);
        }

        pieDataset = new DefaultPieDataset();
        for (int i = 0; i < 5; i++) {
            pieDataset.setValue("Slice " + i, new Random().nextInt(100));
        }

        // 创建图表
        JFreeChart lineChart = createLineChart(xyDataset);
        JFreeChart barChart = createBarChart(barDataset);
        JFreeChart pieChart = createPieChart(pieDataset);

        // 创建面板
        ChartPanel lineChartPanel = new ChartPanel(lineChart);
        lineChartPanel.setPreferredSize(new Dimension(600, 400));

        ChartPanel barChartPanel = new ChartPanel(barChart);
        barChartPanel.setPreferredSize(new Dimension(600, 400));

        ChartPanel pieChartPanel = new ChartPanel(pieChart);
        pieChartPanel.setPreferredSize(new Dimension(400, 400));

        // 添加到内容面板
        JPanel chartPanel = new JPanel();
        chartPanel.setLayout(new GridLayout(3, 1));
        chartPanel.add(lineChartPanel);
        chartPanel.add(barChartPanel);
        chartPanel.add(pieChartPanel);

        // 创建按钮面板
        JPanel buttonPanel = new JPanel();
        JButton updateButton = new JButton("更新图表");
        buttonPanel.add(updateButton);

        // 定时器更新数据
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateData();
            }
        });
        timer.start();

        // 按钮事件
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateData();
            }
        });

        // 设置布局
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(chartPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ComplexJFreeChartExample("综合性的 JFreeChart 图表"));
    }

    private JFreeChart createLineChart(XYSeriesCollection dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Line Chart Example",
                "X-Axis",
                "Y-Axis",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesPaint(1, Color.RED);
        plot.setRenderer(renderer);
        chart.setTitle(new TextTitle("Complex Line Chart", new Font("Serif", Font.BOLD, 18)));
        return chart;
    }

    private JFreeChart createBarChart(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Bar Chart Example",
                "Category",
                "Value",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
        renderer.setSeriesPaint(0, Color.GREEN);
        chart.setTitle(new TextTitle("Complex Bar Chart", new Font("Serif", Font.BOLD, 18)));
        return chart;
    }

    private JFreeChart createPieChart(DefaultPieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Pie Chart Example",
                dataset,
                true,
                true,
                false
        );
        PiePlot plot = (PiePlot) chart.getPlot();
        // plot.setSectionDepth(0.35);
        plot.setLabelBackgroundPaint(Color.white);
        plot.setLabelOutlinePaint(Color.black);
        chart.setTitle(new TextTitle("Complex Pie Chart", new Font("Serif", Font.BOLD, 18)));
        return chart;
    }

    private void updateData() {
        // 更新折线图数据
        series1.add(series1.getItemCount(), Math.sin(series1.getItemCount() * 0.1) * 100);
        series2.add(series2.getItemCount(), Math.cos(series2.getItemCount() * 0.1) * 100);

        // 更新柱状图数据
        barDataset.setValue(new Random().nextInt(100), "Category 1", "Item " + new Random().nextInt(10));

        // 更新饼图数据
        pieDataset.setValue("Slice " + new Random().nextInt(5), new Random().nextInt(100));
    }
}
