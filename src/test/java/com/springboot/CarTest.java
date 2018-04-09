package com.springboot;

import com.springboot.domain.Car;
import com.springboot.mapper.CarMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CarTest {
    @Autowired
    private CarMapper carMapper;

    @Test
    public void find(){
        for(Car car:carMapper.find()){
            System.out.println(car.getName()+" "+car.getPrice());
        }
        System.out.println(carMapper.findById(1));
    }

    @Test
    public void findByParam(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date beginTime = new Date();
        Date endTime = new Date();
        try {
            beginTime = sdf.parse("2017-1-1");
            endTime = sdf.parse("2018-6-6");
            for(Car car:carMapper.findByParam(null,null,null)){
                System.out.println(car.getName()+" "+car.getPrice());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void add(){
        Car car = new Car();
        car.setName("劳斯莱斯");
        car.setPrice(9999d);
        car.setCreateDate(new Date());
       int count = carMapper.add(car);
        System.out.println(count);
    }
}
