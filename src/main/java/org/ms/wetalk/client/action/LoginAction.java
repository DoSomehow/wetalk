package org.ms.wetalk.client.action;

import org.ms.wetalk.center.ClientManager;
import org.ms.wetalk.center.LoginManager;
import org.ms.wetalk.client.bo.Message;
import org.ms.wetalk.client.bo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginAction {

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcomePage() {
        //使用thyemleaf组件之后，动态跳转会覆盖默认的静态跳转，所以如果还想访问static下的静态文件，可以使用重定向
        return "redirect:welcome.html";
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam String username, @RequestParam String password) {
        User user = new User(username, password);
        boolean isLogin = LoginManager.login(user);

        ModelAndView mav = new ModelAndView();
        if (isLogin) {
            ClientManager.createClientSocket(user);

            mav.setViewName("main");
            mav.addObject("username", user.getUsername());
            mav.addObject("userList", LoginManager.getLongUsers());

        } else {
            mav.setViewName("redirect:login");
            mav.addObject("errorInfo", "账号或密码错误！");
        }
        return mav;
    }

    @RequestMapping(value = "/sendMsg", method = RequestMethod.GET)
    @ResponseBody
    public String sendMsg(@RequestParam String sessionUser, @RequestParam String targetUser, @RequestParam String msg) {
        Message message = new Message(sessionUser, targetUser, msg);
        ClientManager.sendMsg(message);
        return "success";
    }

    @RequestMapping(value = "/chatPage", method = RequestMethod.GET)
    public String chatPage() {
        return "chat";
    }


}
