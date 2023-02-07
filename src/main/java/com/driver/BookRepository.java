package com.driver;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    private Map<Integer,Book> db;
    private int id;

    public BookRepository(){
        db=new HashMap<>();
        id=0;
    }

    public Book save(Book book){
        id++;
        book.setId(id);
        db.put(id,book);
        return book;
    }

    public Book findBookById(int id){
         return db.getOrDefault(id,null);
    }

    public List<Book> findAll(){
        Set<Integer> keySet=db.keySet();
        List<Book> bookList=new ArrayList<>();
        for(int id : keySet){
            bookList.add(db.get(id));
        }
        return bookList;
    }

    public void deleteBookById(int id){
        db.remove(id);
    }

    public void deleteAll(){
        db.clear();
    }

    public List<Book> findBooksByAuthor(String author){
        Set<Integer> keySet=db.keySet();
        List<Book> bookList=new ArrayList<>();
        for(int id :keySet){
            if(db.get(id).getAuthor().equals(author))
                bookList.add(db.get(id));
        }
        return bookList;
    }

    public List<Book> findBooksByGenre(String genre){
        Set<Integer> keySet=db.keySet();
        List<Book> bookList=new ArrayList<>();
        for(int id :keySet){
            if(db.get(id).getGenre().equals(genre))
                bookList.add(db.get(id));
        }
        return bookList;
    }
}
