import webview
import sys
from flask import Flask, render_template, request, redirect, url_for

app = Flask(__name__)

@app.route('/')
def login():
    return render_template('login.html')

@app.route('/login', methods=['POST'])
def do_login():
    username = request.form.get('username')
    password = request.form.get('password')
    if username and password:
        return redirect(url_for('main_page'))
    return render_template('login.html', error="请输入用户名和密码！")

@app.route('/main')
def main_page():
    return render_template('main.html')

if __name__ == '__main__':
    # 创建 PyWebView 窗口
    window = webview.create_window('SM Encryption Demo', 'http://127.0.0.1:5000/')

    # 监听关闭窗口事件
    def on_closing():
        print("窗口关闭")
        sys.exit()

    window.events.closing += on_closing

    # 启动 PyWebView 窗口和 Flask 应用
    webview.start(app.run, debug=True)
