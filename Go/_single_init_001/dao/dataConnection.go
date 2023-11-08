package dao

import "fmt"

// 基于 init 实现单例模式
type dataConnection struct {
	ip string
}

func (r dataConnection) PrintConnInfo() {
	fmt.Println(r.ip)
}

var dao *dataConnection

func init() {
	dao = &dataConnection{
		ip: "127.0.0.1",
	}
}

func GetDao() *dataConnection {
	return dao
}
