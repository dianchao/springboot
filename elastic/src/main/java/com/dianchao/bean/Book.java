package com.dianchao.bean;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "dianchao", type = "book")
public class Book {
    private Integer id;
    private String bookName;
    private String author;

    public Book(){}

    public Book(Integer id, String bookName, String author) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
