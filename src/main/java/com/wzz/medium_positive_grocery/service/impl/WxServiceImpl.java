package com.wzz.medium_positive_grocery.service.impl;

import com.wzz.medium_positive_grocery.common.constant.WxConstant;
import com.wzz.medium_positive_grocery.common.utils.HttpClientUtils;
import com.wzz.medium_positive_grocery.entity.WxMenu;
import com.wzz.medium_positive_grocery.service.WxService;
import org.springframework.stereotype.Service;

/**
 * @program: medium_positive_grocery
 * @description: 微信相关接口实现
 * @author: wangzz
 * @create: 2021-02-24 15:14
 */
@Service
public class WxServiceImpl implements WxService {



    @Override
    public String getAccessToken(String grantType, String appId, String secret) {
        return HttpClientUtils.doGet(WxConstant.ACCESS_TOKEN_URL+"?grant_type="+grantType+"&appid="+appId+"&secret="+secret);
    }

    @Override
    public String createMenu(WxMenu wxMenu) {
        return null;
    }
}
