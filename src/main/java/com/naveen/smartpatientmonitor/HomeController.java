
package com.naveen.smartpatientmonitor;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final List<String> samplePatients =
            Arrays.asList("Naveen", "Arun", "Priya");

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("samplePatients", samplePatients);
        model.addAttribute("patientName", "Naveen");
        model.addAttribute("heartRate", 76);
        model.addAttribute("temperature", 36.8);
        model.addAttribute("spo2", 98);
        model.addAttribute("status", "Sample data - not analyzed");

        return "index";
    }

    @PostMapping("/monitor")
    public String monitor(
            @RequestParam String patientName,
            @RequestParam int heartRate,
            @RequestParam double temperature,
            @RequestParam int spo2,
            Model model) {

        model.addAttribute("samplePatients", samplePatients);
        model.addAttribute("patientName", patientName);
        model.addAttribute("heartRate", heartRate);
        model.addAttribute("temperature", temperature);
        model.addAttribute("spo2", spo2);

        String status;
        String name = patientName == null ? "" : patientName.trim();

        if (name.isEmpty()) {
            status = "INPUT ERROR: Patient name is required";
        } else if (heartRate < 1 || heartRate > 250
                || temperature < 25 || temperature > 45
                || spo2 < 0 || spo2 > 100) {
            status = "INPUT ERROR: Check the entered values";
        } else if (spo2 < 90 || heartRate < 40
                || heartRate > 130 || temperature >= 40) {
            status = "EMERGENCY FLAG: Seek urgent medical assessment";
        } else if (spo2 < 95 || heartRate < 50
                || heartRate > 100 || temperature >= 38) {
            status = "WARNING FLAG: Recheck values and seek medical advice if concerned";
        } else {
            status = "NO DEMO ALERT: This does not confirm the patient is safe";
        }

        model.addAttribute("patientName", name);
        model.addAttribute("status", status);

        return "index";
    }
}
