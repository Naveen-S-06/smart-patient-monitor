
package com.naveen.smartpatientmonitor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

public class HomeControllerTest {

    private HomeController controller;
    private Model model;

    @BeforeEach
    void setUp() {
        controller = new HomeController();
        model = new ExtendedModelMap();
    }

    @Test
    void homePageShouldDisplayPatientData() {

        String page = controller.home(model);

        assertEquals("index", page);
        assertEquals("Naveen",
                model.getAttribute("patientName"));
        assertEquals(76,
                model.getAttribute("heartRate"));
        assertEquals(36.8,
                model.getAttribute("temperature"));
        assertEquals(98,
                model.getAttribute("spo2"));

        assertEquals("Sample data - not analyzed",
                model.getAttribute("status"));
    }

    @Test
    void normalPatientTest() {

        controller.monitor("Naveen", 76, 36.8, 98, model);

        assertEquals(
                "NO DEMO ALERT: This does not confirm the patient is safe",
                model.getAttribute("status"));
    }

    @Test
    void warningPatientTest() {

        controller.monitor("Arun", 105, 38.0, 94, model);

        assertEquals(
                "WARNING FLAG: Recheck values and seek medical advice if concerned",
                model.getAttribute("status"));
    }

    @Test
    void emergencyPatientTest() {

        controller.monitor("Priya", 135, 40.0, 88, model);

        assertEquals(
                "EMERGENCY FLAG: Seek urgent medical assessment",
                model.getAttribute("status"));
    }

    @Test
    void invalidPatientTest() {

        controller.monitor("", 76, 36.8, 98, model);

        assertEquals(
                "INPUT ERROR: Patient name is required",
                model.getAttribute("status"));
    }
}
