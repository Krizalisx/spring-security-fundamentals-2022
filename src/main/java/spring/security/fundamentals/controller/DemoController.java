package spring.security.fundamentals.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/demo")
    @PreAuthorize("hasAuthority('read')")
    public String demo() {
        return "Demo!";
    }

    @PreAuthorize("hasAuthority('write')")
    @PostMapping("/demo2")
    public String demo2() {
        return "Demo 2!";
    }

    @PreAuthorize("#name == authentication.name")
    @GetMapping("/demo3/{name}")
    public String demo3(@PathVariable String name) {
        return "Demo 3!";
    }

    @PreAuthorize("@authorizeCondition.hasAuthority(#authority)")
    @GetMapping("/demo4/{authority}")
    public String demo4(@PathVariable String authority) {
        return "Demo 4!";
    }

    @PostAuthorize("returnObject == '1'")
    @GetMapping("/demo5")
    public String demo5() {
        int result = new Random().nextInt(3);
        log.info("In method demo5(), result: {}", result);
        return String.valueOf(result);
    }

    @PreFilter("filterObject.contains('a')")
    @GetMapping("/demo6")
    public String demo6(@RequestBody List<String> values) {
        log.info(values.toString());

        return "Demo 6!";
    }

    @PostFilter("filterObject.contains('1')")
    @GetMapping("/demo7")
    public List<String> demo7() {
        List<String> strings = new ArrayList<>(List.of("1", "2", "3"));
        log.info(strings.toString());

        return strings;
    }

}
