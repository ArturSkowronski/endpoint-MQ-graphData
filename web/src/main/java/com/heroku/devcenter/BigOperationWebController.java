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
    String bigOp="ss";
    @RequestMapping()
    public String display() {
        Thread t = new Thread() {
            public void run() {
                boolean a=true;
                while(a){
                    amqpTemplate.convertAndSend(rabbitQueue.getName(), new DataSimulation());
                    System.out.println("Dane wyslane do RabbitMQ z systemu : " + new DataSimulation());
                    try {
                        Thread.sleep(3500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                };
            }
        };
        t.start();
        return "bigOpSubmissionForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String process(@ModelAttribute("symuluj") DataSimulation bigOp, Map<String,Object> map) {

        return "";
    }
}
