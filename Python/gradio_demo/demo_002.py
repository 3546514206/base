import gradio as gr

def chat(message, history):
    # 调用 LLM API 返回回复
    response = f"你说了：{message}"
    return response

demo = gr.ChatInterface(
    fn=chat,
    title="AI 聊天机器人",
    description="与 AI 助手对话"
)

# 启动应用
if __name__ == "__main__":
    demo.launch()