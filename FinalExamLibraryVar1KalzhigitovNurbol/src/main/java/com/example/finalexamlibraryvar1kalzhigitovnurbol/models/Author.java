package com.example.finalexamlibraryvar1kalzhigitovnurbol.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "authors")
@Data
@Scope("singleton")
@NoArgsConstructor
@Cacheable
@EntityListeners(AuditingEntityListener.class)
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private @NotBlank String firstName;

    @Column
    private @NotBlank String lastName;

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

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
