package com.example.finalexamlibraryvar1kalzhigitovnurbol.response;

import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Author;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.BookCategory;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Genre;

import java.util.Set;

public class BookResponse {
    private Long id;
    private String title;
    private String description;
    private String imageURL;
    private String publication;
    private Integer count;
    private String binding;
    private Set<GenreResponse> genres;
    private Set<AuthorResponse> authors;
    private BookCategoryResponse bookCategory;
    private Double cost;

    public BookResponse(Long id, String title, String description, String imageURL, String publication, Integer count, String binding, Set<GenreResponse> genres, Set<AuthorResponse> authors, BookCategoryResponse bookCategory, Double cost) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
        this.publication = publication;
        this.count = count;
        this.binding = binding;
        this.genres = genres;
        this.authors = authors;
        this.bookCategory = bookCategory;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public Set<GenreResponse> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreResponse> genres) {
        this.genres = genres;
    }

    public Set<AuthorResponse> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorResponse> authors) {
        this.authors = authors;
    }

    public BookCategoryResponse getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategoryResponse bookCategory) {
        this.bookCategory = bookCategory;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
