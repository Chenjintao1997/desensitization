package cjt.core.method;

import cjt.annotations.InfoMask;
import cjt.annotations.InfoMasks;
import cjt.beans.IdCardInfoOperator;
import cjt.beans.PhoneInfoOperator;
import cjt.beans.RealNameInfoOperator;
import cjt.core.bo.InfoMaskPojoTest;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InfoMasksCore {
    @InfoMasks
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
    @InfoMasks
    public Object test3(){
        return new Date();
    }
}
