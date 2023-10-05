package com.itguigu.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName news_headline
 */
//@TableName(value ="news_headline")

//table-prefix: news_+headline->@TableName可以删除
@Data
public class Headline implements Serializable {
    @TableId
    private Integer hid;

    private String title;

    private String article;

    private Integer type;

    private Integer publisher;

    private Integer pageViews;

    private Date createTime;

    private Date updateTime;

    @Version
    private Integer version;

    private Integer isDeleted; //全局配置中已经配置了

    private static final long serialVersionUID = 1L;
}