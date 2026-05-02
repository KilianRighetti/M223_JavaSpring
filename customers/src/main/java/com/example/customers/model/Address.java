package com.example.customers.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

// Questa classe verrà usata da Customer per sostituire l'attributo String che era l'indirizzo
/* [È ALLO STESSO TEMPO]
*    > Delle classi di dominio se le vediamo in ottica Java
*    > Delle entity se le vediamo in ottica JPA/Hibernate
*    > Delle tabelle se le vediamo in ottica database relazionale
*/

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    @SequenceGenerator(name = "address_seq", allocationSize = 1)
    Long id;

    @NotBlank
    @Size(min = 1, max = 30, message = "Lunghezza consentita tra 3 e 30 caratteri")
    String street;

    @NotBlank
    @Size(min = 1, max = 5, message = "Consentiti numeri da 1 a 5 cifre")
    String num;

    @NotBlank
    @Size(min = 4, max = 7, message = "Lunghezza consentita tra 4 a 7 cifre")
    String zip;

    @NotBlank
    @Size(min = 3, max = 30, message = "Lunghezza consentita tra 3 a 30 cifre")
    String city;

    @NotBlank
    @Size(min = 3, max = 30, message = "Lunghezza consentita tra 3 a 30 cifre")
    String nation;
}