import webview
import os


def main():
    # 获取当前文件的路径
    current_dir = os.path.dirname(os.path.abspath(__file__))
    html_file = os.path.join(current_dir, 'star_animation.html')

    # 创建 pywebview 窗口并加载 HTML 文件
    webview.create_window('星空动画', html_file)

    # 启动 pywebview
    webview.start()


# 入口点：当脚本直接运行时，调用 main()
if __name__ == "__main__":
    main()
