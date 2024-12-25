import datetime
import os

import pandas as pd

from constant import base_workspace_dir


def handle_excel_sample(read_path: str, write_path: str = None):
    # 读取 Excel 文件
    df = pd.read_excel(read_path)

    # 获取当前系统时间并格式化为字符串（例如：YYYY-MM-DD_HH-MM-SS）
    current_time_str = datetime.datetime.now().strftime('%Y-%m-%d_%H-%M-%S')

    # 写入新文件
    write_path = base_workspace_dir + current_time_str + '-' + write_path
    if write_path is not None:
        # 先尝试删除已存在的同名文件
        try:
            os.remove(write_path)
        except FileNotFoundError:
            pass  # 如果文件不存在，忽略该错误
        df.to_excel(write_path, index=False)

    return df
