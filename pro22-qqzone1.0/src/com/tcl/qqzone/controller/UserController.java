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

            // userBasic 这个key保存的是登录者信息
            // friend 这个key保存的是当前进入的是谁的空间
            session.setAttribute("userBasic", userBasic);
            session.setAttribute("friend", userBasic);

            return "index";
        }else {
            // 登录失败，返回login页面
            return "login";
        }
    }

    public String friend(Integer id, HttpSession session){
        //1. 根据id获取用户信息,
        // 需要在userBasicService接口中添加方法getUserBasicById，并实现
        UserBasic currFriend = userBasicService.getUserBasicById(id);

        // 2.获取该用户的日志
        List<Topic> topicList = topicService.getTopicList(currFriend);
        currFriend.setTopicList(topicList);

        // 这里的friend和登录中的相呼应，表示更改了friend的值，而userBasic没有改变，因此可以拿来判断是否在自己的空间
        session.setAttribute("friend", currFriend);

        return "index";
    }
}
