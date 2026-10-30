
package com.naveen.smartpatientmonitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

public class HomeControllerTest {

    private HomeController controller;
    private PatientRepository patientRepository;
    private Model model;
    private RedirectAttributesModelMap redirectAttributes;

    @BeforeEach
    void setUp() {

        patientRepository = mock(PatientRepository.class);

        when(patientRepository.findAll())
                .thenReturn(Collections.emptyList());

        controller = new HomeController(patientRepository);

        model = new ExtendedModelMap();

        redirectAttributes = new RedirectAttributesModelMap();
    }

    @Test
    void homePageShouldDisplayPatientData() {

        String page = controller.home(model);

        assertEquals("index", page);
        assertEquals("Naveen", model.getAttribute("patientName"));
        assertEquals(76, model.getAttribute("heartRate"));
        assertEquals(36.8, model.getAttribute("temperature"));
        assertEquals(98, model.getAttribute("spo2"));

        assertEquals(
                "Sample data - not analyzed",
                model.getAttribute("status")
        );
    }

    @Test
    void normalPatientTest() {

        String page = controller.monitor(
                "Naveen", 76, 36.8, 98, redirectAttributes
        );

        assertEquals("redirect:/", page);

        assertEquals(
                "NO DEMO ALERT: This does not confirm the patient is safe",
                redirectAttributes.getFlashAttributes().get("status")
        );

        verify(patientRepository).save(any(Patient.class));
    }

    @Test
    void warningPatientTest() {

        String page = controller.monitor(
                "Arun", 105, 38.0, 94, redirectAttributes
        );

        assertEquals("redirect:/", page);

        assertEquals(
                "WARNING FLAG: Recheck values and seek medical advice if concerned",
                redirectAttributes.getFlashAttributes().get("status")
        );

        verify(patientRepository).save(any(Patient.class));
    }

    @Test
    void emergencyPatientTest() {

        String page = controller.monitor(
                "Priya", 135, 40.0, 88, redirectAttributes
        );

        assertEquals("redirect:/", page);

        assertEquals(
                "EMERGENCY FLAG: Seek urgent medical assessment",
                redirectAttributes.getFlashAttributes().get("status")
        );

        verify(patientRepository).save(any(Patient.class));
    }

    @Test
    void invalidPatientTest() {

        String page = controller.monitor(
                "", 76, 36.8, 98, redirectAttributes
        );

        assertEquals("redirect:/", page);

        assertEquals(
                "INPUT ERROR: Patient name is required",
                redirectAttributes.getFlashAttributes().get("status")
        );

        verify(patientRepository, never()).save(any(Patient.class));
    }
}
