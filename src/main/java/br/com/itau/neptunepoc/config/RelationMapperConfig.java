package br.com.itau.neptunepoc.config;

import br.com.itau.neptunepoc.mapper.RelationshipMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RelationMapperConfig {

    @Bean
    public RelationshipMapper relationshipMapper(){
        return new RelationshipMapper();
    }

}
