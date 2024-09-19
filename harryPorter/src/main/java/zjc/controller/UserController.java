package zjc.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zjc.service.UserService;

@Slf4j
@RestController
@RequestMapping(value = "/hp")
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping (value = "/login")
    public Boolean login(@RequestParam(value = "loginName", required = true) String loginName,
                        @RequestParam(value = "loginPassword", required = true) String loginPassword){
        log.info("用户登录入口,用户名：{}，密码：{}", loginName, loginPassword);
        return userService.login(loginName, loginPassword);
    }

    @GetMapping (value = "/register")
    public Boolean register(@RequestParam(value = "registerName") String registerName,
                        @RequestParam(value = "registerPassword") String registerPassword){
        log.info("用户注册入口,用户名：{}，密码：{}", registerName, registerPassword);
        return userService.register(registerName, registerPassword);
    }
}
