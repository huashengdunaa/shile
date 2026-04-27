package com.hmall.cart.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Sl
 * @Description: TODO
 * @DateTime: 2026/1/18
 * @Version 1.0
 **/
@Component
@Data
@ConfigurationProperties("hm.cart")
public class CartProperties {
    private Integer maxItems;
}
