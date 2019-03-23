package org.richard.home.service;

import org.kie.api.runtime.KieSession;
import org.richard.buero.Customer;
import org.richard.buero.Item;
import org.richard.buero.Order;
import org.richard.buero.OrderLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MeinItemService {

    @Autowired
    private KieSession kSession;

    private List<OrderLine> getBestellungen(){
        List<OrderLine> einkaufsliste = new ArrayList<>();

        OrderLine fleisch = new OrderLine();
        fleisch.setItem(new Item("fleisch", 1.99, 2.99));
        einkaufsliste.add(fleisch);

        OrderLine getraenke = new OrderLine();
        getraenke.setItem(new Item("Getränke", 1.39, 1.49));

        OrderLine eier = new OrderLine();
        eier.setItem(new Item("eier", 1.29, 1.39));

        OrderLine brot = new OrderLine();
        brot.setItem(new Item("brot", 0.89, 0.99));
        OrderLine schokolade = new OrderLine();
        schokolade.setItem(new Item("schokolade", 1.09, 0.99));
        OrderLine bananen = new OrderLine();
        bananen.setItem(new Item("bananen", 1.39, 1.99));
        einkaufsliste.add(getraenke);
        einkaufsliste.add(eier);
        einkaufsliste.add(bananen);
        einkaufsliste.add(brot);
        einkaufsliste.add(schokolade);

        return einkaufsliste;
    }

    public int fireAllItemRules(){

        Item item = new Item((long)1,"test", 23.99, 19.0);
        Customer richard = new Customer((long)23,27,"richard","krasowski.richard@test.de",Customer.Category.NA );
        Order meineOrder = new Order();
        meineOrder.setCustomer(richard);
        meineOrder.setDate(new Date(2019, 3, 23));
        List<OrderLine> orderLines =  getBestellungen();
        meineOrder.setItems(orderLines);

        System.out.println("Item Categew OrderLine()ory: " + item.getCategory());
        kSession.insert(item);
        kSession.insert(richard);
        kSession.insert(meineOrder);
        int fired = kSession.fireAllRules();
        System.out.println("Item Category: " + item.getCategory());
        System.out.println("Customer category: " + richard.getCategory());
        return fired;
    }
}
