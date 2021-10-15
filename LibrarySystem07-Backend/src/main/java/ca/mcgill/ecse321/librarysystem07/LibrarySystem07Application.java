package ca.mcgill.ecse321.librarysystem07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class LibrarySystem07Application {

	public static void main(String[] args) {
		SpringApplication.run(LibrarySystem07Application.class, args);
	}
	
	@RequestMapping("/")
  	public String greeting(){
    		return "Hello world!";
  	}

}
