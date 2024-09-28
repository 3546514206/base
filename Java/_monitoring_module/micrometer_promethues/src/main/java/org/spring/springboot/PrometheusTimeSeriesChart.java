package org.spring.springboot;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * PrometheusTimeSeriesChart
 *
 * @Date 2024-09-20 15:59
 * @Author 杨海波
 **/
public class PrometheusTimeSeriesChart extends JFrame {
    private TimeSeries series;
    private OkHttpClient client;

    public PrometheusTimeSeriesChart(String title) {
        super(title);
        this.series = new TimeSeries("Requests Count");
        this.client = new OkHttpClient();

        // 创建时序图表
        TimeSeriesCollection dataset = new TimeSeriesCollection(series);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "",
                "时间",
                "请求量",
                dataset,
                true,
                true,
                false
        );

        // 自定义图表外观
        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true);
        plot.setRenderer(renderer);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);

        // 启动后台任务，每3秒钟更新一次数据
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::updateData, 0, 3, TimeUnit.SECONDS);
    }

    // 从 Prometheus 获取最新数据并更新 TimeSeries
    private void updateData() {
        String queryUrl = "http://localhost:9090/api/v1/query?query=requests_count_total"
                + "{instance=\"localhost:8089\",job=\"micrometer_prometheus\",endpoint=\"normal\"}&time=" + (System.currentTimeMillis() / 1000);

        Request request = new Request.Builder().url(queryUrl).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.err.println("Failed to fetch data from Prometheus");
                return;
            }

            // 解析 JSON 响应
            JsonObject jsonObject = JsonParser.parseString(response.body().string()).getAsJsonObject();
            JsonArray results = jsonObject.getAsJsonObject("data").getAsJsonArray("result");

            for (JsonElement result : results) {
                JsonArray values = result.getAsJsonObject().getAsJsonArray("value");
                // 转换为毫秒
                long timestamp = values.get(0).getAsLong() * 1000;
                double count = values.get(1).getAsDouble();

                // 更新 TimeSeries 数据
                series.addOrUpdate(new Millisecond(new java.util.Date(timestamp)), count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PrometheusTimeSeriesChart example = new PrometheusTimeSeriesChart("Prometheus 展示请求量统计");
            example.setSize(800, 600);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}
