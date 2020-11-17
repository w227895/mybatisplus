package com.kebo.po;

import com.baomidou.mybatisplus.annotation.*;
import com.sun.javafx.beans.IDProperty;
import com.sun.xml.internal.bind.v2.model.core.ID;
import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: kb
 * @create: 2020-11-17 19:59
 **/
@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    //自动填充创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //自动填充更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /*
    支持的数据类型只有 int,Integer,long,Long,Date,Timestamp,LocalDateTime
    整数类型下 newVersion = oldVersion + 1
    newVersion 会回写到 entity 中
    仅支持 updateById(id) 与 update(entity, wrapper) 方法
    在 update(entity, wrapper) 方法下, wrapper 不能复用!!!
    */
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;
}

