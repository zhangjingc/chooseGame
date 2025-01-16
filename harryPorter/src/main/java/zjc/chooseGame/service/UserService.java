package zjc.chooseGame.service;


import org.springframework.stereotype.Service;



@Service
public interface UserService {

    /**
     * 用户注册
     */
    boolean register(String registerName, String registerPassword);

    /**
     * 用户登录
     */
    boolean login(String loginName, String loginPassword);
}
