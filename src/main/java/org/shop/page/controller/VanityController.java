package org.shop.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by vprasanna on 5/22/2016.
 */
@Controller
public class VanityController {

  /**
   * The constant mappings.
   */
//TODO: to move it to properties
  final static Map<String, String> mappings = new LinkedHashMap<>();

  static {
    mappings.put("api", "/swagger-ui.html");
  }


  /**
   * Handle string.
   *
   * @param path the path
   * @return the string
   */
  @RequestMapping(value = "/page/{path}", method = RequestMethod.GET)
  public String handle(@PathVariable String path) {
    return "redirect:" + mappings.get(path);
  }
}
