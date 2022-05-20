package com.example.finalexamlibraryvar1kalzhigitovnurbol.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddAuthorRequest {
    @NotBlank
    private Optional<String> firstName = Optional.empty();

    @NotBlank
    private Optional<String> lastName = Optional.empty();

}
