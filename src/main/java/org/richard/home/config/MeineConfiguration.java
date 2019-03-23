package org.richard.home.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MeineConfiguration {
    private static final String drlFile = "meineregeln/rules.drl";

    @Bean
    public KieSession kieSession(){
        System.out.println( "Bootstrapping the drools engine..." );
        KieServices ks = KieServices.Factory.get();
        //KieContainer kContainer = ks.getKieClasspathContainer();
        //KieSession kSession = kContainer.newKieSession();

        KieFileSystem kieFileSystem = ks.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource(drlFile));
        KieBuilder kieBuilder = ks.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        KieModule kieModule = kieBuilder.getKieModule();

        return ks.newKieContainer(kieModule.getReleaseId()).newKieSession();
    }
}
