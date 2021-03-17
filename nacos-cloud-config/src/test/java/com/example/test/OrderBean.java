package com.example.test;

import org.springframework.core.Ordered;

public class OrderBean implements Ordered {
    private int order;
    private String name;
    public  OrderBean(int order,String name){
        this.order = order;
        this.name = name;
    }
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "order=" + order +
                ", name='" + name + '\'' +
                '}';
    }
}
