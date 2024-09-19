import webview
import os

# 创建 Webview 窗口
def create_window():
    # 确保正确的本地 HTML 文件路径
    html_file_path = os.path.abspath('index.html')
    window = webview.create_window("Snake Game", f"file://{html_file_path}")
    webview.start()

# 启动 Webview 窗口
if __name__ == '__main__':
    create_window()
