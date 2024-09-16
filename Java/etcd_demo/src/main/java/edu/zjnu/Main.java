package edu.zjnu;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.watch.WatchEvent;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;


/**
 * Main
 *
 * @Date 2024-09-14 16:08
 * @Author 杨海波
 **/
public class Main {
    private final Client client;
    private final KV kvClient;

    // 构造函数，初始化 Etcd 客户端
    public Main() {
        this.client = Client.builder().endpoints("http://localhost:2379").build();
        this.kvClient = client.getKVClient();
    }

    // 关闭客户端连接
    public void closeClient() {
        try {
            kvClient.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Put 操作，将键值对存入 Etcd
    public void putValue(String keyStr, String valueStr) throws Exception {
        ByteSequence key = ByteSequence.from(keyStr, StandardCharsets.UTF_8);
        ByteSequence value = ByteSequence.from(valueStr, StandardCharsets.UTF_8);
        kvClient.put(key, value).get();
        System.out.println("Put key: " + keyStr + ", value: " + valueStr);
    }

    // Get 操作，获取指定键的值
    public void getValue(String keyStr) throws Exception {
        ByteSequence key = ByteSequence.from(keyStr, StandardCharsets.UTF_8);
        CompletableFuture<GetResponse> responseFuture = kvClient.get(key);
        GetResponse response = responseFuture.get();
        response.getKvs().forEach(kv -> {
            String keyRetrieved = kv.getKey().toString(StandardCharsets.UTF_8);
            String valueRetrieved = kv.getValue().toString(StandardCharsets.UTF_8);
            System.out.println("Get key: " + keyRetrieved + ", value: " + valueRetrieved);
        });
    }

    // Delete 操作，删除指定键
    public void deleteValue(String keyStr) throws Exception {
        ByteSequence key = ByteSequence.from(keyStr, StandardCharsets.UTF_8);
        kvClient.delete(key).get();
        System.out.println("Deleted key: " + keyStr);
    }

    // Watch 操作，监听指定键的变更
    public void watchKey(String keyStr) {
        ByteSequence key = ByteSequence.from(keyStr, StandardCharsets.UTF_8);
        client.getWatchClient().watch(key, watchResponse -> {
            for (WatchEvent event : watchResponse.getEvents()) {
                String eventKey = event.getKeyValue().getKey().toString(StandardCharsets.UTF_8);
                String eventValue = event.getKeyValue().getValue().toString(StandardCharsets.UTF_8);
                System.out.println("Event Type: " + event.getEventType());
                System.out.println("Watched key: " + eventKey + ", value: " + eventValue);
            }
        });
        System.out.println("Watching key: " + keyStr);
    }

    // 主程序，执行所有演示操作
    public static void main(String[] args) {
        Main etcdOperations = new Main();

        try {
            // 1. Put 操作
            etcdOperations.putValue("foo", "bar");

            // 2. Get 操作
            etcdOperations.getValue("foo");

            // 3. Delete 操作
            // etcdOperations.deleteValue("foo");

            // 4. Watch 操作
            // etcdOperations.watchKey("foo");

            // 停止客户端
            etcdOperations.closeClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}