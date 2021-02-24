package com.wzz.medium_positive_grocery.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: medium_positive_grocery
 * @description: 菜单按钮
 * @author: wangzz
 * @create: 2021-02-24 14:37
 */
@Data
public class WxButton {

    /**
     * 菜单标题，不超过16个字节，子菜单不超过60个字节
     */
    private String name;
    /**
     * 菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram 表示小程序类型
     */
    private String type;

    /**
     * 菜单KEY值，用于消息接口推送，不超过128字节
     */
    private String key;

    /**
     * 网页 链接，用户点击菜单可打开链接，不超过1024字节。 type为miniprogram时，不支持小程序的老版本客户端将打开本url。
     */
    private String url;

    /**
     *小程序的appid（仅认证公众号可配置)
     */
    @JsonProperty("appid")
    private String appId;

    /**
     *小程序的页面路径
     */
    @JsonProperty("pagepath")
    private String pagePath;

    /**
     *调用新增永久素材接口返回的合法media_id
     */
    @JsonProperty("media_id")
    private String mediaId;

    /**
     * 子菜单
     */
    @JsonProperty("sub_button")
    private List<WxButton> subButton;
}
