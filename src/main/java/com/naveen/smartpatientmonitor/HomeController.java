
package com.naveen.smartpatientmonitor;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    private final PatientRepository patientRepository;

    private final List<String> samplePatients =
            Arrays.asList("Naveen", "Arun", "Priya");

    public HomeController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("samplePatients", samplePatients);

        // Default values only when flash data is unavailable
        if (!model.containsAttribute("patientName")) {
            model.addAttribute("patientName", "Naveen");
        }

        if (!model.containsAttribute("heartRate")) {
            model.addAttribute("heartRate", 76);
        }

        if (!model.containsAttribute("temperature")) {
            model.addAttribute("temperature", 36.8);
        }

        if (!model.containsAttribute("spo2")) {
            model.addAttribute("spo2", 98);
        }

        if (!model.containsAttribute("status")) {
            model.addAttribute("status", "Sample data - not analyzed");
        }

        model.addAttribute("patients", patientRepository.findAll());

        return "index";
    }

    @PostMapping("/monitor")
    public String monitor(
            @RequestParam String patientName,
            @RequestParam int heartRate,
            @RequestParam double temperature,
            @RequestParam int spo2,
            RedirectAttributes redirectAttributes) {

        String name = patientName == null ? "" : patientName.trim();
        String status;

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

        // Save only valid patient measurements
        if (!name.isEmpty()
                && heartRate >= 1 && heartRate <= 250
                && temperature >= 25 && temperature <= 45
                && spo2 >= 0 && spo2 <= 100) {

            Patient patient = new Patient(
                    name,
                    heartRate,
                    temperature,
                    spo2,
                    status
            );

            patientRepository.save(patient);
        }

        // Pass submitted values to the redirected page
        redirectAttributes.addFlashAttribute("patientName", name);
        redirectAttributes.addFlashAttribute("heartRate", heartRate);
        redirectAttributes.addFlashAttribute("temperature", temperature);
        redirectAttributes.addFlashAttribute("spo2", spo2);
        redirectAttributes.addFlashAttribute("status", status);

        // Redirect prevents duplicate form submission on refresh
        return "redirect:/";
    }
}
