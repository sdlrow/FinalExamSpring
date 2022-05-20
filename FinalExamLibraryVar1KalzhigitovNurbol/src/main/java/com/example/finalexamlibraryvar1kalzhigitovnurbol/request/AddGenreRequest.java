package com.example.finalexamlibraryvar1kalzhigitovnurbol.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddGenreRequest {
    @NotBlank
    private Optional<String> title  = Optional.empty();

    @NotBlank
    private Optional<String> description  = Optional.empty();

}