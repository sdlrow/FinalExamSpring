package com.example.finalexamlibraryvar1kalzhigitovnurbol.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;

import java.sql.Date;
import java.util.Optional;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordRequest {

    @NotBlank
    private Optional<String> firstName = Optional.empty();

    @NotBlank
    private Optional<String> secondName = Optional.empty();

    @NotBlank
    private Optional<String> middleName = Optional.empty();

    @NotNull
    private Optional<java.time.LocalDate> dateOfBirth = Optional.of(LocalDate.parse("2017-11-15"));

    @NotBlank
    private Optional<String> address = Optional.empty();

}
