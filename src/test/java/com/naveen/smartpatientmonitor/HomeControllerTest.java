
package com.naveen.smartpatientmonitor;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeControllerTest {

    @Test
    void homePageShouldDisplayPatientData() {
        HomeController controller = new HomeController();
        Model model = new ExtendedModelMap();

        String viewName = controller.home(model);

        assertEquals("index", viewName);
        assertEquals("Naveen", model.getAttribute("patientName"));
        assertEquals(76, model.getAttribute("heartRate"));
        assertEquals(36.8, model.getAttribute("temperature"));
        assertEquals(98, model.getAttribute("spo2"));
        assertEquals("Sample data", model.getAttribute("status"));
    }
}
