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

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Consume;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultConsumerTemplate;

/**
 * An example class for demonstrating some of the basics behind Camel. This
 * example sends some text messages on to a JMS Queue, consumes them and
 * persists them to disk
 */
public final class JMSServer extends RouteBuilder {

    @Consume(uri = "activemq:bean.records")
    private ConsumerTemplate consumer = null;
    
    CamelContext context = new DefaultCamelContext();
    
    //http://camel.apache.org/pojo-messaging-example.html

    public JMSServer() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://lenovo:61616");
        
        context.addComponent("jms", JmsComponent.jmsComponentClientAcknowledge(connectionFactory));
        
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
        from("activemq:personnel.records").process(new MyProcessor());
    }
    

    public static void main(String args[]) throws Exception {
        new JMSServer();
    }

}

