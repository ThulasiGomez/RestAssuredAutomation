package Pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.util.Arrays;

public class Employee
{
    @Test
    void run() throws JsonProcessingException {
        Sample s=new Sample();
        s.setFirstName("Ram");
        s.setLastName("Hello");
        s.setEmail("hello@gmail.com");
        s.setSkills(Arrays.asList("java","Selenium"));

        System.out.println(s.getFirstName());
        System.out.println(s.getLastName());
        System.out.println(s.getEmail());
        System.out.println(s.getSkills());

        ObjectMapper mapper=new ObjectMapper();
        String emp=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(s);  //Serializing
        System.out.println(emp);

    }
}
