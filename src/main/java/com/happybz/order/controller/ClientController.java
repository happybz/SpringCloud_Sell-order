package com.happybz.order.controller;

import com.happybz.order.client.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class ClientController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private ProductClient productClient;

//    @Autowired
//    private RestTemplate restTemplate;

    @GetMapping("/getProductMsg")
    public String getProductMsg(){
        //1.直接使用restTemplate
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://localhost:8080/msg", String.class);

        //2.利用loadBalancerClient
//        RestTemplate restTemplate = new RestTemplate();
//        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//        String url = String.format("http://%s:%s/msg", serviceInstance.getHost(), serviceInstance.getPort());
//        String response = restTemplate.getForObject(url, String.class);

        //3.利用@LoadBalanced
//        String response = restTemplate.getForObject("http://PRODUCT/msg",String.class);

        //4.Feign
        String response = productClient.productMsg();
        log.info("response={}",response);
        return response;
    }
}
