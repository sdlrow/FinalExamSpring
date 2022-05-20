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
@Table(name = "booksCategory")
@Data
@Scope("singleton")
@NoArgsConstructor
@Cacheable
@EntityListeners(AuditingEntityListener.class)
public class BookCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private @NotBlank String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private @NotBlank String description;

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

    public BookCategory(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
