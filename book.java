package com.MongoDB.crud;


import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "Book")
public class Book {
	@Id
	private int id;
	private String bookName;
	private String Authorname;
	
	public Book(int id,String bookName , String Authorname) {
		this.setId(id);
		this.bookName=bookName;
		this.Authorname=Authorname;
		
	}
	
	
	
	
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthorname() {
		return Authorname;
	}
	public void setAuthorname(String authorname) {
		Authorname = authorname;
	}





	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}
	
	

}
--------------------------------------------------------------------------------------------------------------------------------------

package com.MongoDB.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

}
	
  -------------------------------------------------------------------------------------
  
  package com.MongoDB.crud;


import org.springframework.data.mongodb.repository.MongoRepository;



public interface BookRepository extends MongoRepository<Book, Integer>{
	
}

---------------------------------------------------------------------------------
package com.MongoDB.crud;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class BookController {
	
	@Autowired
	private BookRepository repository;
	 
	@PostMapping("/addbook")
	public String saveBook(@RequestBody Book book) {
		repository.save(book);
		return "Book added at id" + book.getId()
;	}
	
	@GetMapping("/findAllBooks")
	public List<Book> getBooks(){
		return repository.findAll();
	}
	
	@GetMapping("/findAllBooks/{id}")
	public Optional<Book> getBooks(@PathVariable int id){
		return repository.findById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteBook(@PathVariable int id) {
		repository.deleteById(id);
		return "book deleted at id" + id;
	}
	

}
-----------------------------------------------------------------

spring:
  data:
    mongodb:
      database: BookStore
      host: localhost
      port: 27017
