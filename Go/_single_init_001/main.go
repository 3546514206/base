package main

import "_single_init_001/dao"

func main() {
	conn := dao.GetDao()
	conn.PrintConnInfo()
}
