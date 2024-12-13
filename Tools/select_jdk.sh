#!/bin/bash

# 查找本地所有的 JDK 安装目录
echo "正在扫描本地的 Java 版本..."

JAVA_HOME_LIST=()

# 查找所有的 JDK 安装路径
for dir in /Library/Java/JavaVirtualMachines/*; do
    if [[ -d "$dir" ]]; then
        JAVA_HOME_LIST+=("$dir")
    fi
done

# 如果没有找到任何 Java 安装，退出脚本
if [[ ${#JAVA_HOME_LIST[@]} -eq 0 ]]; then
    echo "未找到任何 Java 安装目录。"
    exit 1
fi

# 打印所有本地安装的 Java 版本目录
echo "发现以下 Java 安装目录："
for i in "${!JAVA_HOME_LIST[@]}"; do
    echo "$i: ${JAVA_HOME_LIST[$i]}"
done

# 提示用户选择 JDK 版本
echo "请选择一个要使用的 JDK 版本（输入对应的序号）："
read -p "请输入版本号序号：" selected_index

# 检查输入的序号是否有效
if [[ ! "$selected_index" =~ ^[0-9]+$ ]] || [[ "$selected_index" -ge "${#JAVA_HOME_LIST[@]}" ]]; then
    echo "无效的选择。"
    exit 1
fi

# 获取选定版本的 JDK 目录
TARGET_JDK="${JAVA_HOME_LIST[$selected_index]}"

# 获取 JDK 的完整路径
JAVA_HOME="$TARGET_JDK/Contents/Home"

# 打印选择的 JDK 路径
echo "选择的 JDK 是: $JAVA_HOME"
echo "已将 JAVA_HOME 设置为 $JAVA_HOME"

# 获取 ~/.zshrc 文件的路径
ZSHRC_FILE="$HOME/.zshrc"

# 在修改之前，清除已有的 JAVA_HOME 配置
echo "清除之前的 JAVA_HOME 配置..."
sed -i '' '/export JAVA_HOME/d' "$ZSHRC_FILE"

# 将选定的 JAVA_HOME 写入 ~/.zshrc 文件，使其永久生效
echo "export JAVA_HOME=\"$JAVA_HOME\"" >> "$ZSHRC_FILE"
echo "export PATH=\"\$JAVA_HOME/bin:\$PATH\"" >> "$ZSHRC_FILE"

# 提示用户修改已生效
echo "JAVA_HOME 已被写入到 ~/.zshrc 文件中。"

# 重新加载 ~/.zshrc 使改动生效
source "$ZSHRC_FILE"

# 显示当前的 Java 版本
java -version
