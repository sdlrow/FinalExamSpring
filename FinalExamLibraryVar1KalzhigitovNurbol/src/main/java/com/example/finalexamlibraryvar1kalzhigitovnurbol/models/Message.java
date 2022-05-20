package com.example.finalexamlibraryvar1kalzhigitovnurbol.models;

import com.example.finalexamlibraryvar1kalzhigitovnurbol.request.MessageRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "message")
@Data
@Scope("singleton")
@NoArgsConstructor
@Cacheable
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))

    private Set<Book> books = new HashSet<Book>();

    @Column
    @NotBlank
    private String destination;

    @Column
    @NotBlank
    private String status;

    @Column
    private Double payment;

    @Column
    private String description;

    @Column
    private Long answer;

    public Message(User user, Set<Book> books, String destination, String status) {
        this.user = user;
        this.books = books;
        this.destination = destination;
        this.status = status;
    }

    public Message(String destination, String status, Double payment, String description, Long answer) {
        this.destination = destination;
        this.status = status;
        this.payment = payment;
        this.description = description;
        this.answer = answer;
    }
}
