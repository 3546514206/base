package main

import (
	"encoding/json"
	"fmt"
	"reflect"
)

// Serialize 将一个结构体转换为JSON字符串
func Serialize(data interface{}) (string, error) {
	jsonData, _error := json.Marshal(data)
	if _error != nil {
		return "", _error
	}

	return string(jsonData), nil
}

// 逆序列化
func DeSerialize(jsonStr string, target interface{}) error {
	return json.Unmarshal([]byte(jsonStr), target)
}

// 查漏补缺——`json:"name"`的作用是声明序列化的时候结构体字段与json字段之间的对应关系
type Person struct {
	Name    string `json:"name"`
	Age     string `json:"age"`
	Address string `json:"address"`
}

func main() {

	person := Person{
		Name:    "杨海波",
		Age:     "18",
		Address: "浙江杭州",
	}

	personStr, _error := Serialize(person)
	if _error == nil {
		fmt.Println(personStr)
	}

	var deserialized Person
	_error = DeSerialize(personStr, &deserialized)

	if _error == nil {
		t := reflect.TypeOf(deserialized)
		for i := 0; i < t.NumField(); i++ {
			field := t.Field(i)
			fmt.Printf("Name: %s, Type: %s, Tag: %s\n", field.Name, field.Type, field.Tag)
		}
	}
}
