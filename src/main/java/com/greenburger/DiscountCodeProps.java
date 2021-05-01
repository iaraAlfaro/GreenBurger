package com.greenburger;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix="burger.discount")
@Data
public class DiscountCodeProps {
    private Map<String, Integer> codes = new HashMap<>();
}
