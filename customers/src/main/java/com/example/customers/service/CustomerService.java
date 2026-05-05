package com.example.customers.service;
import com.example.customers.data.CustomerRepository;
import com.example.customers.model.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service dice a Spring che questa classe appartiene al livello service,
// cioè il livello che contiene la logica applicativa.
// ===> [!!!] Questo SERVICE fa da tramite tra CONTROLLER e REPOSITORY
@Service
public class CustomerService {

    // Il service usa il repository per accedere ai dati nel database
    private CustomerRepository customerRepository;

    // Dependency Injection:
    // Spring crea il repository e lo passa al service
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Metodo che recupera tutti i clienti
    public List<Customer> getAllCustomers() {
        /*
         * Qui il service delega al repository la lettura di tutti i record.
         * customerRepository.findAll() è un metodo tipico di Spring Data JPA
         * che prende tutti gli oggetti Customer dal database.
         */
        return customerRepository.findAll();
    }

    // Metodo che cerca un cliente per id
    public Customer findById(Long customerId) {
        /*
         * Il SERVICE passa l'id al REPOSITORY.
         * "findById" di solito restituisce un Optional<Customer>,
         * cioè un contenitore che può avere un Customer oppure essere vuoto.
         */
        return customerRepository.findById(customerId).orElseThrow();
    }

    // Metodo che salva un cliente nel database
    public void save(@Valid Customer customer) {
        /*
         * 1) Il CONTROLLER riceve il Customer dal form
         * 2) Il CONTROLLER lo passa al SERVICE.
         * 3) Il SERVICE poi chiama "save" sul REPOSITORY.
         *
         * save(...) in JPA:
         *  >> Se il customer è nuovo -> INSERT nel database
         *  >> Se ha un id esistente -> UPDATE
         */
        customerRepository.save(customer);
    }

    // Metodo che cerca clienti per cognome ignorando maiuscole/minuscole
    public List<Customer> findBySurnameIgnoreCase(String surnameToFilter) {
        /*
         * Metodo custom del REPOSITORY.
         * (Spring Data JPA capisce il nome del metodo e genera la query)
         */
        return customerRepository.findBySurnameIgnoreCase(surnameToFilter);
    }

//    public List<Customer> findByCityIgnoreCase(String city) {
//        // Delega al REPOSITORY
//        return customerRepository.findByCityIgnoreCase(city);
//    }

}