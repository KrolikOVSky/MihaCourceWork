package com.application.backEnd;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Books {
    private List<Book> books;

    public Books() {
        this.books = new ArrayList<Book>();
    }

    public Books(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Book getById(Long id){
        for (var el : books) {
            if (el.getId() == id) return el;
        }
        return null;
    }

    public boolean add(Book book){
        if (getById(book.getId()) != null) return false;
        return books.add(book);
    }

    public boolean remove(long id) {
        if (books == null || getById(id) == null) return false;
        return books.remove(getById(id));
    }

    public int getLength() {
        return books.size();
    }

    public Books sortById(){
        Comparator<Book> comparator = Comparator.comparing(Book::getId).thenComparing(Book::getQuantity).reversed();
        Books books = new Books(this.books);
        books.books.sort(comparator);
        return books;
    }

    public Books filter(String vendorCode){
        Books books = new Books();
        for (var el : this.books) {
            if(el.getVendorCode().equals(vendorCode)){
                books.add(el);
            }
        }
        return books;
    }
}
