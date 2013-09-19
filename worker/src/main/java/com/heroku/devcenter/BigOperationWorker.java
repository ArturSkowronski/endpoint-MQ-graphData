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
import java.util.Set;

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


                Jedis jedis = finalPool.getResource();
                try {
                    jedis.set("foo", "bar");
                    String foobar = jedis.get("foo");
                    jedis.zadd("sose", 0, "car"); jedis.zadd("sose", 0, "bike");
                    Set<String> sose = jedis.zrange("sose", 0, -1);
                    System.out.println("Received from RabbitMQ : " + bigOp);
                    URI redisURI = new URI(System.getenv("REDISTOGO_URL"));
                    System.out.print(redisURI);
                } catch (URISyntaxException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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
