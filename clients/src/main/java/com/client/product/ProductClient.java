package com.client.product;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient("product-sevice")
public interface ProductClient {
    @GetMapping("/product/allProduct/")
     List<Product> getAllProduct();
    @GetMapping(path = "/product/oneProduct/{productId}")
    Product getProduct(@PathVariable("productId") String productId) ;


    @Configuration
    public class LoadBalancerConfiguration {

        @Bean
        public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(ConfigurableApplicationContext context) {
            return ServiceInstanceListSupplier.builder()
                    .withBlockingDiscoveryClient()
                    .withSameInstancePreference()
                    .withHealthChecks()
                    .build(context);
        }
    }
}
