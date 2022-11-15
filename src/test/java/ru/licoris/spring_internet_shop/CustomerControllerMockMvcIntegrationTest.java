package ru.licoris.spring_internet_shop;

import ru.licoris.spring_internet_shop.model.Customer;
import ru.licoris.spring_internet_shop.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerMockMvcIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerRepository repository;


    @Test
    public void givenCustomer_whenAdd_thenStatus200AndPersonReturned() throws Exception {
        Customer customer = createCustomer();

        mockMvc.perform(post("/api/customers")
                        .content(objectMapper.writeValueAsString(customer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenId_whenGetExistingCustomer_thenStatus200AndCustomerReturned() throws Exception {
        Customer customer = createAndSaveCustomer();
        Long id = customer.getId();
        String name = customer.getName();

        mockMvc.perform(get("/api/customers/{id}", id))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    public void givenId_whenUpdateExistingCustomer_thenStatus200AndCustomerReturned() throws Exception {
        Customer customer = createAndSaveCustomer();

        Customer updatedCustomer =
                new Customer("Dankie", "Monkey", "Turkish street 11", "1234567891");
        updatedCustomer.setId(customer.getId());

        Long id = updatedCustomer.getId();
        mockMvc.perform(put("/api/customers/{id}", id)
                        .content(objectMapper.writeValueAsString(updatedCustomer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }

    @Test
    public void saveCustomer_whenDeleteCustomer_thenStatus200() throws Exception {
        Customer customer = createAndSaveCustomer();
        Long id = customer.getId();
        mockMvc.perform(delete("/api/customers/{id}", id)).andExpect(status().isOk());
    }


    private Customer createAndSaveCustomer() {
        Customer customer =
                new Customer("Alex", "Rowlavie", "naked street 32", "9876543211");
        return repository.save(customer);
    }
    private Customer createCustomer(){
        return new Customer("Alex", "Rowlavie", "naked street 32", "9876543211");
    }


}
