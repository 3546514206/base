package edu.zjnu;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * @author: 杨海波
 * @date: 2023-12-01 14:29:28
 * @description: HttpRequestFrame
 */
public class HttpRequestFrame extends JFrame {
    private JTextField urlTextField;
    private JTextArea responseTextArea;

    public HttpRequestFrame() {
        setTitle("HTTP Request Tool");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        urlTextField = new JTextField();
        JButton sendButton = new JButton("Send Request");
        responseTextArea = new JTextArea();

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = urlTextField.getText();
                String jsonResponse = sendHttpRequest(url);
                responseTextArea.setText(jsonResponse);
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(urlTextField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(responseTextArea), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HttpRequestFrame().setVisible(true);
            }
        });
    }

    private String sendHttpRequest(String url) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()))) {
                    StringBuilder jsonResponse = new StringBuilder();
                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        jsonResponse.append(line);
                    }

                    return jsonResponse.toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Error occurred during HTTP request.";
    }
}
