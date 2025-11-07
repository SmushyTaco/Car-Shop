package com.smushytaco.car_shop.configuration;

import io.undertow.server.DefaultByteBufferPool;
import io.undertow.websockets.jsr.WebSocketDeploymentInfo;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UndertowConfiguration {
    private static final String UNDERTOW_WEBSOCKET_DEPLOYMENT_INFO_ATTRIBUTE = "io.undertow.websockets.jsr.WebSocketDeploymentInfo";
    @Bean
    public WebServerFactoryCustomizer<UndertowServletWebServerFactory> undertowWebSocketBufferCustomizer() {
        return factory -> factory.addDeploymentInfoCustomizers(deploymentInfo -> {
            final Object attr = deploymentInfo.getServletContextAttributes().get(UNDERTOW_WEBSOCKET_DEPLOYMENT_INFO_ATTRIBUTE);
            final WebSocketDeploymentInfo webSocketDeploymentInfo;
            if (attr instanceof WebSocketDeploymentInfo info) {
                webSocketDeploymentInfo = info;
            } else {
                webSocketDeploymentInfo = new WebSocketDeploymentInfo();
                deploymentInfo.addServletContextAttribute(UNDERTOW_WEBSOCKET_DEPLOYMENT_INFO_ATTRIBUTE, webSocketDeploymentInfo);
            }
            if (webSocketDeploymentInfo.getBuffers() == null)
                webSocketDeploymentInfo.setBuffers(new DefaultByteBufferPool(true, 16384));
        });
    }
}