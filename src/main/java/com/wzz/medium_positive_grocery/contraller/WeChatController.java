package com.wzz.medium_positive_grocery.contraller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: medium_positive_grocery
 * @description: 微信相关
 * @author: wangzz
 * @create: 2021-02-22 15:20
 */
@RestController
public class WeChatController {


    @GetMapping("/wx/verify")
    public String verify(@RequestParam("signature")String signature,@RequestParam("timestamp")String timestamp,@RequestParam("nonce")String nonce,
                         @RequestParam("echostr")String echostr){

        return echostr;
    }

}
