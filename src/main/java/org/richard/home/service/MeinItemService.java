package org.richard.home.service;

import org.kie.api.runtime.KieSession;
import org.richard.buero.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeinItemService {

    @Autowired
    private KieSession kSession;

    public int fireAllItemRules(){

        Item item = new Item((long)1,"test", 23.99, 19.0);
        System.out.println("Item Category: " + item.getCategory());
        kSession.insert(item);
        int fired = kSession.fireAllRules();
        System.out.println("Item Category: " + item.getCategory());
        return fired;
    }
}
