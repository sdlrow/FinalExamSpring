package com.example.finalexamlibraryvar1kalzhigitovnurbol.request;


import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Author;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Book;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.BookCategory;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {


    private Optional<Set<Book>> books;

    private Optional<String> destination = Optional.of("admin");

}
