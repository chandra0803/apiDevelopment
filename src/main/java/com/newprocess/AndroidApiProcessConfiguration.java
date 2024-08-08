/* ***************************************************************************************
 * (c) COPYRIGHT Kuecker Pulse Integration, L.P. 2023 All Rights Reserved
 * No part of this copyrighted work may be reproduced, modified, or distributed
 * in any form or by any means without the prior written permission of Kuecker Pulse
 * Integration, L.P.
 ****************************************************************************************/
package com.newprocess;

import brave.sampler.Sampler;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

/**
 * Represents a {@link AndroidApiProcessApplication} configuration.
 */

@Configuration
@EnableWebSecurity
//@EnableConfigurationProperties({ SecurityProperties.class})
//@EnableAutoConfiguration(exclude = {
//      org.springframework.boot.autoconfigure.security.SecurityDataConfiguration.class})
// org.springframework.boot.actuate.autoconfigure.ManagementSecurityAutoConfiguration.class})
public class AndroidApiProcessConfiguration {




  /*protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
            .withUser("user1").password("pass").roles("USER");
  } */


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/v1/api").permitAll()
                .antMatchers("/v1/api/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .httpBasic();

        http.headers().cacheControl();

        return http.build();
    }

    /**
     * The {@link HttpClient} to use.
     *
     * @see HttpClient
     */
    @Bean
    @ConditionalOnMissingBean
    public HttpClient httpClient() {
        return HttpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                .doOnConnected(c -> c.addHandlerFirst(new ReadTimeoutHandler(15))
                        .addHandlerFirst(new WriteTimeoutHandler(15)));
    }


    /**
     * The {@link OpenAPI} configuration.
     *
     * @see OpenAPI
     */
    @Bean
    @ConditionalOnMissingBean
    public OpenAPI openApi() {
        return new OpenAPI().info(new Info().title("Android API Process")
                .description("Android API Process")
                .contact(new Contact().name("Chandra, LLC")
                        .url("https://chandraInfo.com/"))
                .version("v1.0.0"));

    }


    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}
