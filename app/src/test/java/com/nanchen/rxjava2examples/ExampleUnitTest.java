package com.nanchen.rxjava2examples;

import com.google.gson.Gson;
import com.nanchen.rxjava2examples.model.MobileAddress;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    String s = "{\"resultcode\":\"200\",\"reason\":\"Return Successd!\",\"result\":{\"province\":\"浙江\",\"city\":\"杭州\",\"areacode\":\"0571\",\"zip\":\"310000\",\"company\":\"移动\",\"card\":\"\"},\"error_code\":0}" ;
   @Test
    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);
       MobileAddress address = new Gson().fromJson(s, MobileAddress.class);
       System.out.println("address:" + address + "\n" + address.getResult().getMobilearea());
   }
}