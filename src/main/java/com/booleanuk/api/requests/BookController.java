package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("books")
public class BookController {
    private ArrayList<Book> books;

    public BookController() {
        this.books = new ArrayList<>();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        this.books.add(book);

        return book;
    }

    @GetMapping
    public ArrayList<Book> getAll() {
        return books;
    }

    @GetMapping("/{id}")
    public Book getOne(@PathVariable int id) {
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getId() == id) {
                return books.get(i);
            }
        }
        return null;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable int id, @RequestBody Book book) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                books.get(i).setTitle(book.getTitle());
                books.get(i).setGenre(book.getGenre());
                books.get(i).setAuthor(book.getAuthor());
                books.get(i).setNumPages(book.getNumPages());
                return books.get(i);
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Book delete(@PathVariable int id) {
        Book deletedBook;
        for(int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                deletedBook = books.get(i);
                books.remove(i);
                return deletedBook;
            }
        }
        return null;
    }
}
