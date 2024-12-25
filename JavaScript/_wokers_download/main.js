// main.js

function initiateDownload() {
    const urls = ['https://www.baidu.com/', 'https://www.sogou.com/']; // 替换为实际的网页链接

    // 创建 Web Worker
    const worker = new Worker('worker.js');

    // 监听 Web Worker 的消息
    worker.onmessage = function (event) {
        console.log(event.data); // 打印下载结果
    };

    // 向 Web Worker 发送下载任务
    worker.postMessage(urls);
}

initiateDownload();
