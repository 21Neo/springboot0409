package com.springboot.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.domain.Car;
import com.springboot.domain.CustomType;
import com.springboot.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
// 跨域
@CrossOrigin(origins = "http://127.0.0.1:8080",maxAge = 3600)
@RequestMapping("/api")
public class CarRestController {
    @Autowired
    private CarService carService;

    /*@RequestMapping(value = "/cars",method = RequestMethod.GET)
    public ResponseEntity<?> getCars(){
        List<Car> cars = carService.list();
        if(cars.isEmpty()){
            return new ResponseEntity<>(new CustomType(400,"查无此结果"),HttpStatus.OK);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }*/

    // 根据ID查询
    @RequestMapping(value = "/cars/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getCar(@PathVariable("id") Integer id){
        Car car = carService.find(id);
        if(car == null){
            return new ResponseEntity<>(new CustomType(400,"没有匹配的结果"),HttpStatus.OK);
        }
        return new ResponseEntity<>(car,HttpStatus.OK);
    }

    // 模糊查询
    /*@RequestMapping(value = "/cars",method = RequestMethod.GET)
    public ResponseEntity<?> findCar(
            @RequestParam(value = "name",required = false) String name,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @RequestParam(value = "beginTime",required = false) Date beginTime,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @RequestParam(value = "endTime",required = false) Date endTime
    ){
        System.out.println("name = "+name+" beginTime="+beginTime+" endTime="+endTime);
        List<Car> cars = carService.find(name,beginTime,endTime);
        if(cars.isEmpty()){
            return new ResponseEntity<>(new CustomType(400,"没有匹配的结果"),HttpStatus.OK);
        }
        return new ResponseEntity<>(cars,HttpStatus.OK);
    }*/
    @RequestMapping(value = "/cars",method = RequestMethod.GET)
    public ResponseEntity<?> findCar(
            @RequestParam(value = "name",required = false) String name,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @RequestParam(value = "beginTime",required = false) Date beginTime,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @RequestParam(value = "endTime",required = false) Date endTime
            ){
        List<Car> cars = carService.find(name,beginTime,endTime);
        if(cars.isEmpty()){
            return new ResponseEntity<>(new CustomType(400,"没有匹配的结果"),HttpStatus.OK);
        }
        return new ResponseEntity<>(cars,HttpStatus.OK);
    }

    // 删除
    @RequestMapping(value = "/cars/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> removeCar(@PathVariable("id") Integer id){
        int count = carService.remove(id);
        if(count == 0){
            return new ResponseEntity<>(new CustomType(400,"删除失败"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomType(200,"删除成功"),HttpStatus.OK);
    }

    @RequestMapping(value = "/cars",method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody Car car){
        int count = carService.add(car);
        if(count == 0){
            return new ResponseEntity<>(new CustomType(400,"新增失败"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomType(200,"新增成功"),HttpStatus.OK);
    }

    @RequestMapping(value = "/cars",method = RequestMethod.PUT)
    public ResponseEntity<?> modify(@RequestBody Car car){
        int count = carService.modify(car);
        if(count == 0){
            return new ResponseEntity<>(new CustomType(400,"新增失败"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomType(200,"新增成功"),HttpStatus.OK);
    }

}
