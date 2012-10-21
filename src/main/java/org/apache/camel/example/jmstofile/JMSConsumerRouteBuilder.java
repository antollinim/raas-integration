package org.apache.camel.example.jmstofile;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import com.plugtree.integration.model.Person;

public class JMSConsumerRouteBuilder extends RouteBuilder {

    CamelContext context = new DefaultCamelContext();
    
    //http://camel.apache.org/pojo-messaging-example.html

    public JMSConsumerRouteBuilder() {
        
//        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://lenovo:61616");
//        
//        context.addComponent("jms", JmsComponent.jmsComponentClientAcknowledge(connectionFactory));
        
        //consumer = new DefaultConsumerTemplate(context);
        try {
            context.start();
            context.addRoutes(this);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    
    @Override
    public void configure() throws Exception {
        //JsonDataFormat jsonFormat = new JsonDataFormat(JsonLibrary.XStream);
        
        from("activemq:events.json").process( new Processor() {
            
            public void process(Exchange exchange) throws Exception {
//                
                //String msg = exchange.getIn().getBody(String.class);
                String msg = exchange.getIn().getBody(String.class);
                //Person msg = exchange.getIn().getBody(Person.class);
                  System.out.println(msg);
//                for (String key : map.keySet()) {
//                    System.out.println("key: " + key + ", value: " + map.get(key));
//                }
//                System.out.println("Message Received: " + msg );
                //System.out.println("Message Receive: " + exchange.getProperties());
                
            }
        });
                    
        
        from("activemq:events.pojo").process( new Processor() {
            
            public void process(Exchange exchange) throws Exception {
//                
                //String msg = exchange.getIn().getBody(String.class);
                Person person = exchange.getIn().getBody(Person.class);
                //Person msg = exchange.getIn().getBody(Person.class);
                System.out.println(person);
//                for (String key : map.keySet()) {
//                    System.out.println("key: " + key + ", value: " + map.get(key));
//                }
//                System.out.println("Message Received: " + msg );
                //System.out.println("Message Receive: " + exchange.getProperties());
                
            }
        });
                    
    }
    
}
