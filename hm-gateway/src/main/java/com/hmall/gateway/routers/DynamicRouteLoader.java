package com.hmall.gateway.routers;

import cn.hutool.json.JSONUtil;
import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

/**
 * @Author: Sl
 * @Description: TODO
 * @DateTime: 2026/1/18
 * @Version 1.0
 **/
@Slf4j
@Configuration
@Component
@RequiredArgsConstructor
public class DynamicRouteLoader {
    private final String dataId = "gateway-routes.json";

    private final String groupId = "DEFAULT_GROUP";

    private final NacosConfigManager nacosConfigManager;

    private final RouteDefinitionWriter routeDefinitionWriter;

    private final Set<String> routeSet = new HashSet<>();

    @PostConstruct
    public void initRouteConfigListener() throws NacosException {

        //项目启动先拉取一次配置，在配置监听器
        String configInfo = nacosConfigManager.getConfigService().getConfigAndSignListener(dataId, groupId, 5000, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String config) {
                //监听到配置更新，更新配置路由表
                updateConfig(config);
            }
        });
        //第一次读取到配置，也更新到路由表
        updateConfig(configInfo);
    }

    private void updateConfig(String configInfo) {
        log.debug("监听到路由配置信息{}",configInfo);
        //解析配置
        List<RouteDefinition> list = JSONUtil.toList(configInfo, RouteDefinition.class);

        //删除旧路由表
        for (String routeId : routeSet) {
            routeDefinitionWriter.delete(Mono.just(routeId));
        }

        routeSet.clear();
        for (RouteDefinition routeDefinition : list) {
            //更新路由表
            routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
            routeSet.add(routeDefinition.getId());
        }
    }

}
