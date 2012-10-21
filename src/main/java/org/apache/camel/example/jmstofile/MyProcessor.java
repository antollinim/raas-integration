package org.apache.camel.example.jmstofile;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.apache.camel.processor.UnmarshalProcessor;
import org.apache.cxf.message.MessageContentsList;

import com.plugtree.integration.model.Person;

class MyProcessor implements Processor {

    public void process(Exchange exchange) throws Exception {

//        XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();
//        JaxbDataFormat jaxbDataFormat;
//        xmlJsonFormat.getDataFormat().marshal(exchange, graph, stream)
        Person person = exchange.getIn().getBody(Person.class);
//        System.out.println("Received message - Person: " + person);
//        System.out.println("Received message - FirstName: " + person.getFirstName());
//        System.out.println("Received message - Name: " + person.getName());
        //AsyncProcessorConverterHelper.
        
        
        MessageContentsList msgList = (MessageContentsList)exchange.getIn().getBody();
        Object a = msgList.get(0);
        System.out.println(a.toString());
//        for (String key : person.keySet()) {
//            System.out.println("Key: " + key);
//        }
        
    }

}
