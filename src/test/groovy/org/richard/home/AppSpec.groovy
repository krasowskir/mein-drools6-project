package org.richard.home

import org.kie.api.runtime.KieSession
import org.richard.buero.Item
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
}


