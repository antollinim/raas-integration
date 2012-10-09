package com.plugtree.integration.external.api;

import org.apache.camel.InOnly;

@InOnly   
public interface HelloService {  

    void sayHello(Person person);

}  