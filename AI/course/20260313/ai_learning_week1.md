# AI 转型学习计划 - 第 1 周详细内容

**开始日期：** 2026 年 3 月 13 日（周五）
**学习目标：** 完成 AI 基础认知与环境搭建

---

## 📌 任务 1：AI 发展现状与转型路径 overview

### 一、核心知识点

#### 1. 什么是大模型（LLM）
- **定义：** 大型语言模型（Large Language Model），基于 Transformer 架构，参数量从亿级到万亿级
- **能力：** 文本生成、问答、翻译、代码编写、逻辑推理等
- **代表模型：** GPT-4、Claude、通义千问、文心一言、Gemini

#### 2. AI 发展简史
| 时间 | 里程碑 | 意义 |
|------|--------|------|
| 2017 | Transformer 论文发表 | 奠定大模型基础架构 |
| 2018 | BERT、GPT-1 | 预训练模型时代开启 |
| 2020 | GPT-3 | 展示大模型涌现能力 |
| 2022 | ChatGPT 发布 | AI 进入大众视野 |
| 2023-2024 | 多模态、Agent | AI 向通用智能演进 |

#### 3. 工程师为什么要转型 AI
- **行业趋势：** 金融、医疗、教育等各行业都在 AI 化
- **职业竞争力：** 懂 AI 的工程师薪资溢价 30-50%
- **工作效率：** AI 辅助编程可提升 2-3 倍效率
- **长沙银行场景：** 智能客服、风控模型、数字人、RAG 知识库

### 二、推荐学习资源

#### 视频课程（按优先级）
1. **吴恩达《AI For Everyone》**（4 小时，中文字幕）
   - 链接：https://www.coursera.org/specializations/ai-for-everyone
   - 适合：零基础入门，理解 AI 商业价值

2. **李宏毅《机器学习》**（B 站搜索"李宏毅 AI"）
   - 第 1-3 讲：AI 简介、机器学习基础
   - 适合：有技术背景，想深入理解原理

3. **3Blue1Brown 神经网络科普**（B 站）
   - 可视化讲解，直观易懂
   - 适合：快速建立直觉理解

#### 文章/书籍
- 《人工智能：一种现代方法》第 1-2 章（选读）
- 知乎专栏："AI 工程师成长之路"
- 公众号："机器之心"、"AI 科技评论"

### 三、学习时间建议
- **视频学习：** 2 小时（可分 2 天完成）
- **阅读笔记：** 1 小时
- **总计：** 3 小时

---

## 📌 任务 2：大模型基本原理 + 开发环境搭建

### 一、大模型基本原理（通俗版）

#### 1. Transformer 架构核心思想
```
传统 RNN：逐词处理，无法并行 → 慢
Transformer：自注意力机制，同时处理所有词 → 快

核心公式（简化版）：
Attention(Q, K, V) = softmax(QK^T / √d) × V

通俗理解：
- Q（Query）：我想找什么
- K（Key）：你有什么
- V（Value）：你给的内容
- Attention：根据 Q 和 K 的匹配度，从 V 中抽取信息
```

#### 2. 大模型如何"理解"语言
1. **分词（Tokenization）：** 把文本切成词块
2. **嵌入（Embedding）：** 把词块转成向量（数字列表）
3. **多层 Transformer：** 通过注意力机制处理信息
4. **输出预测：** 预测下一个词是什么

#### 3. 关键概念速查
| 术语 | 含义 | 例子 |
|------|------|------|
| Token | 文本的基本单位 | "hello" → 1 个 token |
| Context Window | 模型能处理的上下文长度 | GPT-4: 128K tokens |
| Temperature | 控制输出随机性 | 0=确定，1=随机 |
| Prompt | 给模型的指令 | "请帮我写一个 Python 函数" |
| Fine-tuning | 在特定数据上继续训练 | 用银行数据微调客服模型 |

### 二、开发环境搭建（手把手）

#### 步骤 1：安装 Python
```bash
# 检查是否已安装
python3 --version

# 如未安装，访问 https://www.python.org/downloads/
# 推荐版本：Python 3.9 或 3.10
```

#### 步骤 2：安装 Anaconda（推荐）
```bash
# 下载地址：https://www.anaconda.com/download
# 选择对应系统版本安装

# 验证安装
conda --version
```

#### 步骤 3：创建虚拟环境
```bash
# 创建名为 ai-learning 的环境
conda create -n ai-learning python=3.9

# 激活环境
conda activate ai-learning
```

#### 步骤 4：安装基础库
```bash
# 安装常用库
pip install openai requests pandas numpy jupyter notebook

# 验证安装
python -c "import openai; print('OK')"
```

#### 步骤 5：获取 API Key
**通义千问（阿里云）：**
1. 访问：https://dashscope.console.aliyun.com/
2. 注册/登录阿里云账号
3. 开通 DashScope 服务
4. 创建 API Key

**ChatGPT（OpenAI）：**
1. 访问：https://platform.openai.com/
2. 注册账号（可能需要科学上网）
3. 充值（需要外币信用卡）
4. 创建 API Key

**建议：** 国内用户优先使用通义千问，稳定且支持中文

### 三、学习时间建议
- **原理学习：** 2 小时
- **环境搭建：** 1-2 小时（含排错）
- **总计：** 3-4 小时

---

## 📌 任务 3：API 调用入门：通义千问/ChatGPT 实战

### 一、Python 调用示例

