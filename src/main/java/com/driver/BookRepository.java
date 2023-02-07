package com.driver;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    private Map<Integer,Book> idBookMapping;
    private Map<String,List<Book>>authorBookMapping;
    private Map<String,List<Book>> genreBookMapping;
    private List<Book> bookList;
    private int id;

    public BookRepository(){
        this.idBookMapping= new HashMap<>();
        this.authorBookMapping= new HashMap<>();
        this.genreBookMapping= new HashMap<>();
        this.bookList= new ArrayList<>();
        this.id=0;
    }

    public Book save(Book book){
        this.id ++;
        this.idBookMapping.put(id,book);
        List<Book> bookAuthorList=authorBookMapping.getOrDefault(book.getAuthor(),new ArrayList<>());
        bookAuthorList.add(book);
        this.authorBookMapping.put(book.getAuthor(),bookAuthorList);
        List<Book> bookGenreList=genreBookMapping.getOrDefault(book.getGenre(),new ArrayList<>());
        bookGenreList.add(book);
        this.genreBookMapping.put(book.getGenre(),bookGenreList);
        bookList.add(book);
        return book;
    }

    public Book findBookById(int id){
        if(idBookMapping.containsKey(id))
            return idBookMapping.get(id);
        return null;
    }

    public List<Book> findAll(){
        return bookList;
    }

    public void deleteBookById(int id){
        if(!idBookMapping.containsKey(id))
         return ;
        Book book=idBookMapping.get(id);
        //removing book from all database
        idBookMapping.remove(id);
        //removing book from author book database
        String author=book.getAuthor();
        authorBookMapping.get(author).remove(book);
        //removing book from genre book database
        String genre=book.getGenre();
        genreBookMapping.get(genre).remove(book);

        bookList.remove(book);
    }

    public void deleteAll(){
        idBookMapping.clear();
        authorBookMapping.clear();
        genreBookMapping.clear();
        bookList.clear();
        return;
    }

    public List<Book> findBooksByAuthor(String author){
        return authorBookMapping.getOrDefault(author, null);
    }

    public List<Book> findBooksByGenre(String genre){
        return genreBookMapping.getOrDefault(genre,null);
    }
}
