package ru.kpfu.itis.ovchinnikov.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.ovchinnikov.services.CarService;
import ru.kpfu.itis.ovchinnikov.services.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dmitry on 25.05.2017.
 */

@Component
public class Validator {

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    public String rentCarValidate(String name, String number, String car, String startDate, String returnDate){
        if(name.equals("")||number.equals("")||car.equals("")||startDate.equals("")||returnDate.equals("")){
            return "All fields required";
        }
        Pattern p = Pattern.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");
        Matcher m = p.matcher(number);
        if(!m.matches()){
           return "Incorrect number";
        }
        if(carService.getCar(car)==null){
            return "Incorrect car";
        }
        Date datestart;
        Date dateReturn;
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        try {
            datestart = format.parse(startDate);
            System.out.println(datestart);
            dateReturn = format.parse(returnDate);
            System.out.println(dateReturn);
        }catch (Exception e){
            return "Incorrect Date";
        }
        System.out.println(new Date(System.currentTimeMillis()));
        if(datestart.getTime()>=dateReturn.getTime()||datestart.getTime()<new Date(System.currentTimeMillis()).getTime()){
            return "Incorrect Dates";
        }
        return "Success";
    }

    public String editCarValidate(String model, String mileage, String year, String power, String cost){
        if(model.equals("")||mileage.equals("")||year.equals("")||power.equals("")||cost.equals("")){
            return "All fields required";
        }
        try{
            Integer.parseInt(mileage);
        }catch (Exception e){
            return "Incorrect mileage";
        }

        try{
            int yearInt = Integer.parseInt(year);
            if(yearInt>2017||yearInt<1960){
                return "Incorrect year";
            }
        }catch (Exception e){
            return "Incorrect year";
        }
        try{
            Integer.parseInt(year);
        }catch (Exception e){
            return "Incorrect year";
        }

        try{
            Integer.parseInt(cost);
        }catch (Exception e){
            return "Incorrect cost";
        }
        return "Success";
    }

}