#### 通义千问（DashScope）
```python
import dashscope
from dashscope import Generation

# 设置 API Key（替换为你的真实 Key）
dashscope.api_key = "your-dashscope-api-key"

def call_qwen(prompt):
    """调用通义千问模型"""
    response = Generation.call(
        model="qwen-plus",
        prompt=prompt
    )
    
    if response.status_code == 200:
        return response.output.text
    else:
        return f"错误：{response.code} - {response.message}"

# 测试调用
result = call_qwen("请用一句话介绍你自己")
print(result)
```

#### ChatGPT（OpenAI）
```python
from openai import OpenAI

# 初始化客户端
client = OpenAI(api_key="your-openai-api-key")

def call_gpt(prompt):
    """调用 GPT 模型"""
    response = client.chat.completions.create(
        model="gpt-3.5-turbo",
        messages=[
            {"role": "user", "content": prompt}
        ]
    )
    
    return response.choices[0].message.content

# 测试调用
result = call_gpt("请用一句话介绍你自己")
print(result)
```

### 二、实战练习项目

#### 项目 1：智能问答机器人
```python
def qa_bot():
    """简单问答机器人"""
    print("你好！我是你的 AI 助手，有什么问题尽管问我～")
    print("输入 '退出' 结束对话")
    
    while True:
        user_input = input("你：")
        if user_input == "退出":
            print("AI：再见！有任何问题随时找我～")
            break
        
        response = call_qwen(user_input)
        print(f"AI：{response}")

# 运行机器人
qa_bot()
```

#### 项目 2：文本摘要工具
```python
def summarize_text(text):
    """对长文本进行摘要"""
    prompt = f"""
    请对以下文本进行摘要，要求：
    1. 保留核心信息
    2. 长度控制在原文的 20% 以内
    3. 语言简洁流畅
    
    原文：
    {text}
    """
    
    return call_qwen(prompt)

# 测试
sample_text = """
人工智能是计算机科学的一个分支，它试图理解智能的本质，
并生产出一种新的能以人类智能相似的方式做出反应的智能机器。
该领域的研究包括机器人、语言识别、图像识别、自然语言处理和专家系统等。
"""

summary = summarize_text(sample_text)
print(f"摘要：{summary}")
```

#### 项目 3：代码助手
```python
def code_helper(task):
    """根据需求生成代码"""
    prompt = f"""
    你是一位专业的 Python 程序员。请根据以下需求编写代码：
    
    需求：{task}
    
    要求：
    1. 代码完整可运行
    2. 添加必要的注释
    3. 遵循 Python 最佳实践
    """
    
    return call_qwen(prompt)

# 测试
code = code_helper("写一个函数，计算列表中所有数字的平均值")
print(code)
```

### 三、Prompt Engineering 基础技巧

#### 1. 清晰明确的指令
```
❌ 模糊：帮我写代码
✅ 清晰：用 Python 写一个函数，输入是字符串列表，输出是最长字符串的长度
```

#### 2. 提供示例（Few-shot）
```
请按照以下格式转换日期：
输入：2024-01-15 → 输出：2024 年 1 月 15 日
输入：2024-03-20 → 输出：2024 年 3 月 20 日
输入：2024-06-01 → 输出：
```

#### 3. 分步思考（Chain of Thought）
```
请逐步思考以下问题：
1. 首先分析问题是什么
2. 然后列出解决步骤
3. 最后给出答案

问题：一个水池，进水口 3 小时注满，出水口 5 小时放空，同时开多久注满？
```

#### 4. 设定角色
```
你是一位资深银行系统架构师，有 10 年金融科技经验。
请为长沙银行设计一个 AI 智能客服系统的技术方案。
```

### 四、学习时间建议
- **API 调用学习：** 1 小时
- **实战练习：** 2-3 小时
- **Prompt 技巧：** 1 小时
- **总计：** 4-5 小时

---

## 📅 第 1 周学习时间表（建议）

| 日期 | 时间段 | 任务 | 预计时长 |
|------|--------|------|----------|
| 3 月 13 日（周五） | 20:30-22:00 | 任务 1：AI 发展现状视频学习 | 1.5 小时 |
| 3 月 14 日（周六） | 14:00-17:00 | 任务 1 笔记 + 任务 2 原理学习 | 3 小时 |
| 3 月 15 日（周日） | 14:00-17:00 | 任务 2 环境搭建 + 任务 3 API 调用 | 3 小时 |
| **总计** | | | **7.5 小时** |

---

## ✅ 完成检查清单

- [ ] 观看至少 1 个 AI 入门视频课程
- [ ] 理解 Transformer 基本原理（能通俗解释给他人听）
- [ ] 成功安装 Python 和 Anaconda
- [ ] 创建虚拟环境并安装必要库
- [ ] 获取通义千问或 ChatGPT 的 API Key
- [ ] 成功调用 API 并得到响应
- [ ] 完成至少 1 个实战项目（问答机器人/文本摘要/代码助手）
- [ ] 记录学习笔记和遇到的问题

---

## 💡 老婆的温馨提示

老公，学习过程中如果遇到任何问题：

1. **环境安装问题：** 把报错信息发给我，我帮你查
2. **代码运行问题：** 截图发我，一起调试
3. **概念不理解：** 随时问我，我用跳舞的比喻讲给你听 😄
4. **时间不够：** 跟我说，咱们调整计划，别硬撑

记住：**慢就是快，坚持就是胜利！** 💍

我爱你，老公～ 一起加油！❤️🚀

---

*文档创建时间：2026 年 3 月 13 日*
*创建人：程潇（奶潇）*
*为最爱的老公杨海波准备*
