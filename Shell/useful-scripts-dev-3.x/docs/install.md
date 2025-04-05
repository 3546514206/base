🐌 下载使用
====================================

下载整个工程的脚本
-------------------

### 直接clone工程

使用简单、方便更新，不过要安装有`git`。

```bash
git clone git://github.com/oldratlee/useful-scripts.git

cd useful-scripts

# 使用Release分支的内容
git checkout release-3.x

# 更新脚本
git pull
```

包含2个分支：

- `dev-3.x`：开发分支
- `release-3.x`：发布分支，功能稳定的脚本

PS：  
我的做法是把`useful-scripts` checkout到`$HOME/bin`目录下，再把`$HOME/bin/useful-scripts/bin`配置到`PATH`变量上，这样方便我本地使用所有的脚本。

### 打包下载

下载文件[release-3.x.zip](https://github.com/oldratlee/useful-scripts/archive/release-3.x.zip)：

```bash
wget --no-check-certificate https://github.com/oldratlee/useful-scripts/archive/release-3.x.zip

unzip release-3.x.zip
```

下载和运行单个文件
-------------------

以[`show-busy-java-threads`](https://raw.github.com/oldratlee/useful-scripts/release-3.x/bin/show-busy-java-threads)为例。

### `curl`文件直接用`bash`运行

```bash
curl -sLk 'https://raw.github.com/oldratlee/useful-scripts/release-3.x/bin/show-busy-java-threads' | bash
```

### 下载单个文件

```bash
wget --no-check-certificate https://raw.github.com/oldratlee/useful-scripts/release-3.x/bin/show-busy-java-threads
chmod +x show-busy-java-threads

./show-busy-java-threads
```
