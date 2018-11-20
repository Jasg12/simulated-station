package com.sjsu.cmpe.street.simulatedstation.controller;


import com.sjsu.cmpe.street.simulatedstation.Entity.Sensor;
import com.sjsu.cmpe.street.simulatedstation.service.SensorService;
import com.sjsu.cmpe.street.simulatedstation.service.SnodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.lang.String;

@Controller
@RestController
@RequestMapping("/Id")
public class SNodeController {

    @Autowired
    private SnodeService snodeService = new SnodeService();
    private int id;
//Get sensor value through id
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getSensorvalue(@PathVariable("id") int id){
        Random rannum= new Random();
        int x = rannum.nextInt(35);
        int y = rannum.nextInt(7);
        return ("Temperature in Degrees celsius is "+x+ " temperature in Fahrenheit is "+(9*x/5)+32+
                " Wind speed is "+y+" Miles/hour");
        }


        //Get all sensor data in a Smart Node by Smart node id
        @RequestMapping(value = "/Snode/{id}", method = RequestMethod.GET)
        public String getAllSensorvalue(@PathVariable("id") int id) {
            Random ran = new Random();
            int x = ran.nextInt(35);
            int y = ran.nextInt(7);
            int z = ran.nextInt(30)+50;
            return ("Temperature in Degrees celsius in sensor1 is " + x +
                    " temperature in Fahrenheit in sensor 2 is " + ((9 * x) / 5) + 32 +
                    " Wind speed in sensor 3 is " + y + " Miles/hour"+
                    " Humidity in sensor 4 is "+z+"%");
        }

        //Get all sensor node data

        @RequestMapping(path = "/AllSensorNodes", method = RequestMethod.GET)
        public String getAllSensorNodes() {
            Random ran = new Random();
            int x = ran.nextInt(35);
            int y = ran.nextInt(7);
            int z = ran.nextInt(35);
            int a = ran.nextInt(7);
            int b = ran.nextInt(35);
            int c = ran.nextInt(7);
            int a1 = ran.nextInt(7);
            int b1 = ran.nextInt(35);
            int c1 = ran.nextInt(7);
            String text = "Temperature in Degrees celsius in sensor 1 is "+ x + " temperature in Fahrenheit in sensor 1 is " + (((9 * x) / 5) + 32) +
                    " Wind speed in sensor 2 is " + y + " Miles/hour\n" ;
            String text1 = "Temperature in Degrees celsius in sensor 3 is " + x +
                    " temperature in Fahrenheit in sensor 3 is " + (((9 * x) / 5) + 32) +
                    " Wind speed in sensor 4 is " + y + " Miles/hour";
            String text3 = "Temperature in Degrees celsius in sensor 5 is " + x +
                    " temperature in Fahrenheit in sensor  is " + (((9 * x) / 5) + 32) +
                    " Wind speed in sensor 6 is " + y + " Miles/hour";
            return text+text1+text3;
        }


//Get sensor status through id
    @RequestMapping(value = "/status/{id}", method = RequestMethod.GET)
    public String getStatus(@PathVariable("id") int id){
        return("True");
    }
    }


