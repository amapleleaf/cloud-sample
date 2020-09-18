package com.example;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class nacosgateway {
    public static void main(String[] args) {
       //simpleCreate();
        //createFlux();
       // createMono();
        //createFlux();
        subscribe();
    }

    public  static  void simpleCreate(){
        Mono.just("foo").subscribe(System.out::println);
        System.out.println("----------------------");
        Flux.just("Hello", "World").subscribe(System.out::println);
    }

    public static void createFlux() {
        //整型
        Flux<Integer> integerFlux = Flux.just(1, 2, 3, 4, 5);
        //字符串
        Flux<String> stringFlux = Flux.just("hello", "world");
        List<String> list = Arrays.asList("hello", "world");
        //列表
        Flux<String> stringFlux1 = Flux.fromIterable(list);
        //范围
        Flux<Integer> integerFlux1 = Flux.range(1, 5);
        //时间间隔
        Flux<Long> longFlux = Flux.interval(Duration.ofMillis(1000));
        longFlux.subscribe(System.out::println);

        //Flux 创建
        Flux<String> stringFlux2 = Flux.from(stringFlux1);
        stringFlux2.subscribe(System.out::println);
        System.out.println("------------1-------");
        stringFlux2.subscribe(System.out::println);
        System.out.println("----------2---------");
        Flux.range(1, 10).filter(i -> i % 2 == 0).subscribe(System.out::println);
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void createMono(){
        //字符串
        Mono<String> stringMono = Mono.just("Hello World");
        //Callable创建
        Mono<String> stringMono1 = Mono.fromCallable(()->
        {
            return "Hello World";
        });
        //Future创建
        Mono<String> stringMono2 = Mono.fromFuture(CompletableFuture.completedFuture("Hello World"));
        //Suppier创建
        Mono<Double> doubleMono = Mono.fromSupplier(new Random()::nextDouble);
        //Mono创建
        Mono<Double> doubleMono1 = Mono.from(doubleMono);
        doubleMono1.subscribe(System.out::print);
        //Flux创建
        Mono<Integer> integerMono = Mono.from(Flux.range(1,5));
        integerMono.subscribe(System.out::println);
        stringMono2.subscribe(System.out::println);
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static  Subscription mySubscription;
    public static void subscribe(){
        Flux<String> stringFlux = Flux.just("Hello","World");
        //stringFlux.subscribe(System.out::println);
       /* //订阅方式一
        stringFlux.subscribe(val ->{
            System.out.print("val:"+val);
        },error ->{
            System.out.print("error:"+error);
        },() ->{
            System.out.print("Finished");
        },subscription -> {
            subscription.request(1);
        });*/
        //订阅方式二
        stringFlux.subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("onSubscribe");
                mySubscription = subscription;
                subscription.request(1);
            }
            @Override
            public void onNext(String s) {
                System.out.println("onNext:"+s);
                mySubscription.request(1);
            }
            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
                mySubscription.cancel();
            }
            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });

    }
}
