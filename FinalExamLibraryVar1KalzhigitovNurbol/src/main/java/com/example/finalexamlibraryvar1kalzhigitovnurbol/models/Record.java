package com.example.finalexamlibraryvar1kalzhigitovnurbol.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "record")
@Data
@Scope("singleton")
@NoArgsConstructor
@Cacheable
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column
    @NotBlank
    private String firstName;

    @Column
    @NotBlank
    private String secondName;

    @Column
    @NotBlank
    private String middleName;

    @Column
    @NotNull
    @Basic
    @Past
    private java.time.LocalDate dateOfBirth;

    @Column
    private String address;

    @Lob
    private byte[] picture;

    public Record(User user, String firstName, String secondName, String middleName, java.time.LocalDate dateOfBirth, String address) {
        this.user = user;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }
}
