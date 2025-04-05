@echo off
setlocal enabledelayedexpansion
set src_dir=c:\a
set dst_dir=c:\b
set last_full_time=0
set last_diff_time=0
 
rem 遍历源文件夹里的文件
rem 把文件名用下划线_作为分隔符分成3个部分
rem 判断文件名第2部分是diff或full，分别获取最新时间的文件
rem 第3部分就是备份时间，比较其数值，数值越大则表示文件越新
for /r "%src_dir%" %%a in (*) do (
  for /f "tokens=2,3 delims=_" %%b in ("%%~na") do (
    if "%%b"=="diff" (
      if "%%c" gtr "!last_diff_time!" (
        set last_diff_time=%%c
        set last_diff_name=%%a
      )
    ) else if "%%b"=="full" (
      if "%%c" gtr "!last_full_time!" (
        set last_full_time=%%c
        set last_full_name=%%a
      )
    )  
  )
)
 
if %last_full_time% equ 0 goto :eof
copy /b "%last_full_name%" "%dst_dir%"
if "%last_full_time%" lss "%last_diff_time%" (
  copy /b "%last_diff_name%" "%dst_dir%"
  goto B
) else (goto A)
 
:B
echo B
rem goto :eof
 
:A
echo A
rem goto :eof

pause