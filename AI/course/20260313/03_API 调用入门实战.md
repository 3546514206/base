# 任务 3：API 调用入门实战

**学习时间：** 4-5 小时
**学习目标：** 能独立调用 API 完成实际任务

---

## 第一部分：API 基础概念（30 分钟）

### 什么是 API？

**通俗比喻：**
> API 就像餐厅的服务员
> - 你（程序员）→ 点菜（发送请求）→ 服务员（API）→ 厨房（AI 模型）
> - 厨房做好菜 → 服务员（API）→ 端给你（返回结果）

**技术定义：**
```
API = Application Programming Interface（应用程序编程接口）

作用：让你的程序能和 AI 模型"对话"
```

### API 调用流程

```
┌─────────────┐     ┌─────────────┐     ┌─────────────┐
│   你的程序   │ ──→ │   API 接口   │ ──→ │  AI 模型    │
│  (Python)   │     │  (HTTP)     │     │ (Qwen/GPT)  │
└─────────────┘     └─────────────┘     └─────────────┘
       ↑                                       │
       │              返回结果                  │
       └───────────────────────────────────────┘
```

### API 调用基本要素

| 要素 | 说明 | 例子 |
|------|------|------|
| Endpoint | API 地址 | https://dashscope.aliyuncs.com/api/v1/... |
| Method | 请求方法 | POST |
| Headers | 请求头 | Authorization: Bearer YOUR_API_KEY |
| Body | 请求体 | {"prompt": "你好", "model": "qwen-plus"} |
| Response | 响应 | {"text": "你好！有什么可以帮你的？"} |

---

## 第二部分：通义千问 API 调用（1.5 小时）

### 准备工作

**获取 API Key：**
1. 访问 https://dashscope.console.aliyun.com/
2. 登录阿里云账号
3. 进入"API-KEY 管理"
4. 创建并复制 API Key

**安装 SDK：**
```bash
conda activate ai-learning
pip install dashscope
```

### 方法 1：使用官方 SDK（推荐）

**基础示例：**

```python
import dashscope
from dashscope import Generation

# 设置 API Key
dashscope.api_key = "sk-你的 API Key"

def call_qwen(prompt):
    """调用通义千问模型"""
    response = Generation.call(
        model="qwen-plus",  # 模型名称
        prompt=prompt       # 你的问题/指令
    )
    
    # 检查是否成功
    if response.status_code == 200:
        return response.output.text
    else:
        return f"错误：{response.code} - {response.message}"

# 测试
result = call_qwen("你好，请介绍一下你自己")
print(result)
```

**运行测试：**
```bash
python test_qwen.py
```

**预期输出：**
```
你好！我是通义千问，是阿里巴巴集团旗下的通义实验室自主研发的超大规模语言模型...
```

### 方法 2：使用 HTTP 请求（理解原理）

```python
import requests
import json

# API 配置
API_KEY = "sk-你的 API Key"
URL = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation"

# 请求头
headers = {
    "Authorization": f"Bearer {API_KEY}",
    "Content-Type": "application/json"
}

# 请求体
data = {
    "model": "qwen-plus",
    "input": {
        "prompt": "你好，请介绍一下你自己"
    }
}

# 发送请求
response = requests.post(URL, headers=headers, json=data)

# 解析结果
if response.status_code == 200:
    result = response.json()
    print(result["output"]["text"])
else:
    print(f"错误：{response.status_code}")
    print(response.text)
```

### 常用参数说明

```python
response = Generation.call(
    model="qwen-plus",      # 模型：qwen-plus / qwen-turbo / qwen-max
    prompt="你的问题",       # 输入内容
    temperature=0.7,        # 温度：0-1，越高越随机
    max_tokens=2000,        # 最大输出长度
    top_p=0.8,             # 核采样参数
    seed=42                # 随机种子（固定结果用）
)
```

### 模型选择指南

| 模型 | 特点 | 适用场景 | 价格 |
|------|------|---------|------|
| qwen-turbo | 速度快，成本低 | 简单问答、大量调用 | 便宜 |
| qwen-plus | 平衡性能和成本 | 日常使用、代码生成 | 中等 |
| qwen-max | 最强能力 | 复杂推理、专业任务 | 较贵 |

