package com.wzz.medium_positive_grocery.service;

import com.wzz.medium_positive_grocery.entity.WxMenu;
import org.springframework.cache.annotation.Cacheable;

/**
 * @program: medium_positive_grocery
 * @description: 微信相关接口
 * @author: wangzz
 * @create: 2021-02-24 15:09
 */
public interface WxService {

    /**
     * 获取Access token
     * @param grantType 获取access_token填写client_credential
     * @param appId 第三方用户唯一凭证
     * @param secret 第三方用户唯一凭证密钥，即 app secret
     * @return
     */
    @Cacheable(value = {"ACCESS_TOKEN"}, key = "#appId")
    String getAccessToken(String grantType,String appId,String secret);


    /**
     * 创建微信自定义菜单
     * @param wxMenu 菜单对象
     * @return
     */
    String createMenu(WxMenu wxMenu);
}
