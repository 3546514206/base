import time

import pywifi
from pywifi import const


def wifi_scan():
    # 初始化 WiFi 对象
    wifi = pywifi.PyWiFi()

    # 获取第一个无线网卡接口
    if len(wifi.interfaces()) == 0:
        print("未检测到无线网卡！")
        return
    interface = wifi.interfaces()[0]

    # 开始扫描
    interface.scan()
    print("扫描中，请等待 5 秒...")
    time.sleep(5)  # 等待扫描完成

    # 获取扫描结果
    scan_results = interface.scan_results()

    # 去重并提取 WiFi 信息
    wifi_list = []
    seen_ssids = set()
    for result in scan_results:
        try:
            ssid = result.ssid.encode('raw_unicode_escape').decode('utf-8')  # 解决中文乱码
            if not ssid or ssid in seen_ssids:
                continue
            seen_ssids.add(ssid)

            # 获取信号强度（dBm）并转换为百分比
            signal = 100 + result.signal  # 近似百分比计算
            signal = max(0, min(signal, 100))  # 限制在 0-100 范围

            wifi_info = {
                "SSID": ssid,
                "BSSID": result.bssid,
                "信号强度": f"{signal}%",
                "加密类型": _get_cipher_name(result.akm[0])
            }
            wifi_list.append(wifi_info)
        except Exception as e:
            continue

    # 按信号强度排序
    wifi_list = sorted(wifi_list, key=lambda x: int(x["信号强度"].replace('%', '')), reverse=True)

    # 打印结果
    print("\n{:<20} {:<18} {:<10} {:<15}".format("WiFi名称", "MAC地址", "信号强度", "加密类型"))
    print("-" * 65)
    for wifi in wifi_list:
        print("{:<20} {:<18} {:<10} {:<15}".format(
            wifi["SSID"],
            wifi["BSSID"],
            wifi["信号强度"],
            wifi["加密类型"]
        ))


def _get_cipher_name(akm_type):
    # 获取加密类型名称
    cipher_map = {
        const.AKM_TYPE_NONE: "开放网络",
        const.AKM_TYPE_WPA: "WPA",
        const.AKM_TYPE_WPAPSK: "WPA-PSK",
        const.AKM_TYPE_WPA2: "WPA2",
        const.AKM_TYPE_WPA2PSK: "WPA2-PSK"
    }
    return cipher_map.get(akm_type, "未知加密")


if __name__ == "__main__":
    wifi_scan()
