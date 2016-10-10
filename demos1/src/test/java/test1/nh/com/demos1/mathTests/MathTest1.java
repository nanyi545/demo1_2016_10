package test1.nh.com.demos1.mathTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 15-12-30.
 */
public class MathTest1 {

    @Before
    public void before(){
    }

    @After
    public void after(){
    }


    @Test
    public void test1(){
        System.out.println(""+(5%7));
        System.out.println(""+(21%7));
        System.out.println(""+(25%7));
    }


    @Test
    public void test2(){
        int a1=-1;
        int a=Math.abs(a1);
        System.out.println(""+a1+"   "+a);
    }


    @Test
    public void test3(){
        double a=Math.sin(Math.PI*30/180);
        System.out.println("   "+a+"    "+Math.PI);
    }
    @Test
    public void test4(){
        int a=30%50;
        int b=-20%50;
        int c=-70%50;
        System.out.println("   "+a+"    "+b+"    "+c);
    }





}
