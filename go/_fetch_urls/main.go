package main

import (
	"fmt"
	"net/http"
	"sync"
)

func main() {

	urls := []string{
		"https://www.google.com/",
		"https://www.baidu.com/",
	}

	var wg = sync.WaitGroup{}
	var mutex = sync.Mutex{}

	results := make(map[string]string)
	for _, url := range urls {
		wg.Add(1)

		// 居然没报错！！！！
		go func(url string) {

			defer wg.Done()

			content, err := fetchUrl(url)

			if err != nil {
				fmt.Printf("Error fetching %s: %v\n", url, err)
				return
			}

			mutex.Lock()
			results[url] = content
			mutex.Unlock()
		}(url)

	}

	wg.Wait()

	fmt.Println("Results:")
	for url, content := range results {
		fmt.Printf("%s: %d bytes\n", url, len(content))
	}

}

// 根据 url 爬取网页内容
func fetchUrl(url string) (string, error) {
	response, _error := http.Get(url)

	defer response.Body.Close()

	if _error != nil {
		return "", _error
	}

	content := ""
	buff := make([]byte, 1024)
	for {
		n, _error := response.Body.Read(buff)
		// 如果报错说名解析完成
		if _error != nil || n <= 0 {
			break
		}

		if n > 0 {
			// 根据返回的字节长度对切片做切割，主要是针对最后一轮
			content += string(buff[:n])
		}
	}

	return content, _error
}
