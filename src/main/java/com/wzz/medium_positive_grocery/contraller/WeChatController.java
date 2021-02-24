package com.wzz.medium_positive_grocery.contraller;

import com.wzz.medium_positive_grocery.entity.WxButton;
import com.wzz.medium_positive_grocery.entity.WxMenu;
import com.wzz.medium_positive_grocery.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: medium_positive_grocery
 * @description: 微信相关
 * @author: wangzz
 * @create: 2021-02-22 15:20
 */
@RestController
public class WeChatController {

    @Autowired
    private WxService wxService;

    @GetMapping("/wx/verify")
    public String verify(@RequestParam("signature")String signature,@RequestParam("timestamp")String timestamp,@RequestParam("nonce")String nonce,
                         @RequestParam("echostr")String echostr){

        return echostr;
    }



    @GetMapping("/wx/button")
    public String button(){
        WxMenu wxMenu = new WxMenu();
        List<WxButton> wxButtons = new ArrayList<>();
        WxButton buttonMahjong = new WxButton();
        buttonMahjong.setType("click");
        buttonMahjong.setName("麻将机预定");
        buttonMahjong.setKey("order_mahjong");

        WxButton buttonSnacks = new WxButton();
        buttonSnacks.setType("click");
        buttonSnacks.setName("零食上门");
        buttonSnacks.setKey("order_snacks");

        WxButton buttonFood = new WxButton();
        buttonFood.setType("click");
        buttonFood.setName("深夜食堂");
        buttonFood.setKey("order_food");

        wxButtons.add(buttonFood);
        wxButtons.add(buttonSnacks);
        wxButtons.add(buttonMahjong);
        wxMenu.setButton(wxButtons);

       return wxService.createMenu(wxMenu);
    }

}
