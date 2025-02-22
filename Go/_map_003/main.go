package main

import "fmt"

func main() {
	var siteMap = make(map[string]string)
	siteMap["Google"] = "谷歌"
	siteMap["Runoob"] = "菜鸟教程"
	siteMap["Baidu"] = "百度"
	siteMap["Wiki"] = "维基百科"

	for site := range siteMap {
		fmt.Println(site, "是", siteMap[site])
	}

	var key string
	key, ok := siteMap["Baidu"]

	fmt.Println(ok)

	fmt.Println(key)

	_, exit := siteMap["test"]
	fmt.Println(exit)
}
