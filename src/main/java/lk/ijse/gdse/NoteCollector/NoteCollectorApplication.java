package lk.ijse.gdse.NoteCollector;

import org.modelmapper.ModelMapper;
import  org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NoteCollectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteCollectorApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return  new ModelMapper();
	}
}
