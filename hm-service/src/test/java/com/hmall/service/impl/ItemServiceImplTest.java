package com.hmall.service.impl;

import com.hmall.domain.dto.OrderDetailDTO;
import com.hmall.service.IItemService;
import com.hmall.utils.JwtTool;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class ItemServiceImplTest {

    @Autowired
    protected IItemService itemService;

    @Autowired
    private JwtTool jwtTool;

    @Test
    void testJwt() {
        String token = jwtTool.createToken(1L, Duration.ofMinutes(30));
        System.out.println("token = " + token);
    }

    @Test
    void deductStock() {
        List<OrderDetailDTO> items = List.of(
                new OrderDetailDTO().setItemId(317578L).setNum(1),
                new OrderDetailDTO().setItemId(317580L).setNum(1)
        );
        itemService.deductStock(items);
    }
}