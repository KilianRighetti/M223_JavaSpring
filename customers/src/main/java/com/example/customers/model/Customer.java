package com.example.customers.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.util.List;

@Data // Lombok genera automaticamente getter, setter, toString, equals, hashCode ...
@Entity // dice a JPA che questa classe rappresenta una tabella del database
public class Customer {

    @Id
    // Generazione automatica dell'id tramite una sequence del DATABASE
    // >>> Vedere file ".sql"
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name="customer_seq", sequenceName = "customer_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 20, message = "Lunghezza consentita: tra 2 e 20 caratteri")
    private String name;

    @NotBlank
    @Size(min = 2, max = 20, message = "Lunghezza consentita: tra 2 e 20 caratteri")
    private String surname;

    @NotNull
    @Min(18)
    @Max(122)
    private Integer age;

    /* Qui creo un oggetto che REFERENZIA Address
     * [@OneToOne] funge da relazione. Riferimento all' Entity Address
     *   > cascade: Propaga l'esecuzione di un'azione (modifica, eliminazione) a "cascata"
     *              Di default è nullo.
     *   > CascadeType: Tipo di propagazione (ALL, PERSIST, MERGE, REMOVE, REFRESH, DETACH)
     *
     *  [@JoinColumn] serve a specificare i dettagli della relazione e può definire gli attributi.
     *   > name: Il nome della chiave esterna nella tabella corrente.
     *           Se non specificato, Spring lo deduce dal nome dell'attributo (es. user → user_id)
     *   > referencedColumnName: Il nome della chiave primaria nella tabella referenziata.
     *                           Di default cerca il campo id e se non lo trova genera un errore.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @Valid
    private Address address;

    /* Customer è la classe principale che referenzia una lista di Reservation (classe dipendente, senza vita propria)
     *  [@ToString.Exclude] Esclude il ToString delle reservation
     *   > Evitare il loop dei "toString" che si invocano a vicenda e generano dipendenza circolare
     *
     *  [@OneToMany] funge da composizione.
     *   > mappedBy: Indica a JPA che la relazione è già gestita dalla FK customer definita sul lato @ManyToOne
     *               Evita la creazione di tabelle join superflue.
     */
    @ToString.Exclude
    @OneToMany (mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    /*
    @NotBlank
    //@CreditCardNumber
    private String ccNumber;

    @NotBlank
    @Pattern(
            regexp = "^(0[1-9]|1[0-2])\\/\\d{2}$",
            message = "Formato valido: MM/YY (Es: 05/26)"
    )
    private String ccExpiration;

    @NotBlank
    @Digits(integer = 3, fraction = 0)
    private String ccCVV;
     */
}