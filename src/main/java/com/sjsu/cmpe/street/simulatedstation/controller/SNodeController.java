package com.sjsu.cmpe.street.simulatedstation.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@Controller
@RestController
@RequestMapping("/Id")
public class SNodeController
{
    @RequestMapping(value = "")
    public int getSensorValue(){
        Random rannum= new Random();
        int x = rannum.nextInt(98);
        return x;
        }
    }
