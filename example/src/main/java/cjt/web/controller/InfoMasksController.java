package cjt.web.controller;


import cjt.annotations.InfoMask;
import cjt.annotations.InfoMasks;
import cjt.beans.IdCardInfoOperator;
import cjt.beans.PhoneInfoOperator;
import cjt.beans.RealNameInfoOperator;
import cjt.web.vo.InfoMaskPojoTest;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class InfoMasksController {


    @InfoMasks
    @GetMapping("test/infoMasker")
    public  Object infoMaskerTest1(){
        InfoMaskPojoTest infoMaskPojoTest = new InfoMaskPojoTest();
        infoMaskPojoTest.setUserName("张三");
        infoMaskPojoTest.setAge(20);
        infoMaskPojoTest.setIdCard("410521194501010001");
        infoMaskPojoTest.setPhone("15515551555");
        infoMaskPojoTest.setSex("男");

        InfoMaskPojoTest infoMaskPojoTest1 = new InfoMaskPojoTest();
        infoMaskPojoTest1.setUserName("马可·波罗");
        infoMaskPojoTest1.setAge(40);
        infoMaskPojoTest1.setIdCard("410521190101001");
        infoMaskPojoTest1.setPhone("18818881888");
        infoMaskPojoTest1.setSex("男");

        List<InfoMaskPojoTest> list = new ArrayList<>();
        list.add(infoMaskPojoTest);
        list.add(infoMaskPojoTest1);

        Map<String,Object> map = new HashMap<>();
        map.put("userName","李四");
        map.put("age",30);
        map.put("idCard","410522194901010002");
        map.put("phone","13313331333");
        map.put("sex","女");

        Map<String,Object> map2 = new HashMap<>();
        map2.put("testPojo",infoMaskPojoTest);
        map2.put("list",list);

        map.put("map",map2);
        System.out.println("脱敏前："+map.toString());
        return map;
    }

    @InfoMasks({
            @InfoMask(value = PhoneInfoOperator.class,whiteListKey = {"phone"}),
            @InfoMask(value = IdCardInfoOperator.class,blackListKey = {"idCard"}),
            @InfoMask(RealNameInfoOperator.class)
    })
    @GetMapping("test/infoMasker1")
    public  Object infoMaskerTest2(){
        InfoMaskPojoTest infoMaskPojoTest = new InfoMaskPojoTest();
        infoMaskPojoTest.setUserName("张三");
        infoMaskPojoTest.setAge(20);
        infoMaskPojoTest.setIdCard("410521194501010001");
        infoMaskPojoTest.setPhone("15515551555");
        infoMaskPojoTest.setSex("男");

        InfoMaskPojoTest infoMaskPojoTest1 = new InfoMaskPojoTest();
        infoMaskPojoTest1.setUserName("马可·波罗");
        infoMaskPojoTest1.setAge(40);
        infoMaskPojoTest1.setIdCard("410521190101001");
        infoMaskPojoTest1.setPhone("18818881888");
        infoMaskPojoTest1.setSex("男");

        List<InfoMaskPojoTest> list = new ArrayList<>();
        list.add(infoMaskPojoTest);
        list.add(infoMaskPojoTest1);

        Map<String,Object> map = new HashMap<>();
        map.put("userName","李四");
        map.put("age",30);
        map.put("idCard","410522194901010002");
        map.put("phone","13313331333");
        map.put("sex","女");

        Map<String,Object> map2 = new HashMap<>();
        map2.put("testPojo",infoMaskPojoTest);
        map2.put("list",list);

        map.put("map",map2);
        System.out.println("脱敏前："+map.toString());
        return map;
    }
//    @PostMapping("/test/login")
//    public Object testLogin(HttpServletRequest request, HttpServletResponse response){
//
//        System.out.println(jedisConnectionFactory.getHostName());
//        InfoMaskPojoTest infoMaskPojoTest = new InfoMaskPojoTest();
//        infoMaskPojoTest.setAge(10);
//        infoMaskPojoTest.setUserName("zs");
//        infoMaskPojoTest.setIdCard("789789789");
//        infoMaskPojoTest.setPhone("15515091290");
//        infoMaskPojoTest.setIdCard("410521199706047019");
//        infoMaskPojoTest.setSex("男");
//        String infoMaskPojoTestJson = JSON.toJSONString(infoMaskPojoTest);
//        request.getSession().setAttribute("tenantId",infoMaskPojoTestJson);
//        return request.getSession().getAttribute("tenantId").toString();
//    }
//    @PostMapping("/test/login1")
//    public Object testLogin1(HttpServletRequest request, HttpServletResponse response){
//
//        System.out.println(jedisConnectionFactory.getHostName());
//        request.getSession().setAttribute("tenantId","789789");
//        return null;
//    }


    @PostMapping("/add/session")
    public Object addSession(HttpServletRequest request,@RequestParam("tenantId")String tenantId){
        HttpSession session = request.getSession();
        session.setAttribute("tenantId",tenantId);
        return true;
    }

    @PostMapping("/invalidate/session")
    public Object invalidateSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return true;
    }
    @PostMapping("/get/session")
    public Object getSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("tenantId"));
        return session.getAttribute("tenantId");
    }
}