**建议：** 学习阶段用 qwen-plus，性价比高

---

## 第三部分：ChatGPT API 调用（30 分钟）

### 准备工作

**获取 API Key：**
1. 访问 https://platform.openai.com/
2. 注册并登录
3. 进入 API Keys 页面
4. 创建新的 API Key

**安装 SDK：**
```bash
pip install openai
```

### 基础示例

```python
from openai import OpenAI

# 初始化客户端
client = OpenAI(api_key="sk-你的 OpenAI API Key")

def call_gpt(prompt):
    """调用 GPT 模型"""
    response = client.chat.completions.create(
        model="gpt-3.5-turbo",  # 或 gpt-4
        messages=[
            {"role": "user", "content": prompt}
        ]
    )
    
    return response.choices[0].message.content

# 测试
result = call_gpt("你好，请介绍一下你自己")
print(result)
```

### 多轮对话示例

```python
from openai import OpenAI

client = OpenAI(api_key="sk-你的 API Key")

# 保存对话历史
messages = [
    {"role": "system", "content": "你是一个有帮助的助手"},
    {"role": "user", "content": "你好"},
    {"role": "assistant", "content": "你好！有什么可以帮你的？"},
    {"role": "user", "content": "今天天气怎么样？"}
]

response = client.chat.completions.create(
    model="gpt-3.5-turbo",
    messages=messages
)

print(response.choices[0].message.content)
```

---

## 第四部分：实战项目（2-3 小时）

### 项目 1：智能问答机器人 ⭐ 入门必做

**功能：** 命令行对话机器人

**代码：**
```python
import dashscope
from dashscope import Generation

# 配置
dashscope.api_key = "sk-你的 API Key"

def call_qwen(prompt, history=None):
    """调用通义千问"""
    if history is None:
        history = []
    
    # 构建消息列表
    messages = [{"role": "user", "content": prompt}]
    
    response = Generation.call(
        model="qwen-plus",
        messages=messages
    )
    
    if response.status_code == 200:
        return response.output.text
    else:
        return f"出错了：{response.message}"

def chat_bot():
    """问答机器人主程序"""
    print("=" * 50)
    print("🤖 欢迎使用智能问答机器人")
    print("输入 '退出' 或 'quit' 结束对话")
    print("=" * 50)
    print()
    
    while True:
        # 获取用户输入
        user_input = input("你：").strip()
        
        # 检查是否退出
        if user_input.lower() in ["退出", "quit", "exit"]:
            print("\n🤖 机器人：再见！有任何问题随时找我～")
            break
        
        # 跳过空输入
        if not user_input:
            continue
        
        # 调用 AI
        print("\n🤖 机器人：", end="", flush=True)
        response = call_qwen(user_input)
        print(response)
        print()

if __name__ == "__main__":
    chat_bot()
```

**运行：**
```bash
python chat_bot.py
```

**效果：**
```
==================================================
🤖 欢迎使用智能问答机器人
输入 '退出' 或 'quit' 结束对话
==================================================

你：Python 怎么读取文件？

🤖 机器人：在 Python 中读取文件有多种方法，最常用的是使用 open() 函数...

你：谢谢！

🤖 机器人：不客气！还有其他问题吗？

你：退出

🤖 机器人：再见！有任何问题随时找我～
```

---

### 项目 2：文本摘要工具 💼 实用

**功能：** 自动总结长文本

