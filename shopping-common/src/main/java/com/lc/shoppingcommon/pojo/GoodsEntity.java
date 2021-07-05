package com.lc.shoppingcommon.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 刘晨
 * @description 商品表
 * @create 2021/6/8 0008
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Table(name = "goods")
public class GoodsEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -2496668008984916073L;

    /**
     * 商品名
     */
    private String goodsName;
    /**
     * 商品标题
     */
    private String goodsTitle;
    /**
     * 商品详情
     */
    private String goodsDetail;
    /**
     * 价格
     */
    private Double goodsPrice;
    /**
     * 库存
     */
    private Integer goodsStock;
}
