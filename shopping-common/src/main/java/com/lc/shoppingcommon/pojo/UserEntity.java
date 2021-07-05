package com.lc.shoppingcommon.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 刘晨
 * @description 用户表
 * @create 2021/5/14 0014
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Table(name = "miaosha_user")
public class UserEntity extends BaseEntity implements Serializable{

    private static final long serialVersionUID = -1140209348318371204L;
    private String nickName;
    private String pwd;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;
}
