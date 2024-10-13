package lk.ijse.gdse.NoteCollector.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// app eka hriytma run wenwad kiyl blanwa.parameters pass wenne na

@RestController
@RequestMapping("api/v1/healthtest")
public class HealthTestController {
    @GetMapping
    public String healthTest(){
        return "Note Controller app run Successfully";
    }
}
