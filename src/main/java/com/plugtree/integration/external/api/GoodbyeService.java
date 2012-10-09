package com.plugtree.integration.external.api;

import org.apache.camel.InOnly;

@InOnly   
public interface GoodbyeService {  
    
    void sayGoodbye(Person person);  

} 