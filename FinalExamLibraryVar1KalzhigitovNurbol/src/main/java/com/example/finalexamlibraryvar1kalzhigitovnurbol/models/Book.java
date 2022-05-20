package com.example.finalexamlibraryvar1kalzhigitovnurbol.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
@Data
@Scope("singleton")
@NoArgsConstructor
@Cacheable
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Audited
    private Long id;

    @Column
    private
//    @NotBlank
    String title;

    @Column(columnDefinition = "TEXT")
    private
//    @NotBlank
    String description;

    @Column
    private
//    @NotBlank
    String imageURL;

    @Column
    private
//    @NotBlank
    String publication;

    @Column
    private
//    @NotNull
    Integer count;

    @Column
//    @NotBlank
    private String binding;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))

    private Set<Genre> genres = new HashSet<Genre>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))

    private Set<Author> authors = new HashSet<Author>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookCategory_id")
    private BookCategory bookCategory;

    private Double cost;

    @CreatedBy
    @Column
    private String createdBy;

    @CreatedDate
    @Column
    private LocalDateTime created;

    @LastModifiedBy
    @Column
    private String modifiedBy;

    @LastModifiedDate
    @Column
    private LocalDateTime modified;

    public Book(String title, String description, String imageURL, String publication, Integer count, String binding, Set<Genre> genres, Set<Author> authors, BookCategory bookCategory, Double cost) {
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
        this.publication = publication;
        this.count = count;
        this.binding = binding;
        this.genres = genres;
        this.authors = authors;
        this.bookCategory = bookCategory;
        this.cost = cost;
    }


}
