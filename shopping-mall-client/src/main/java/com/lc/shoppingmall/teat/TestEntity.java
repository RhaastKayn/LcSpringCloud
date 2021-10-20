package com.lc.shoppingmall.teat;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author 刘晨
 * @description
 * @create 2021/7/14 0014
 * @since 1.0.0
 */
@Data
public class TestEntity {
    private Long id;
    private Map<Long, List<Long>> map;
    private List<TestTwoEntity> testTwoEntities;
}
