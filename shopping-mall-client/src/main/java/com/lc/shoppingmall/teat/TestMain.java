package com.lc.shoppingmall.teat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lc.shoppingcommon.pojo.GoodsEntity;
import com.lc.shoppingcommon.pojo.SeckillOrderEntity;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 刘晨
 * @description
 * @create 2021/6/15 0015
 * @since 1.0.0
 */
public class TestMain {
    public static void main(String[] args) {

        /**
         * 输入一个字符串，得到不连续的字符串长度
         * 例如字符串为 abcda，则输出4，因为abcd长度为4
         */
//        String s = "abcc";
//        int[] last = new int[128];
//        int n = s.length();
//
//        int res = 0;
//        int start = 0; // 窗口开始位置
//        for(int i = 0; i < n; i++) {
//            //charAt(i)返回指定索引处的字符
//            int index = s.charAt(i);
//            start = Math.max(start, last[index]);
//            res   = Math.max(res, i - start + 1);
//            last[index] = i+1;
//        }
//        System.out.println(res);

        /**
         * java8 新的时间处理类 LocalDataTime
         */
//        LocalDateTime now = LocalDateTime.now();
//        //直接创建指定时间
//        LocalDateTime startTime = LocalDateTime.of(2018, 1, 1, 12, 0);
//        LocalDateTime endTime = LocalDateTime.of(2018, 1, 1, 15, 0);
//        //Period计算两个时间相差的天数和月数，参数必须为LocalData类型
//        Period period = Period.between(LocalDate.of(2018, 1, 8), LocalDate.of(2018, 2, 5));
//        Integer days = period.getDays();
//        Integer months = period.getMonths();
//        System.out.println(days + "--" + months);   //28--0
//        //Duration可以将相差时间转为天，时分秒等
//        Duration duration = Duration.between(startTime,endTime);
//        System.out.println(duration.toDays() + "-" + duration.toHours() + "-" + duration.toMinutes());

        /**
         * fastJson转换
         */
        SeckillOrderEntity seckillOrderEntity = new SeckillOrderEntity();
        seckillOrderEntity.setId(111L);
        seckillOrderEntity.setOrderId(1L);
        seckillOrderEntity.setGoodsId(2L);
        seckillOrderEntity.setUserId(3L);
        seckillOrderEntity.setCreateBy(1L);
        seckillOrderEntity.setCreateTime(new Date());
        seckillOrderEntity.setUpdateBy(1L);
        seckillOrderEntity.setUpdateTime(new Date());

        SeckillOrderEntity seckillOrderEntity1 = new SeckillOrderEntity();
        seckillOrderEntity1.setId(222L);
        seckillOrderEntity1.setOrderId(2L);
        seckillOrderEntity1.setGoodsId(3L);
        seckillOrderEntity1.setUserId(4L);
        seckillOrderEntity1.setCreateBy(1L);
        seckillOrderEntity1.setCreateTime(new Date());
        seckillOrderEntity1.setUpdateBy(1L);
        seckillOrderEntity1.setUpdateTime(new Date());
        //Entity --> Json    JSON.toJSONString()
        String jsonString = JSON.toJSONString(seckillOrderEntity);
        //{"createBy":1,"createTime":1625807893417,"goodsId":2,"id":111,"orderId":1,"updateBy":1,"updateTime":1625807893417,"userId":3}
        System.out.println(jsonString);
        //EntityList --> Json  JSON.toJSONString()
        List<SeckillOrderEntity> seckillOrderEntityList = new ArrayList<>();
        seckillOrderEntityList.add(seckillOrderEntity1);
        seckillOrderEntityList.add(seckillOrderEntity);
//        String jsonListString = JSON.toJSONString(seckillOrderEntityList);
//        //[{"createBy":1,"createTime":1625808122741,"goodsId":3,"id":222,"orderId":2,"updateBy":1,"updateTime":1625808122741,"userId":4},{"createBy":1,"createTime":1625808122741,"goodsId":2,"id":111,"orderId":1,"updateBy":1,"updateTime":1625808122741,"userId":3}]
//        System.out.println(jsonListString);
//
//        //发现jsonString转换为对象或List<对象>时，无法转换父类的字段，这时要在子类加上注解@ToString(callSuper = true)
//        String testJSONString= "{\"createBy\":1,\"createTime\":1628737633816,\"testId\":2,\"id\":111,\"orderId\":1,\"updateBy\":1,\"updateTime\":1628737633816,\"userId\":3}";
//        SeckillOrderEntity seckillOrderEntity3 = JSON.parseObject(jsonString, SeckillOrderEntity.class);
//        System.out.println("jsonString to entity : " + seckillOrderEntity3);
//
//        List<SeckillOrderEntity> seckillOrderEntityList1 = JSON.parseArray(jsonListString, SeckillOrderEntity.class);
//        System.out.println(seckillOrderEntityList1);

        /**
         * 替换list中的某一条数据
         */
        SeckillOrderEntity seckillOrderEntity2 = new SeckillOrderEntity();
        seckillOrderEntity2.setId(333L);
        seckillOrderEntity2.setOrderId(1L);
        seckillOrderEntity2.setGoodsId(2L);
        seckillOrderEntity2.setUserId(3L);
        seckillOrderEntity2.setCreateBy(1L);
        seckillOrderEntity2.setCreateTime(new Date());
        seckillOrderEntity2.setUpdateBy(1L);
        seckillOrderEntity2.setUpdateTime(new Date());
        seckillOrderEntityList.add(seckillOrderEntity2);
//        System.out.println("替换前List: " + seckillOrderEntityList);
//        for (int i=0; i<seckillOrderEntityList.size(); i++) {
//            if (seckillOrderEntityList.get(i).getGoodsId().equals(seckillOrderEntity2.getGoodsId())) {
//                seckillOrderEntityList.set(i, seckillOrderEntity2);


//            }
//        }
//        System.out.println("替换后List: " + seckillOrderEntityList);


        /**
         * list进行stream后，会按照原先的顺序进行排序
         */
        List<Long> idList = seckillOrderEntityList.stream().map(r -> r.getId()).collect(Collectors.toList());
        System.out.println(idList);
        /**
         * list进行stream后，是否会去重.转成list不会去重。需要用.distinct()
         */
        List<Long> orderIdList = seckillOrderEntityList.stream().map(r -> r.getOrderId()).distinct().collect(Collectors.toList());
        System.out.println("去重" + orderIdList);
        //根据某一字段去重去重
        //seckillOrderEntityList为元数据list，根据OrderId字段进行去重，testList是去重后的list
        List<SeckillOrderEntity> testList = seckillOrderEntityList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(r -> r.getOrderId()))), ArrayList::new));

        System.out.println("根据某一字段去重" + testList);

        /**
         * 判断aa是哪一个实验的哪一步
         * examQuestionIdList 为 试题Id的lsit
         * map 为 Map<试题ID，实体实验步数>
         */
