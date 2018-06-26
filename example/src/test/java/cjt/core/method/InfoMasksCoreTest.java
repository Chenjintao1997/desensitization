package cjt.core.method;



import cjt.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class InfoMasksCoreTest {
    @Autowired
    InfoMasksCore core;

    @Test
    public void infoMaskerTest1(){
        System.out.println("脱敏后："+core.infoMaskerTest1());
    }
    @Test
    public void infoMaskerTest2(){
        System.out.println("脱敏后："+core.infoMaskerTest2());
    }
    @Test
    public void Test3(){
        //System.out.println("111"+new Date());
        System.out.println(core.test3());
    }
}
