package com.lc.shoppingcommon.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author 刘晨
 * @description 公共字段Entity
 * @create 2021/5/8 0008
 * @since 1.0.0
 */
@Data
//@MappedSuperclass
public class BaseEntity {

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    /** 创建人 */
    private Long createBy;
    /** 创建时间 */
    @Column(updatable = false, insertable = true,columnDefinition = "datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间,自动生成'")
    private Date createTime ;
    /** 更新人 */
    private Long updateBy;
    /** 更新时间 */
    @Column(updatable = true, insertable = true,columnDefinition = "datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间,自动生成'")
    private Date updateTime ;
}
