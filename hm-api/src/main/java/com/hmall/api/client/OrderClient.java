package com.hmall.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @Author: Sl
 * @Description: TODO
 * @DateTime: 2026/1/12
 * @Version 1.0
 **/
@FeignClient("order-service")
public interface OrderClient {
    @PutMapping("/orders/{orderId}")
    void markOrderPaySuccess(@PathVariable("orderId") Long orderId);
}
