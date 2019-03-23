package org.richard.home;

import org.richard.home.service.MeinItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Start {

    @Autowired
    public MeinItemService meinItemService;


    @Autowired
    private AnnotationConfigApplicationContext applicationContext;

    @PostConstruct
    public void startApp(){
        for (String elem : applicationContext.getBeanDefinitionNames()){
            System.out.println("beanc: " + elem);

        }
        int fired = meinItemService.fireAllItemRules();
        System.out.println("Number of Rules executed = " + fired);
    }
}
