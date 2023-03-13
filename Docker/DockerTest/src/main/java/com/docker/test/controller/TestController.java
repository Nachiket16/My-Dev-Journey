package com.docker.test.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @RequestMapping("/")
  public Map<String, Object> getValues() {
    Map<String, Object> data = new HashMap<>();
    data.put("Message", "Java api is working fine");
    data.put("Language", Arrays.asList("Java", "Python", "JavaScript"));
    data.put("Code", 1443);
    return data;
  }

}
