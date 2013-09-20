package com.heroku.devcenter;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ErrorHandler;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

/**
 * Worker for receiving and processing BigOperations asynchronously.
 */
public class BigOperationWorker {
    static JedisPool pool;

    public static void main(String[] args) {

        final ApplicationContext rabbitConfig = new AnnotationConfigApplicationContext(RabbitConfiguration.class);
        final ConnectionFactory rabbitConnectionFactory = rabbitConfig.getBean(ConnectionFactory.class);
        final Queue rabbitQueue = rabbitConfig.getBean(Queue.class);
        final MessageConverter messageConverter = new SimpleMessageConverter();
        // create a listener container, which is required for asynchronous message consumption.
        // AmqpTemplate cannot be used in this case
        final SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
        listenerContainer.setConnectionFactory(rabbitConnectionFactory);
        listenerContainer.setQueueNames(rabbitQueue.getName());
        try {
            URI redisURI = new URI(System.getenv("REDISTOGO_URL"));
                               System.out.print(redisURI);
            pool = new JedisPool(new JedisPoolConfig(),
                    redisURI.getHost(),
                    redisURI.getPort(),
                    Protocol.DEFAULT_TIMEOUT,
                    redisURI.getUserInfo().split(":",2)[1]);
        } catch (URISyntaxException e) {
            // URI couldn't be parsed. Handle exception
        }
        // set the callback for message handling
        final JedisPool finalPool = pool;
        listenerContainer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                final DataSimulation bigOp = (DataSimulation) messageConverter.fromMessage(message);
                HashMap<String, String> map=new HashMap<String, String>();
                map.put("device",""+bigOp.getDeviceEnum());
                map.put("weather",""+bigOp.getWeatherEnum());
                map.put("tariff",""+bigOp.getTariff());
                map.put("user",""+bigOp.getUser().getId());
                map.put("time",""+bigOp.getTime());
                Jedis jedis = finalPool.getResource();
                try {
                    HashMap<String, String> deviceMap= (HashMap<String, String>) map.clone();
                    deviceMap.remove("device");
                    jedis.hmset("device", deviceMap);
                    HashMap<String, String> weatherMap= (HashMap<String, String>) map.clone();
                    deviceMap.remove("weather");
                    jedis.hmset("weather", weatherMap);
                    HashMap<String, String> tariffMap= (HashMap<String, String>) map.clone();
                    deviceMap.remove("tariff");
                    jedis.hmset("tariff", tariffMap);
                    HashMap<String, String> userMap= (HashMap<String, String>) map.clone();
                    deviceMap.remove("user");
                    jedis.hmset("user", userMap);
                    HashMap<String, String> timeMap= (HashMap<String, String>) map.clone();
                    deviceMap.remove("time");
                    jedis.hmset("time", timeMap);
                    System.out.println("Worker otrzymal dane z RabbitMQ i przekazal do Redisa: " + bigOp);
                } finally {
                    finalPool.returnResource(jedis);
                }
            }
        });

        // set a simple error handler
        listenerContainer.setErrorHandler(new ErrorHandler() {
            public void handleError(Throwable t) {
                t.printStackTrace();
            }
        });

        // register a shutdown hook with the JVM
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("Shutting down BigOperationWorker");
                listenerContainer.shutdown();
            }
        });

        // start up the listener. this will block until JVM is killed.
        listenerContainer.start();
        System.out.println("BigOperationWorker started");
    }
}
