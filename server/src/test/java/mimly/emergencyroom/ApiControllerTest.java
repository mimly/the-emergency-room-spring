package mimly.emergencyroom;

import com.fasterxml.jackson.databind.ObjectMapper;
import mimly.emergencyroom.model.dto.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    public void testInvalidPath() throws Exception {
//        this.mockMvc.perform(get("/invalid"))
//                .andExpect(status().isNotFound())
//                .andExpect(content().contentType("application/json"))
//                .andExpect(content().json("[]"));
//    }

    @Test
    public void testGetPriorities() throws Exception {
        this.mockMvc.perform(get("/api/priorities"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json("[{\"name\":\"SATISFACTORY\",\"id\":1,\"ID\":1},{\"name\":\"GUARDED\",\"id\":2,\"ID\":2},{\"name\":\"SERIOUS\",\"id\":3,\"ID\":3},{\"name\":\"CRITICAL\",\"id\":4,\"ID\":4},{\"name\":\"GRAVE\",\"id\":5,\"ID\":5}]"));
    }

    @Test
    public void testGetPriority() throws Exception {
        this.mockMvc.perform(get("/api/priorities/unknown"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json("[]"));

        this.mockMvc.perform(get("/api/priorities/0"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json("[]"));

        this.mockMvc.perform(get("/api/priorities/6"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json("[]"));

        this.mockMvc.perform(get("/api/priorities/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json("[{\"name\":\"GUARDED\",\"id\":2,\"ID\":2}]"));

        this.mockMvc.perform(get("/api/priorities/GUARDED"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json("[{\"name\":\"GUARDED\",\"id\":2,\"ID\":2}]"));
    }

    @Test
    public void testEnqueue() throws Exception {
        Patient patient = Patient.builder().ID(-1).firstName("X").lastName("Y").build();
        String patientJson = new ObjectMapper().writeValueAsString(patient);
        this.mockMvc.perform(post("/api/patients")
                .contentType("application/json")
                .content(patientJson))
                .andExpect(content().json(patientJson));
    }
}
