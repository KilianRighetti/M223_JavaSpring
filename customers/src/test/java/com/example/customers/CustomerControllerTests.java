package com.example.customers;

import com.example.customers.data.CustomerRepository;
import com.example.customers.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc  // Abilita l'uso di MockMvc che ci permette di simulare le chiamate HTTP (GET, POST, ecc..)
@SpringBootTest  // Avvia l'applicazione Spring con tutte le sue componenti
@Transactional  // Non del tutto necessario qua, ma assicura che ogni test sia eseguito in modo transazionale (tutto o niente, modifiche incomplete vengono annullate)
public class CustomerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testLoadCustomers() throws Exception {
        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())  // Verifica che lo stato sia OK (200)
                .andExpect(view().name("customersList"))  // Verifica che la vista sia "customersList"
                .andExpect(model().attribute("customer100", customerRepository.findAll()))  // Verifica che i clienti contenuti in "customers" siano esattamente quelli della chiamata "customerRepository.findAll()"
                .andExpect(model().attribute("customer100", hasSize(3)))  // Verifica il numero di clienti
                .andExpect(model().attribute("customer100", hasItem(hasProperty("name", is("Gino")))));  // Verifica che ci sia un cliente di nome "Gino"
    }

    @Test
    public void testLoadInsertPage() throws Exception {
        mockMvc.perform(get("/customers/insert"))
                .andExpect(status().isOk())  // Verifica che lo stato sia OK (200)
                .andExpect(view().name("insertCustomer"))  // Verifica che la vista sia "insertCustomer"
                .andExpect(model().attributeExists("customer"));  // Verifica che il modello contenga un attributo "customer"
    }

    @Test
    public void testSaveCustomer_Success() throws Exception {
        mockMvc.perform(post("/customers/insert")
                        .param("name", "Gino")
                        .param("surname", "Bartali")
                        .param("age", "70")
                        .param("city", "Firenze")
                        .param("ccNumber", "1234567812345678")
                        .param("ccExpiration", "05/26")
                        .param("ccCVV", "123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/customers"));

        Customer savedCustomer = customerRepository.findBySurnameIgnoreCase("Bartali").get(0);
        assert savedCustomer != null;
        assert savedCustomer.getName().equals("Gino");
        assert savedCustomer.getAge().equals(70);
    }

    @Test
    public void testSaveCustomer_WithErrors() throws Exception {
        mockMvc.perform(post("/customers/insert")
                        .param("name", "Fausto")
                        .param("surname", "Coppi")
                        .param("city", "Novara")
                        .param("ccNumber", "1234567812345678")
                        .param("ccExpiration", "05/26")
                        .param("ccCVV", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name("insertCustomer"))
                .andExpect(model().attributeHasFieldErrors("customer", "age"));

        assert customerRepository.findBySurnameIgnoreCase("Coppi").isEmpty();
    }

    @Test
    public void testLoadCustomersBySurname() throws Exception {
        mockMvc.perform(get("/customers/Rossi"))
                .andExpect(status().isOk())  // Verifica che lo stato sia OK (200)
                .andExpect(view().name("customersList"))  // Verifica che la vista sia "customerList"
                .andExpect(model().attributeExists("customer100"))  // Verifica che il modello contenga "customers"
                .andExpect(model().attribute("customer100", customerRepository.findBySurnameIgnoreCase("Rossi")));  // Verifica che "customer" contenga il risultato della query
    }
}