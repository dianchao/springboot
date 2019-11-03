package com.dianchao.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyAppHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        //代表健康
        //return Health.up().build();

        return Health.down().withDetail("msg", "服务异常!").build();
    }
}
