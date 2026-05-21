package net.likelion.backend.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class HelloController {

    @GetMapping("/hello")
    public Map<String, String>hello(){
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello, 멋사!");
        return response;
    }
    @GetMapping("/hello/{name}")
    public Map<String,String> helloName(@PathVariable String name){
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello, " + name + "!");
        return response;
    }

    @GetMapping("/hello/search")
    public Map<String, String> helloSearch(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") int page){
            Map<String,String> response = new HashMap<>();
            response.put("keyword", keyword);
            response.put("page", String.valueOf(page));
            return response;
    }
}
