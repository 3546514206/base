package edu.zjnu.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: BootLoader导入
 * @author: 杨海波
 * @date: 2021-08-19
 **/
@SpringBootApplication
//@RestController
public class BootLoader {

    public static void main(String[] args) {
        SpringApplication.run(BootLoader.class, args);
    }

//    /**
//     * Hello，World
//     *
//     * @param who 参数，非必须
//     * @return Hello, ${who}
//     */
//    @GetMapping("/hello")
//    public String sayHello(@RequestParam(required = false, name = "who") String who) {
//        if (null == who || "".equals(who)) {
//            who = "World";
//        }
//        return "Hello, " + who + " !";
//    }
}
