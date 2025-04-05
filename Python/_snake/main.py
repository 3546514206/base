import webview
from flask import Flask, render_template

# 创建 Flask 应用
app = Flask(__name__)

@app.route('/')
def index():
    return render_template('snake.html')

if __name__ == '__main__':
    # 启动 Flask 应用并将其嵌入到 PyWebView 窗口中
    window = webview.create_window('贪吃蛇游戏', 'http://127.0.0.1:5000/')
    webview.start(app.run, debug=True)
