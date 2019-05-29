package org.ms.wetalk.client.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginAction {

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome.jsp";
    }


}
