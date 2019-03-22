package rg.drools.devguide;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.richard.buero.Item;

public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Bootstrapping the drools engine..." );
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession();


        Item item = new Item((long)1,"test", 23.99, 19.0);
        System.out.println("Item Category: " + item.getCategory());
        kSession.insert(item);
        int fired = kSession.fireAllRules();
        System.out.println("Number of Rules executed = " + fired);
        System.out.println("Item Category: " + item.getCategory());
    }
}
