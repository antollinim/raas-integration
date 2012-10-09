package org.apache.camel.example.jmstofile;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.plugtree.integration.external.api.Person;

class MyProcessor implements Processor {

    public void process(Exchange exchange) throws Exception {

        Person person = exchange.getIn().getBody(Person.class);
        System.out.println("Received message: " + person);
    }

}
