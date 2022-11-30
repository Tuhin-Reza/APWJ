package com.controller;

import com.domain.Currency;
import com.domain.LeaveApplication;
import com.domain.LeaveType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.LeaveApplicationService;
import com.service.LeaveTypeService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/HelloController")
public class HomeController {
    private LeaveTypeService leaveTypeService;
    private LeaveApplicationService leaveApplicationService;

    public HomeController(LeaveApplicationService leaveApplicationService,
                           LeaveTypeService leaveTypeService) {
        this.leaveApplicationService = leaveApplicationService;
        this.leaveTypeService = leaveTypeService;
    }


    @CrossOrigin
    @RequestMapping("/currency/rate")
    public Currency rate() throws IOException {
        Currency currency = new Currency();
        currency.setRate(101.2);
        return currency;
    }
    @CrossOrigin
    @GetMapping("/currency/rate/from/{from}/to/{to}")
    public Currency rateWithParameter(@PathVariable("from") String from, @PathVariable("to") String to) throws IOException {
        Currency currency = new Currency();

        if (from.equals("USD") && to.equals("BDT")) {
            currency.setRate(101.2);
        }
        else if (from.equals("BDT") && to.equals("USD")) {
            currency.setRate(0.12);
        }
        else {
            currency.setRate(0);
        }
        return currency;
    }

    @CrossOrigin
    @GetMapping("m1")
    public String m1(){
        return "I am Working";
    }


//    @RequestMapping("/get")
//    public LeaveType fetch() {
//        LeaveType leaveType = leaveTypeService.get(1L);
//        System.out.println(leaveType.getLeave_id() + " " + leaveType.getCategory() + " " + leaveType.getTotal_days());
//        return leaveType;
//    }
//
//    @RequestMapping("/test")
//    public String test() throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        LeaveType leaveType = objectMapper.readValue(new ClassPathResource("leave_type.json").getFile(), LeaveType.class);
//        System.out.println(leaveType.getCategory() + " " + leaveType.getTotal_days());
//        return leaveType.getCategory() + " " + leaveType.getTotal_days();
//    }
//
//    @PostMapping(path = "/save")
//    public void save(@Valid @RequestBody LeaveType leaveType) {
//        System.out.println(leaveType.getCategory());
//        System.out.println(leaveType.getTotal_days());
//        leaveTypeService.insert(leaveType);
//    }


}
