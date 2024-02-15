package spring.security.fundamentals.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {


    @GetMapping("/demo")
    public String demo() {
        return "Demo!";
    }

    @PostMapping("/demo2")
    public String demo2() {
        return "Demo 2!";
    }

    @GetMapping("/demo3")
    public String demo3() {
        return "Demo 3!";
    }

}