//        int aa =5;
//        int examNum = 0;
//        int stepNum = 0;
        List<Integer> examQuestionIdList = Arrays.asList(13,23,32);
//        Map<Integer, Integer> map = new HashMap<>();
//        map.put(13,3);
//        map.put(23,2);
//        map.put(32,4);
//        for (int i=0; i<examQuestionIdList.size(); i++) {
//            int j = i - 1;
//            if (j>=0 && map.get(examQuestionIdList.get(j)) != null) {
//                map.put(examQuestionIdList.get(i), map.get(examQuestionIdList.get(j)) + map.get(examQuestionIdList.get(i)));
//                if (aa > map.get(examQuestionIdList.get(j)) && aa<=map.get(examQuestionIdList.get(i))){
//                    examNum = examQuestionIdList.get(i);
//                    stepNum = aa - map.get(examQuestionIdList.get(j));
//                }
//            } else {
//                if (aa <= examQuestionIdList.get(i)){
//                    examNum = examQuestionIdList.get(i);
//                    stepNum = aa;
//                }
//
//            }
//
//        }
//        System.out.println(map);
//        System.out.println(examNum + ":" + stepNum);
//
        /**
         * stream的filter是过滤出r==13的数据，不是过滤掉
         */
        System.out.println(examQuestionIdList.stream().filter(r -> r==13).collect(Collectors.toList()));

        /**
         * addAll不会顶替掉原来的数据，也不会自动去重，需要手动去重
         * stream属于中间操作
         * 比如 integerList.stream().distinct() 就属于中间操作。如果操作后不把赋值给list，就不起作用
         */
        List<Integer> integerList = new ArrayList<>();
        List<Integer> integerList1 = new ArrayList<>(Arrays.asList(1,2,3));
        Map<Integer, String> integerMap1 = new HashMap<>();
        integerMap1.put(1, "1");
        List<Integer> integerList2 = new ArrayList<>(Arrays.asList(1,2,6));
        integerList.addAll(integerList1);
        integerList.addAll(integerList2);
        System.out.println("addAll : " + integerList);
        integerList = integerList.stream().distinct().collect(Collectors.toList());
        System.out.println("stream distinct : " + integerList);




        /**
         * 尽量使用CollectionUtils.isEmpty()，因为如果list为null的话，List.isEmpty()会报错
         */
        List<Long> longList = null;
        System.out.println("List.isEmpty()"  + "; CollectionUtils.isEmpty()" + CollectionUtils.isEmpty(longList));

        //String jsonString1 = "[{\"id\":123,\"map\":{\"665672\":[665692,1405754]},\"testTwoEntities\":[{\"id\":111,\"name\":\"小白\"}]},{\"id\":456,\"map\":{\"665671\":[665692,1405754]},\"testTwoEntities\":[{\"id\":111,\"name\":\"小红\"}]}]\n";

        String jsonString1 = "[{\"id\":123,\"map\":{\"665672\":[665692,1405754]},\"testTwoEntities\":[{\"id\":111,\"name\":\"小白\"}]}]";
        List<TestEntity> testEntityList = JSON.parseArray(jsonString1,TestEntity.class);

        testEntityList = testEntityList.stream().filter(r -> r.getMap().containsKey(665672L)).collect(Collectors.toList());
        System.out.println(testEntityList);

        /**
         * 两数相除，需要将除数与被除数转成float类型。小数位使用BigDecimal
         */
//        Double a = 1-Double.valueOf((float)3 / (float)7);
//        BigDecimal b = new BigDecimal(a);
//        a = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//        System.out.println("Double is : " + a);

        /**
         * get得到map中不存在的key并转类型不会报错
         * 但要使用强转，不能toString。因为null.toString()就会报错
         */
        Map<String, Object> testMap = new HashMap<>();
        testMap.put("a", "a");
        String b = (String)testMap.get("b");
        System.out.println("map:" + b);

        Long aa = 123L;
        System.out.println(String.format(", '%s'", aa));

        List<Double> doubles = new ArrayList<>();
                doubles.add(17.0);
        String  asdasa =String.valueOf(doubles.stream().mapToDouble(Double::doubleValue).average().getAsDouble());
        System.out.println(asdasa);
    }
}
