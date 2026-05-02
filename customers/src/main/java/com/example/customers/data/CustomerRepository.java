package com.example.customers.data;

import com.example.customers.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// @Repository indica che questa interfaccia appartiene al livello di accesso ai dati
// e si occupa di comunicare con il database
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /*
     * JpaRepository<Customer, Long> fornisce automaticamente molti metodi pronti,
     * ad esempio:
     *
     * - findAll()        -> restituisce tutti i Customer
     * - findById(id)     -> cerca un Customer per chiave primaria
     * - save(customer)   -> salva o aggiorna un Customer
     * - deleteById(id)   -> elimina un Customer per id
     *
     * Quindi non serve riscriverli.
     */

    // [ METODI CUSTOM ]
    List<Customer> findBySurnameIgnoreCase(String surname);
    // List<Customer> findByCityIgnoreCase(String city);
}