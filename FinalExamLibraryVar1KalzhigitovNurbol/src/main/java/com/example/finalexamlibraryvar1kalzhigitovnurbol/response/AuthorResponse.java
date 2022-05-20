package com.example.finalexamlibraryvar1kalzhigitovnurbol.response;

import javax.validation.constraints.NotBlank;

public class AuthorResponse {
    private Long id;
    private String firstName;
    private String lastName;

    public AuthorResponse(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
