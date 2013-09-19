package com.heroku.devcenter;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping("/symuluj")
public class BigOperationWebController {

    @Autowired private AmqpTemplate amqpTemplate;
    @Autowired private Queue rabbitQueue;

    @ModelAttribute("symuluj")
    public DataSimulation newBigOp() {
        return new DataSimulation();
    }

    @RequestMapping()
    public String display() {
        return "bigOpSubmissionForm";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String process(@ModelAttribute("symuluj") DataSimulation bigOp, Map<String,Object> map) {
        amqpTemplate.convertAndSend(rabbitQueue.getName(), bigOp);
        System.out.println("Sent to RabbitMQ from app: " + bigOp);
        return "''";
    }
}
