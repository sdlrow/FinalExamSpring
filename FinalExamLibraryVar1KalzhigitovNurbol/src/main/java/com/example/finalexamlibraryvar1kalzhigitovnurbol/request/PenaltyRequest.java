package com.example.finalexamlibraryvar1kalzhigitovnurbol.request;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public class PenaltyRequest {

    @NotNull
    private Optional<String> username;

    @NotNull
    private Optional<String> name;

    @NotNull
    private Optional<String> description;

    public Optional<String> getUsername() {
        return username;
    }

    public void setUsername(Optional<String> username) {
        this.username = username;
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    public Optional<String> getDescription() {
        return description;
    }

    public void setDescription(Optional<String> description) {
        this.description = description;
    }
}
