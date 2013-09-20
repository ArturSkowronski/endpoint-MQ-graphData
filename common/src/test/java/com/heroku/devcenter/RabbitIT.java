package com.heroku.devcenter;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.BeforeTest;

/**
 * @author Ryan Brainard
 */
public class RabbitIT {

    private final ApplicationContext rabbitConfig = new AnnotationConfigApplicationContext(RabbitConfiguration.class);
    private final ConnectionFactory rabbitConnectionFactory = rabbitConfig.getBean(ConnectionFactory.class);
    private final AmqpTemplate amqpTemplate = rabbitConfig.getBean(AmqpTemplate.class);
    private final Queue rabbitQueue = rabbitConfig.getBean(Queue.class);

    @BeforeTest
    public void cleanTheRabbit() {
        while (amqpTemplate.receive(rabbitQueue.getName()) != null){}
    }


}
