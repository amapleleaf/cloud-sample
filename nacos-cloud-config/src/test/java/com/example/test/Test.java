package com.example.test;

import org.springframework.core.OrderComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random random =   new Random();
        List<OrderBean> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(new OrderBean(random.nextInt(50),i+""));
        }
        Collections.sort(list, OrderComparator.INSTANCE);
        list.forEach(System.out::println);
    }
}
