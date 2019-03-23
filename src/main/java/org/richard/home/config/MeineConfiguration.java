package org.richard.home.config;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MeineConfiguration {
    private static final String drlFile = "meineregeln";

    @Bean
    public KieSession kieSession(){
        System.out.println( "Bootstrapping the drools engine..." );
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        return kContainer.newKieSession();
    }
}
