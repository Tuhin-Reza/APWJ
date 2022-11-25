package com.controller;

import com.domain.Currency;
import com.domain.LeaveType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.LeaveApplicationService;
import com.service.LeaveTypeService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

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


    @RequestMapping("/currency/rate")
    public Currency rate() throws IOException {
        Currency currency = new Currency();
        currency.setRate(101.2);
        return currency;
    }

    @RequestMapping("/get")
    public LeaveType fetch() {
        LeaveType leaveType = leaveTypeService.get(1L);
        System.out.println(leaveType.getLeave_id() + " " + leaveType.getCategory() + " " + leaveType.getTotal_days());
        return leaveType;
    }

    @RequestMapping("/test")
    public String test() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        LeaveType leaveType = objectMapper.readValue(new ClassPathResource("leave_type.json").getFile(), LeaveType.class);
        System.out.println(leaveType.getCategory() + " " + leaveType.getTotal_days());
        return leaveType.getCategory() + " " + leaveType.getTotal_days();
    }

    @PostMapping(path = "/save")
    public void save(@Valid @RequestBody LeaveType leaveType) {
        System.out.println(leaveType.getCategory());
        System.out.println(leaveType.getTotal_days());
        leaveTypeService.insert(leaveType);
    }


}
