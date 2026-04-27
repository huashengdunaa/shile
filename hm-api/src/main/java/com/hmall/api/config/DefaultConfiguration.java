package com.hmall.api.config;

import com.hmall.api.client.fallback.ItemClientFallbackFactory;
import com.hmall.common.utils.UserContext;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;

/**
 * @Author: Sl
 * @Description: TODO
 * @DateTime: 2026/1/17
 * @Version 1.0
 **/
public class DefaultConfiguration {
    @Bean
    public Logger.Level fullFeignLoggerLevel() {
        return  Logger.Level.FULL;
    }

    /**
     * 微服务的统一用户信息设置
     * 用户信息保存在请求头中
     * @return
     */
    @Bean
    public RequestInterceptor requestInterceptor(){
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                Long user = UserContext.getUser();
                if(user != null){
                    requestTemplate.header("user-info",user.toString());
                }
            }
        };
    }

    @Bean
    public ItemClientFallbackFactory itemClientFallbackFactory(){
        return new ItemClientFallbackFactory();
    }
}