**代码：**
```python
import dashscope
from dashscope import Generation

dashscope.api_key = "sk-你的 API Key"

def summarize_text(text, max_length=200):
    """
    对长文本进行摘要
    
    参数：
        text: 原始文本
        max_length: 摘要最大长度
    """
    prompt = f"""
请对以下文本进行摘要：

要求：
1. 保留核心信息
2. 长度控制在{max_length}字以内
3. 语言简洁流畅
4. 用中文输出

原文：
{text}
"""
    
    response = Generation.call(
        model="qwen-plus",
        prompt=prompt,
        max_tokens=max_length
    )
    
    if response.status_code == 200:
        return response.output.text
    else:
        return f"错误：{response.message}"

# 测试
if __name__ == "__main__":
    sample_text = """
    人工智能是计算机科学的一个分支，它试图理解智能的本质，
    并生产出一种新的能以人类智能相似的方式做出反应的智能机器。
    该领域的研究包括机器人、语言识别、图像识别、自然语言处理
    和专家系统等。人工智能从 1956 年达特茅斯会议提出至今，
    经历了多次发展高潮和低谷。2010 年后，随着深度学习的突破，
    人工智能进入了快速发展期。2022 年 ChatGPT 的发布更是让
    人工智能进入了大众视野，引发了全球范围内的 AI 热潮。
    """
    
    print("原文：")
    print(sample_text)
    print("\n" + "=" * 50 + "\n")
    
    summary = summarize_text(sample_text)
    print("摘要：")
    print(summary)
```

**运行：**
```bash
python summarize.py
```

---

### 项目 3：代码助手 🚀 程序员必备

**功能：** 根据需求生成代码

**代码：**
```python
import dashscope
from dashscope import Generation

dashscope.api_key = "sk-你的 API Key"

def code_helper(task, language="Python"):
    """
    根据需求生成代码
    
    参数：
        task: 编程任务描述
        language: 编程语言
    """
    prompt = f"""
你是一位专业的{language}程序员，有 10 年开发经验。

请根据以下需求编写代码：

需求：{task}

要求：
1. 代码完整可运行
2. 添加必要的注释
3. 遵循{language}最佳实践
4. 如果有输入输出示例更好

请直接输出代码，不需要过多解释：
"""
    
    response = Generation.call(
        model="qwen-plus",
        prompt=prompt,
        max_tokens=2000
    )
    
    if response.status_code == 200:
        return response.output.text
    else:
        return f"错误：{response.message}"

# 测试
if __name__ == "__main__":
    # 示例 1：写一个函数
    task1 = "写一个函数，计算列表中所有数字的平均值"
    print("任务 1：" + task1)
    print("\n代码：")
    print(code_helper(task1))
    print("\n" + "=" * 50 + "\n")
    
    # 示例 2：处理文件
    task2 = "读取 CSV 文件，统计某一列的平均值"
    print("任务 2：" + task2)
    print("\n代码：")
    print(code_helper(task2))
```

**运行：**
```bash
python code_helper.py
```

---

### 项目 4：Prompt 技巧练习 📝 进阶

**功能：** 练习不同的 Prompt 技巧

**代码：**
```python
import dashscope
from dashscope import Generation

dashscope.api_key = "sk-你的 API Key"

def call_ai(prompt):
    """调用 AI"""
    response = Generation.call(
        model="qwen-plus",
        prompt=prompt
    )
    if response.status_code == 200:
        return response.output.text
    return f"错误：{response.message}"

# 技巧 1：清晰指令
print("=" * 50)
print("技巧 1：清晰指令")
print("=" * 50)

prompt1 = """
用 Python 写一个函数，要求：
- 函数名：find_max
- 输入：一个数字列表
- 输出：列表中的最大值
- 不能用内置的 max() 函数
- 添加注释说明算法思路
"""
print(call_ai(prompt1))

# 技巧 2：提供示例（Few-shot）
print("\n" + "=" * 50)
print("技巧 2：提供示例")
print("=" * 50)

prompt2 = """
请按照以下格式转换日期：

示例：
输入：2024-01-15 → 输出：2024 年 1 月 15 日
输入：2024-03-20 → 输出：2024 年 3 月 20 日
输入：2024-06-01 → 输出：2024 年 6 月 1 日

现在请转换：
输入：2026-03-13 → 输出：
"""
print(call_ai(prompt2))

# 技巧 3：分步思考（Chain of Thought）
print("\n" + "=" * 50)
print("技巧 3：分步思考")
print("=" * 50)

prompt3 = """
请逐步思考以下问题：

1. 首先理解问题是什么
2. 然后列出已知条件
3. 接着分析解题思路
4. 最后给出答案和验证

问题：
一个水池，单独开进水口 3 小时可以注满，
单独开出水口 5 小时可以放空。
如果同时打开进水口和出水口，多久能注满？
"""
print(call_ai(prompt3))

# 技巧 4：设定角色
print("\n" + "=" * 50)
print("技巧 4：设定角色")
print("=" * 50)

prompt4 = """
你是一位资深银行系统架构师，有 10 年金融科技经验。

请为长沙银行设计一个 AI 智能客服系统的技术方案，包括：
1. 系统架构
2. 技术选型
3. 数据安全考虑
4. 实施步骤

请用专业的语言，分点说明。
"""
print(call_ai(prompt4))
```

