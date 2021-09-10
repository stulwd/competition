package com.challenge;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        InputStream input = App.class.getResourceAsStream("/input.json");
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        InputBean dataBean = mapper.readValue(input, InputBean.class);
//        System.out.println("data: " + dataBean);
        RateCalculator rateCalculator = new RateCalculator(dataBean);
        long totalRate = rateCalculator.calculateTotalRate(dataBean.shift.start, dataBean.shift.end);
        System.out.println("rate=" + totalRate);
    }


}

class RateCalculator {
    InputBean dataBean;
    public RateCalculator(InputBean input) {
        dataBean = input;
    }

    public long calculateTotalRate(LocalDateTime from, LocalDateTime end){
        LocalDateTime itr = from;
        int totalAmount = 0;
        int WorkedMinutes = 0;
        while (itr.isBefore(end)){
            totalAmount += calculateCurrentMinuteRate(itr);
            itr = itr.plusMinutes(1);
            WorkedMinutes++;
            if (WorkedMinutes >= 60 * 8){
                WorkedMinutes = 0;
                itr = itr.plusHours(1);
            }
        }
        return totalAmount;
    }

    public long calculateCurrentMinuteRate(LocalDateTime currentDateTime){
        DayOfWeek dayOfWeek = currentDateTime.getDayOfWeek();
        int hour = currentDateTime.getHour();
        if (isStandardDay(dayOfWeek) == true){
            if (isDay(hour) == true){
                return dataBean.roboRate.standardDay.value;
            }else {
                return dataBean.roboRate.standardNight.value;
            }
        }else {
            if (isDay(hour) == true){
                return dataBean.roboRate.extraDay.value;
            }else {
                return dataBean.roboRate.extraNight.value;
            }
        }
    }

    public boolean isStandardDay(DayOfWeek day){
        if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY){
            return false;
        }
        return true;
    }

    public boolean isDay(int hour){
        if (hour >= 7 && hour <= 22){
            return true;
        }
        return false;
    }
}
