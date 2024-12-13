# 查找本地所有的 JDK 安装目录
Write-Host "正在扫描本地的 Java 版本..."

# 获取所有安装的 JDK 目录
$javaHomeList = Get-ChildItem -Path "C:\Program Files\Java" -Directory

if ($javaHomeList.Count -eq 0)
{
    Write-Host "未找到任何 Java 安装目录。"
    exit
}

# 打印所有本地安装的 Java 版本目录
Write-Host "发现以下 Java 安装目录："
for ($i = 0; $i -lt $javaHomeList.Count; $i++) {
    Write-Host "$i: $($javaHomeList[$i].FullName)"
}

# 提示用户选择 JDK 版本
$selectedIndex = Read-Host "请输入版本号序号（选择一个 JDK 版本）"

# 检查输入的序号是否有效
if ($selectedIndex -lt 0 -or $selectedIndex -ge $javaHomeList.Count)
{
    Write-Host "无效的选择。"
    exit
}

# 获取选定版本的 JDK 目录
$targetJdk = $javaHomeList[$selectedIndex].FullName

# 获取 JDK 的完整路径
$javaHome = "$targetJdk\bin"

# 打印选择的 JDK 路径
Write-Host "选择的 JDK 是: $javaHome"
Write-Host "已将 JAVA_HOME 设置为 $javaHome"

# 获取环境变量
$envVariable = [System.Environment]::GetEnvironmentVariable('JAVA_HOME', [System.EnvironmentVariableTarget]::User)

# 如果 JAVA_HOME 已经存在，则先清除旧配置
if ($envVariable)
{
    Write-Host "清除旧的 JAVA_HOME 配置..."
    [System.Environment]::SetEnvironmentVariable('JAVA_HOME', $null, [System.EnvironmentVariableTarget]::User)
}

# 将新的 JAVA_HOME 写入到用户环境变量
Write-Host "设置 JAVA_HOME 环境变量为 $javaHome"
[System.Environment]::SetEnvironmentVariable('JAVA_HOME', $javaHome, [System.EnvironmentVariableTarget]::User)

# 更新 PATH 环境变量
$path = [System.Environment]::GetEnvironmentVariable('Path', [System.EnvironmentVariableTarget]::User)
if ($path -notlike "*$javaHome*")
{
    Write-Host "将 JDK 的 bin 目录添加到 PATH 环境变量中"
    $newPath = "$javaHome;$path"
    [System.Environment]::SetEnvironmentVariable('Path', $newPath, [System.EnvironmentVariableTarget]::User)
}

# 提示用户配置已生效
Write-Host "JAVA_HOME 已更新到 $javaHome"
Write-Host "PATH 已更新，包含 JDK bin 目录。"
Write-Host "请重新启动终端或命令行窗口以使更改生效。"

# 显示当前 Java 版本
java -version
