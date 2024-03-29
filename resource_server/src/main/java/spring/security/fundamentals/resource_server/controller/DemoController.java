package spring.security.fundamentals.resource_server.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/demo")
    public String demo() {
        return "demo";
    }

    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/demo1")
    public String demo1() {
        return "demo1";
    }

    @PreAuthorize("hasAuthority('PATCH')")
    @GetMapping("/demo2")
    public String demo2() {
        return "demo2";
    }
}
