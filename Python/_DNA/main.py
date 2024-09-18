import webview
import time
from flask import Flask, render_template
import threading

# 创建 Flask 应用
app = Flask(__name__)

# 路由到主页面
@app.route('/')
def index():
    return render_template('index.html')

# 启动 Flask 应用
def start_flask():
    app.run(port=5000)

# 创建并启动 Webview
def start_webview():
    # 等待 Flask 启动并监听端口
    time.sleep(1)
    # 创建窗口
    webview.create_window('DNA Preloader', 'http://127.0.0.1:5000')
    webview.start()

if __name__ == '__main__':
    # 启动 Flask 服务器线程
    flask_thread = threading.Thread(target=start_flask)
    flask_thread.start()
    # 启动 Webview 窗口
    start_webview()
