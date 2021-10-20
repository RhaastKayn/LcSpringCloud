package com.lc.shoppingcommon.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 刘晨
 * @description
 * @create 2021/5/17 0017
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Table(name = "seckill_order")
public class SeckillOrderEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -1945215391602395942L;

    private Long userId;
    private Long orderId;
    @JsonProperty("testId")
    private Long goodsId;
}
