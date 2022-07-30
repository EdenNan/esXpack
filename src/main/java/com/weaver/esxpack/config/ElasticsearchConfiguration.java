package com.weaver.esxpack.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfiguration {

    @Autowired
    EsConfig esConfig;

    @Bean(destroyMethod = "close")
    public RestHighLevelClient client() {

        RestClientBuilder builder = RestClient.builder(new HttpHost(esConfig.getHost(), esConfig.getPort()))
                .setRequestConfigCallback((requestConfigBuilder) -> {
                    requestConfigBuilder.setConnectTimeout(esConfig.getConnTimeout());
                    requestConfigBuilder.setSocketTimeout(esConfig.getSocketTimeout());
                    return requestConfigBuilder;
                });
        if(true){
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(esConfig.getUsername(), esConfig.getPassword()));
            builder.setHttpClientConfigCallback((httpClientBuilder) -> {
                httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                return httpClientBuilder;
            });
        }
        return new RestHighLevelClient(builder);
    }
}