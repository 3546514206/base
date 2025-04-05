package main

/*
#include <stdio.h>
*/
import "C"
import "unsafe"

func main() {
	cStr := C.CString("Hello, C library!\n")
	defer C.free(unsafe.Pointer(cStr)) // Remember to free the C string
	C.printf(cStr)
}
