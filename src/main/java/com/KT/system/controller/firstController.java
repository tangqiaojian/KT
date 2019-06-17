package com.KT.system.controller;

import com.KT.system.rabbitMQ.queuProduct.Product;
import com.KT.system.servces.Impl.SystemServcesImpl;
import com.KT.system.servces.SystemServces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tqj
 * @Description
 * @create 2019/6/12 11:57
 **/
@Controller
@RequestMapping("/firstController")
public class firstController {
    @Autowired
    SystemServces systemServces;
 /*   @Autowired
    Product product;*/

    @RequestMapping(value = "first", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String first(HttpServletRequest request) throws Exception {
        Date star_date = new Date();
        List<Map<String, Object>> result = systemServces.selectMeu();
        String s = request.getParameter("a");
        System.out.println(s);
        Date end_date = new Date();
        return "成功" + star_date + "结束" + end_date;
    }

}
