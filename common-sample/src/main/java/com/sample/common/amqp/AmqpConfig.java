package com.sample.common.amqp;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {
    @Autowired
   private  ConnectionFactory connectionFactory;

   /* @Bean
    public ConnectionFactory connectionFactory() {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHosthost);
        return connectionFactory;
    }
*/
   /* @Bean(name="rabbitListenerContainerFactory")//和kafka的配置仓库类一莫一样很牛逼的
    public RabbitListenerContainerFactory<SimpleMessageListenerContainer> rabbitListenerContainerFactory(){
       /* //ConcurrentKafkaListenerContainerFactory
        //消息的统一过滤器
        //MessageConverter messageConverter = new ObjConsert();
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConcurrentConsumers(5);//允许同时消费数量为5
        factory.setMaxConcurrentConsumers(10);//允许同时最大消费数量为10
        factory.setReceiveTimeout(10000L);//10秒
        //factory.setMessageConverter(messageConverter);//具体的逻辑要自己在ObjConsert里面写
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);//设置手动提交
        factory.setConnectionFactory(connectionFactory);
        factory.setBatchListener(true);
        factory.setDeBatchingEnabled(true);
        factory.setBatchSize(10);
        return  factory;
    }*/
   /* @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        //template.setDefaultReceiveQueue(queue);//设置默认接收队列
        return template;
    }*/


    @Bean
    public Queue logDirectQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //   return new Queue("TestDirectQueue",true,true,false);

        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue("LogDirectQueue", true);
    }

    @Bean
    public DirectExchange logtDirectExchange() {
        //  return new DirectExchange("TestDirectExchange",true,true);
        return new DirectExchange("LogDirectExchange", true, false);
    }

    //绑定  将队列和交换机绑定, 并设置用于匹配键：LogDirectRouting
    @Bean
    public Binding bindingDirect() {
        return BindingBuilder.bind(logDirectQueue()).to(logtDirectExchange()).with("LogDirectRouting");
    }
}
