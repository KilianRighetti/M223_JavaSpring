package com.example.customers.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Reservation {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE,
            generator = "reservation_seq")
    @SequenceGenerator (name = "reservation_seq", allocationSize = 1)
    Long id;

    @ManyToOne // Join many to one con l'Entity Customer
    @JoinColumn(name = "customer_id", nullable = false)
    Customer customer;

    // Si vuole avere una relazione e navigazione bidirezionale tra Customer e Reservation
    // in modo che sia possibile navigare gli oggetti in entrambe le direzioni.
    //  >> [@ManyToOne] in Reservation
    //  >> [@OneToMany] in Customer
    @NotBlank
    @Size(min = 1, max = 3, message = "Numero camera deve essere tra 1 e 999")
    String room;

    @NotBlank
    @Size(min = 8, max = 16, message = "Ora del checkin tra 8 e 16 caratteri")
    String checkin;

    @Size(min = 8, max = 16, message = "Ora del checkout tra 8 e 16 caratteri")
    String checkout;
}