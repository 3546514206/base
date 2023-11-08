package main

import "_single_once_003/single"

func main() {
	singleObj := single.GetSingleObj()
	singleObj.PrintIpInfo()
}
