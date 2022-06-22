package com.example.logging.autoConfig;


import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.context.properties.bind.validation.BindValidationException;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;




public class LoggingAutoConfigurationIT {

    @Test
    void shouldAutoConfigurationBeAppliedToWebApplication(){
        new WebApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(LoggingAutoConfiguretion.class))
                .run(context -> {
                    assertThat(context).hasNotFailed()
                            .hasSingleBean(CommonsRequestLoggingFilter.class)
                            .hasSingleBean(LoggingProperties.class)
                            .getBean(LoggingProperties.class).hasNoNullFieldsOrProperties();

                });
    }

    @Test
    void shouldAutoConfigurationBeFailedToWebApplication(){
        new WebApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(LoggingAutoConfiguretion.class))
                .withPropertyValues("example.logging.message-prefix=w")
                .run(context -> {
                    assertThat(context).hasFailed().getFailure()
                            .hasRootCauseInstanceOf(BindValidationException.class)
                            .hasStackTraceContaining("'example.logging' on field 'messagePrefix'");

                });
    }

    @Test
    void shouldLoggingFilterBeNotAppliedToContext () {
        new WebApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(LoggingAutoConfiguretion.class))
                .withUserConfiguration(TestConfiguration.class)
                .run(context -> {
                    assertThat(context).hasNotFailed()
                            .hasSingleBean(CommonsRequestLoggingFilter.class)
                            .hasSingleBean(MyRequestLoggingFilter.class);

    });
    }
        private  static  class  TestConfiguration {

        @Bean
            public MyRequestLoggingFilter myRequestLoggingFilter(){
            return  new MyRequestLoggingFilter();
        }
        }

        private static class MyRequestLoggingFilter extends CommonsRequestLoggingFilter{
        @Override
        protected  boolean shouldLog(HttpServletRequest request){
            return true;
        }
        }
}
