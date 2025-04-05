#!/bin/bash

# 定义 Gradle wrapper 存放的目录
GRADLE_WRAPPER_DIR="$HOME/.gradle/wrapper/dists"

# 检查该目录是否存在
if [ ! -d "$GRADLE_WRAPPER_DIR" ]; then
  echo "目录 $GRADLE_WRAPPER_DIR 不存在，无法找到任何 Gradle 安装。"
  exit 1
fi

# 查找所有 Gradle 安装的版本
echo "正在扫描本地的 Gradle 版本..."

GRADLE_LIST=()

# 查找 wrapper/dists 目录下所有有效的 Gradle 版本
for dir in "$GRADLE_WRAPPER_DIR"/gradle-*/*/gradle-*; do
  if [ -d "$dir" ]; then
    VERSION=$(basename "$(dirname "$dir")" | sed 's/gradle-//')
    GRADLE_LIST+=("$VERSION:$dir")
  fi
done

# 如果没有找到任何 Gradle 安装，退出脚本
if [ ${#GRADLE_LIST[@]} -eq 0 ]; then
  echo "未找到任何 Gradle 安装目录。"
  exit 1
fi

# 打印所有本地安装的 Gradle 版本
echo "发现以下 Gradle 版本目录："
for i in "${!GRADLE_LIST[@]}"; do
  echo "$i: ${GRADLE_LIST[$i]}"
done

# 提示用户选择 Gradle 版本
echo "请选择一个要使用的 Gradle 版本（输入对应的序号）："
read -p "请输入版本号序号：" selected_index

# 检查输入的序号是否有效
if ! [[ "$selected_index" =~ ^[0-9]+$ ]] || [ "$selected_index" -ge "${#GRADLE_LIST[@]}" ]; then
  echo "无效的选择。"
  exit 1
fi

# 获取选定版本的 Gradle 路径
selected_gradle_info="${GRADLE_LIST[$selected_index]}"
TARGET_GRADLE_HOME=$(echo "$selected_gradle_info" | cut -d ':' -f2)

# 获取 Gradle 的完整路径
GRADLE_HOME="$TARGET_GRADLE_HOME"

# 打印选择的 Gradle 路径
echo "选择的 Gradle 版本是: $GRADLE_HOME"
echo "已将 GRADLE_HOME 设置为 $GRADLE_HOME"

# 获取 ~/.zshrc 文件的路径
ZSHRC_FILE="$HOME/.zshrc"

# 清除重复的 JAVA_HOME 和 GRADLE_HOME 配置
echo "清除之前的 JAVA_HOME 和 GRADLE_HOME 配置..."
sed -i '' '/export JAVA_HOME/d' "$ZSHRC_FILE"
sed -i '' '/export GRADLE_HOME/d' "$ZSHRC_FILE"

# 如果 JAVA_HOME 未设置，添加 JAVA_HOME
if ! grep -q "export JAVA_HOME" "$ZSHRC_FILE"; then
  echo "export JAVA_HOME=\"$JAVA_HOME\"" >> "$ZSHRC_FILE"
  echo "export PATH=\"\$JAVA_HOME/bin:\$PATH\"" >> "$ZSHRC_FILE"
fi

# 如果 GRADLE_HOME 未设置，添加 GRADLE_HOME
if ! grep -q "export GRADLE_HOME" "$ZSHRC_FILE"; then
  echo "export GRADLE_HOME=\"$GRADLE_HOME\"" >> "$ZSHRC_FILE"
  echo "export PATH=\"\$GRADLE_HOME/bin:\$PATH\"" >> "$ZSHRC_FILE"
fi

# 提示用户修改已生效
echo "已将 JAVA_HOME 和 GRADLE_HOME 写入到 ~/.zshrc 文件中。"

# 重新加载 ~/.zshrc 使改动生效
source "$ZSHRC_FILE"

# 显示当前 Gradle 版本
gradle -v
