/**
 * Licensed to the Apache Soft ware Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.example.jmstofile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Consume;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.RecipientList;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import scala.actors.threadpool.Arrays;

/**
 * An example class for demonstrating some of the basics behind Camel. This
 * example sends some text messages on to a JMS Queue, consumes them and
 * persists them to disk
 */
public final class CamelJmsToFileExample {

    //http://camel.apache.org/pojo-messaging-example.html
    
    @Consume(uri = "jms:queue:numbers", context="camel-client")
    @RecipientList
    public void route() {
        System.out.println("Message Arrived");
    }
    
    private CamelJmsToFileExample() {
        
    }
    
    public static void main(String args[]) throws Exception {
        
        
//        // START SNIPPET: e1
        CamelContext context = new DefaultCamelContext();
        
          // END SNIPPET: e1
//        // Set up the ActiveMQ JMS Components
//        // START SNIPPET: e2
        
        //ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://lenovo:61616");
        //Connection connection = connectionFactory.createConnection();
        //connection.start();
        //ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        //Connection connection = ActiveMQConnection.makeConnection("tcp://lenovo:61616");
        
        //ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_BIND_URL);
//        // Note we can explicit name the component
//        //context.addComponent("test-jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        //context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        context.addComponent("jms", JmsComponent.jmsComponentClientAcknowledge(connectionFactory));
        
        context.addRoutes(new RouteBuilder() {

            public void configure() {
                from("jms:queue:numbers").process(new Processor() {

                    public void process(Exchange e) {
                        Map<String, String> props = e.getIn().getBody(HashMap.class);
                        System.out.println(new Date() + ". Received exchange: " + Arrays.toString(props.values().toArray()));
                        e.getOut().setBody("OK");
                    }
                });
            }
        });
        
        
//        // END SNIPPET: e2
//        // Add some configuration by hand ...
//        // START SNIPPET: e3
//        context.addRoutes(new RouteBuilder() {
//            public void configure() {
//                //from("test-jms:queue:test.queue").to("file://test");
//                from("jms:queue:numbers").to("file://test");
//            }
//        });
//        // END SNIPPET: e3
//        // Camel template - a handy class for kicking off exchanges
//        // START SNIPPET: e4
//        ProducerTemplate template = context.createProducerTemplate();
        //ConsumerTemplate template = context.createConsumerTemplate();
        //template.receiveBody("jms:queue:numbers");
//        template.start();
          // END SNIPPET: e4
//        // Now everything is set up - lets start the context
        context.start();
//        // Now send some test text to a component - for this case a JMS Queue
//        // The text get converted to JMS messages - and sent to the Queue
//        // test.queue
//        // The file component is listening for messages from the Queue
//        // test.queue, consumes
//        // them and stores them to disk. The content of each file will be the
//        // test we sent here.
//        // The listener on the file component gets notified when new files are
//        // found ... that's it!
//        // START SNIPPET: e5
//        for (int i = 0; i < 100; i++) {
//            Thread.sleep(1000);
//            //template.sendBody("test-jms:queue:test.queue", "Test Message: " + i);
//            template.sendBody("jms:queue:numbers", "Test Message: " + i);
//        }
//        // END SNIPPET: e5
//        // wait a bit and then stop
//        //Thread.sleep(1000000);
//        //context.stop();
    }
}
