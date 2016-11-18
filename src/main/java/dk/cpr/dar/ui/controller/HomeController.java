package dk.cpr.dar.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Rune Molin, rmo@nine.dk
 */
@Controller
public class HomeController {

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("message", "Hello!");
    return "index";
  }
}
