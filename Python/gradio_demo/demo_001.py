import gradio as gr

def to_uppercase(text):
    return text.upper()

# 创建 Gradio 接口
demo = gr.Interface(
    fn=to_uppercase,               # 调用的函数
    inputs=gr.Textbox(label="输入文本"),   # 输入组件
    outputs=gr.Textbox(label="输出结果"),  # 输出组件
    title="文本转大写工具",
    description="请输入任意文本，点击提交后将转换为大写。"
)

# 启动应用
if __name__ == "__main__":
    demo.launch()