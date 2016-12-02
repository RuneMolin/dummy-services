package dk.cpr.dar.view;

import dk.cpr.dar.service.StorageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import java.time.LocalDate;
/**
 * @author Rune Molin, rmo@nine.dk
 */
@Controller
public class HomeController {
  private final StorageService service;

  public HomeController(StorageService service) {
    this.service = service;
  }

  @GetMapping("/")
  public ModelAndView index() {
    ModelAndView mam = new ModelAndView("index");
    mam.addObject("currentStatus", service.getStatus());
    mam.addObject("currentRecords", service.getRecords());
    mam.addObject("theTime", LocalDate.now());
    return mam;
  }
}
