package com.example.finalexamlibraryvar1kalzhigitovnurbol.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageAdmin {

    private Optional<Long> id;


    @NotNull
    private Optional<Double> payment;

    private Optional<String> description = Optional.of("Please do not hesitate to contact us if you require any further assistance.");

    @NotBlank
    private Optional<String> destination;
}
