package com.app.api.apigateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Set;

@Component
public class MyPreFilter implements GlobalFilter {

    private Logger LOG = LoggerFactory.getLogger(MyPreFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String requestPath = exchange.getRequest().getPath().toString();
        LOG.info("Resquest Paht " + requestPath);
        HttpHeaders headers = exchange.getRequest().getHeaders();
        Set<String> headersName = headers.keySet();
        headersName.forEach((name -> {
            String first = headers.getFirst(name);
            LOG.info(name + " " + first);
        }));


        return chain.filter(exchange);
    }
}
