package edu.zjnu.base.net.RabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * @author: 杨海波
 * @date: 2023-06-25 08:48:54
 * @description: todo
 */
public class Consumer {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println("Waiting for messages...");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println("Received message: " + message);
            };

            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});
            Thread.sleep(2000); // 等待消息接收
        }
    }
}
