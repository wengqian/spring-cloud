package com.wq.microservicenettywebsocket.config;import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;import org.springframework.context.annotation.Bean;import org.springframework.context.annotation.Configuration;//import org.springframework.web.socket.server.standard.ServerEndpointExporter;//nettyimport org.yeauty.standard.ServerEndpointExporter;/** * 开启WebSocket支持 * @author wengqian */@Configuration@ConditionalOnWebApplicationpublic class WebSocketConfig {    @Bean    public ServerEndpointExporter serverEndpointExporter() {        return new ServerEndpointExporter();    }    @Bean    public MySpringConfigurator mySpringConfigurator() {        return new MySpringConfigurator();    }}