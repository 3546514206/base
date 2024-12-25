// worker.js

// 监听主线程发送的消息
onmessage = function (event) {
    const urls = event.data;

    // 模拟下载网页的函数
    function downloadPage(url) {
        // 这里可以使用适合异步操作的方法，比如fetch
        // 这里使用setTimeout模拟异步下载
        setTimeout(() => {
            const result = `Downloaded: ${url}`;
            // 将下载结果发送回主线程
            postMessage(result);
            // 模拟不同下载耗时
        }, Math.random() * 2000);
    }

    // 遍历所有网页链接，每个链接创建一个下载任务
    urls.forEach((url) => {
        downloadPage(url);
    });
};
