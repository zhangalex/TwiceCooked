package com.yumong.live.discovery;

import com.netflix.appinfo.InstanceInfo;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaServerStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EurekaListener {

    @EventListener //服务下线事件
    public void listen(EurekaInstanceCanceledEvent eurekaInstanceCanceledEvent) {
        //服务断线事件
        String appName = eurekaInstanceCanceledEvent.getAppName();
        String serverId = eurekaInstanceCanceledEvent.getServerId();
        log.info("服务断线事件:"+appName+":"+serverId);
    }

    @EventListener //服务注册事件
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        log.info("服务注册事件:"+instanceInfo.getAppName()+":"+instanceInfo.getIPAddr()+":"+instanceInfo.getPort());
    }

	@EventListener // 服务续约事件
	public void listen(EurekaInstanceRenewedEvent event) {
		String appName = event.getAppName();
		String serverId = event.getServerId();
        log.info("服务续约事件:" + appName + ":" + serverId);
	}

    @EventListener //注册中心启动事件
    public void listen(EurekaRegistryAvailableEvent event) {
        log.info("注册中心启动事件:"+event);
    }

    @EventListener //Server启动事件
    public void listen(EurekaServerStartedEvent event) {
        log.info("Server启动事件:"+event);
    }
}
