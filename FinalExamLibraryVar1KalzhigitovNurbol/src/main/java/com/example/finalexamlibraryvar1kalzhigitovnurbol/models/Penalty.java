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
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "penalty")
@Data
@Scope("singleton")
@NoArgsConstructor
@Cacheable
@EntityListeners(AuditingEntityListener.class)
public class Penalty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String name;

    @Lob
    @Column(columnDefinition = "TEXT")
    private @NotBlank String description;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

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

    public Penalty(String name, String description, User user) {
        this.name = name;
        this.description = description;
        this.user = user;
    }
}
