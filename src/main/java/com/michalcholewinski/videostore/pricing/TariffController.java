package com.michalcholewinski.videostore.pricing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tariffs")
public class TariffController {

    @Autowired
    private TariffRepository tariffRepository;


    @GetMapping
    public List<Tariff> getTariffs() {
        return tariffRepository.findAll();
    }
}
