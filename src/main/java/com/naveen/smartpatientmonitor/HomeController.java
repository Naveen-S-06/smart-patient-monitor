
package com.naveen.smartpatientmonitor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Testing GitHub webhook

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("patientName", "Naveen");
        model.addAttribute("heartRate", 76);
        model.addAttribute("temperature", 36.8);
        model.addAttribute("spo2", 98);
        model.addAttribute("status", "Sample data");

        return "index";
    }
}
