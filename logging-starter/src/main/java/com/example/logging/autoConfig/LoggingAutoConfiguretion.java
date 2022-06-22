package com.example.logging.autoConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@EnableConfigurationProperties(LoggingProperties.class)
@ConditionalOnProperty(value = "example.logging.enabled", havingValue = "true", matchIfMissing = true)
public class LoggingAutoConfiguretion {

    private static final Logger LOG = (Logger) LoggerFactory.getLogger(LoggingAutoConfiguretion.class);


    @Bean
    @ConditionalOnMissingBean
    public CommonsRequestLoggingFilter requestLoggingFilter(LoggingProperties properties) {
        CommonsRequestLoggingFilter commonsRequestLoggingFilter = new CommonsRequestLoggingFilter();
        commonsRequestLoggingFilter.setBeforeMessagePrefix(properties.getMessagePrefix());
        commonsRequestLoggingFilter.setAfterMessagePrefix(properties.getMessagePrefix());
        commonsRequestLoggingFilter.setBeforeMessageSuffix("");
        commonsRequestLoggingFilter.setAfterMessageSuffix("");
        commonsRequestLoggingFilter.setIncludePayload(true);
        commonsRequestLoggingFilter.setMaxPayloadLength(properties.getPayload().getMaxLength());
        LOG.info("Using following configuration:'message prefix' - {},  'max payload length' - {}", properties.getMessagePrefix(), properties.getPayload().getMaxLength());
        System.out.println("я тут");
        return commonsRequestLoggingFilter;
    }
}