---

## 第五部分：Prompt Engineering 核心技巧

### 技巧 1：清晰明确的指令

```
❌ 模糊：帮我写代码
✅ 清晰：用 Python 写一个函数，输入是字符串列表，输出是最长字符串的长度，要求时间复杂度 O(n)
```

### 技巧 2：提供示例（Few-shot Learning）

```
请将以下口语转换为正式书面语：

口语：这个东西很好用 → 书面语：该产品具有优异的使用性能
口语：这个办法不行 → 书面语：该方案存在实施困难
口语：这个想法不错 → 书面语：
```

### 技巧 3：分步思考（Chain of Thought）

```
请逐步思考：
1. 首先...
2. 然后...
3. 最后...

问题：[你的问题]
```

### 技巧 4：设定角色

```
你是一位 [角色]，有 [X] 年经验。
请 [任务描述]。
要求：[具体要求]
```

### 技巧 5：结构化输出

```
请用以下格式回答：

## 核心观点
[一句话总结]

## 详细分析
[分点说明]

## 建议
[ actionable 建议]
```

---

## 第六部分：错误处理与调试

### 常见错误及解决方案

**错误 1：API Key 无效**
```
错误：InvalidApiKey
解决：检查 API Key 是否正确复制，有无多余空格
```

**错误 2：余额不足**
```
错误：InsufficientBalance
解决：充值或等待免费额度刷新
```

**错误 3：请求太频繁**
```
错误：RateLimitExceeded
解决：降低调用频率，加延时 time.sleep(1)
```

**错误 4：网络超时**
```
错误：Timeout
解决：检查网络，或增加超时时间
```

### 调试技巧

```python
import dashscope
from dashscope import Generation

# 开启调试模式
dashscope.api_key = "sk-你的 API Key"

def debug_call(prompt):
    """带调试信息的调用"""
    print(f"发送请求：{prompt[:50]}...")
    
    response = Generation.call(
        model="qwen-plus",
        prompt=prompt
    )
    
    print(f"状态码：{response.status_code}")
    print(f"完整响应：{response}")
    
    if response.status_code == 200:
        print(f"输出：{response.output.text}")
    else:
        print(f"错误：{response.code} - {response.message}")
    
    return response
```

---

## 第七部分：学习笔记模板

```markdown
# 任务 3 学习笔记

**学习日期：** ________

## API 调用理解
- API 是什么：
- 调用流程：

## 代码练习

### 项目 1：问答机器人
- 完成状态：□ 未开始 □ 进行中 □ 已完成
- 遇到的问题：
- 解决方案：

### 项目 2：文本摘要
- 完成状态：□ 未开始 □ 进行中 □ 已完成
- 测试结果：

### 项目 3：代码助手
- 完成状态：□ 未开始 □ 进行中 □ 已完成
- 生成的代码能运行吗：

## Prompt 技巧练习
- 最实用的技巧：
- 自己的心得：

## 下一步计划
- 
```

---

## ✅ 完成标准

- [ ] 理解 API 调用的基本流程
- [ ] 成功调用通义千问 API 并获得响应
- [ ] 完成至少 2 个实战项目
- [ ] 能独立编写调用 AI 的 Python 脚本
- [ ] 掌握至少 3 种 Prompt 技巧
- [ ] 完成学习笔记

---

*编制：程潇（奶潇）*
*为老公杨海波准备*
*2026 年 3 月 13 日*
