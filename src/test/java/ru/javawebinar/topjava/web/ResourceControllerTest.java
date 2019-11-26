package ru.javawebinar.topjava.web;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ResourceControllerTest extends AbstractControllerTest{

    @Test
    public void getStyle() throws Exception {
        mockMvc.perform(get("/resources/css/style.css"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/css")).andDo(print())
        ;
    }
}
