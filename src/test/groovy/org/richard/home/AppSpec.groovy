package org.richard.home

import org.kie.api.runtime.KieSession
import org.richard.buero.Customer
import org.richard.buero.Item
import org.richard.buero.Order
import org.richard.buero.OrderLine
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class AppSpec extends Specification {

    @Autowired
    private KieSession kieSession

    def 'test the rules'() {

        given: 'an example item with cost lower than 200'
        int fired = 0
        Item testItem = new Item((long)12,"fleisch", 19.99,29.99)
        kieSession.insert(testItem)

        when:
        fired = kieSession.fireAllRules()

        then: 'only one rule was executed'
        fired == 1

        and: 'the category of the item was changed'
        testItem.category == Item.Category.LOW_RANGE
    }

    def 'an order with 5 items makes the customer to a silver customer'(){

        given: 'an Order which belongs to a customer who has no Category'
        Order meineOrder = new Order()
        Customer richard = new Customer((long)23,27,"richard","krasowski.richard@test.de",Customer.Category.NA )
        meineOrder.setCustomer(richard)
        meineOrder.setDate(new Date(2019, 3, 23))

        and: 'customer puts 5 items in the order'
        List<OrderLine> orderLines =  getBestellungen()
        meineOrder.setItems(orderLines)
        kieSession.insert(meineOrder)
        kieSession.insert(richard)

        when:
        int fired = kieSession.fireAllRules()

        then: 'all rules are fired'
        fired == 4

        and: 'the category of the customer is change to SILVER'
        meineOrder.customer.category == Customer.Category.SILVER

        /*and: ' he gets a discount 10%'
        meineOrder.discount != null
        meineOrder.discount.percentage == 10*/
    }

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
}


