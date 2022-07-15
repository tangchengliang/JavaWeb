package com.tcl.qqzone.controller;

import com.tcl.qqzone.pojo.Topic;
import com.tcl.qqzone.pojo.UserBasic;
import com.tcl.qqzone.service.TopicService;
import com.tcl.qqzone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class UserController {

    private UserBasicService userBasicService;
    private TopicService topicService;

    public String login(String loginId, String pwd, HttpSession session) {
        UserBasic userBasic = userBasicService.login(loginId, pwd);
        if(userBasic!=null){
            // 登录成功，转接页面
            // 注意，登录成功后转接的页面还需要有： 日志列表，好友列表
            List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
            List<Topic> topicList = topicService.getTopicList(userBasic);

            userBasic.setFriendList(friendList);
            userBasic.setTopicList(topicList);

            session.setAttribute("userBasic", userBasic);

            return "index";
        }else {
            // 登录失败，返回login页面
            return "login";
        }
    }
}
