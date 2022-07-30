package com.weaver.esxpack.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "elasticsearch")
public class EsConfig {

    private String username;

    private String password;

    private String host;

    private int port;

    private int connTimeout;

    private int socketTimeout;

    private int connectionRequestTimeout;
}