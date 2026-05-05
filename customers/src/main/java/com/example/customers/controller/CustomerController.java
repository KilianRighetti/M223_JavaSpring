package com.example.customers.controller;

import com.example.customers.model.Customer;
import com.example.customers.service.CustomerService;
import com.example.customers.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// RequestMapping mette sempre nel percorso "/customers"
@RequestMapping("/customers")
@Controller
public class CustomerController {

    private final ReservationService reservationService;
    // Il controller NON parla direttamente col database.
    // --> Usa il SERVICE come "intermediario"
    private CustomerService customerService;

    // Dependency Injection:
    // Spring crea CustomerService e lo passa al controller
    @Autowired
    public CustomerController(CustomerService customerService, ReservationService reservationService) {
        this.customerService = customerService;
        this.reservationService = reservationService;
    }

//    @GetMapping
//    public String loadCustomers(Model model) {
//        model.addAttribute("customers", customerRepository.findAll());
//        return "customerList";
//    }

    @GetMapping
    public String getCustomers(Model model, @RequestParam(value = "id", required = false) Long customerId) {
        if (customerId == null) {
            model.addAttribute("customer100", customerService.getAllCustomers()); // MODEL passa dati alla view
        } else {
            model.addAttribute("customer100", customerService.findById(customerId));
        }
        // Ritorna il nome della view da mostrare (Es: pagina "customersList")
        return "customersList";
    }

//    @GetMapping("/{id}")
//    public String getCustomerPathId(Model model, @PathVariable("id") Long customerId) {
//
//        List<Customer> filteredCustomers = List.of(customerRepository.findById(customerId).orElse(null));
//
//        model.addAttribute("customer100", filteredCustomers);
//        return "customersList";
//    }

    @GetMapping("/insert")
    public String loadInsertPage(@ModelAttribute Customer customer) {
        return "insertCustomer";
    }

    @PostMapping("/insert")
    public String saveCustomer(@Valid Customer customer, Errors errors) {

        // @Valid dice a Spring di controllare i vincoli (NotBlank, Size, Min, Max ...) nel MODEL
        // >> Se ci sono errori, si rimane nel form (Pagina "insertCustomer")
        if(errors.hasErrors()){
            return "insertCustomer";
        }

        customerService.save(customer); // passa il Customer al SERVICE
        // redirect = nuova richiesta GET su /customers
        // utile per evitare il re-invio del form aggiornando la pagina
        return "redirect:/customers";
    }

    @GetMapping("/{surnameToFilter}")
    public String getCustomerBySurname(Model model, @PathVariable("surnameToFilter") String surnameToFilter) {

        // Il controller chiede al SERVICE di usare un suo metodo per filtrare (cognome, NO case sensitive)
        List<Customer> filteredCustomers = customerService.findBySurnameIgnoreCase(surnameToFilter);

        // Passa la lista alla view
        model.addAttribute("customer100", filteredCustomers);
        return "customersList";
    }

    @GetMapping("/reservations")
    public String getReservations(Model model) {
        model.addAttribute("reservation100", reservationService.getAllReservations()); // MODEL passa dati alla view
        return "reservationList";
    }

    /*
    * Questa sezione di codice è fondamentale per permettere la MODIFICA di dati già esistenti.
    *   > INSERIMENTO: viene passato al front-end un oggetto vuoto
    *   > MODIFICA: passo un oggetto con dati già presenti nel DB
    *
    *  [Customer customerToEdit] e` necessario per passare l'id del customer.
    *   > La View (front-end) non contiene infatti l'ID, quindi devo passarlo in back-end
    *     Senza questa riga, l'id sarebbe nullo
     */
    @GetMapping("/edit/{customerId}")
    public String loadEditPage(@ModelAttribute Customer customer, Model model,
                               @PathVariable Long customerId) {

        Customer customerToEdit = customerService.findById(customerId);
        model.addAttribute("customer", customerToEdit);
        return "insertCustomer";
    }


    // Questo metodo visualizzerà i dati di 'customerToEdit' del metodo 'loadEditPage'. Il form verrà popolato con quei dati.
    @PostMapping("/edit/{customerId}")
    public String updateCustomer(@Valid Customer customer, Errors errors,
                                 @PathVariable Long customerId) {
        customer.setId(customerId);
        if (errors.hasErrors()) {
            return "insertCustomer";
        } else {
            customerService.save(customer); // Salva sul DB
            return "redirect:/customers";
        }
    }


//    @GetMapping("/customerbycity")
//    public String getCustomerByCity(Model model, @RequestParam("city") String city) {
//
//        List<Customer> filteredCustomers = customerService.findByCityIgnoreCase(city);
//
//        // Passa la lista alla view
//        model.addAttribute("customer100", filteredCustomers);
//        return "customersList";
//    }
}