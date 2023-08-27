package main

import "fmt"

func main() {
	
	countryCapitalMap := make(map[string]string, 30)
	countryCapitalMap["France"] = "Paris"
	countryCapitalMap["Italy"] = "Rome"
	countryCapitalMap["Japan"] = "Tokyo"
	countryCapitalMap["India"] = "Paris"
	countryCapitalMap["France"] = "New delhi"
	
	fmt.Println("原始地图")
	for country, capital := range countryCapitalMap {
		fmt.Println(country, "的首都是", capital)
	}
	
	delete(countryCapitalMap, "France")
	
	fmt.Println("删除元素后地图")
	
	/*打印地图*/
	for country := range countryCapitalMap {
		fmt.Println(country, "首都是", countryCapitalMap[country])
	}
}
