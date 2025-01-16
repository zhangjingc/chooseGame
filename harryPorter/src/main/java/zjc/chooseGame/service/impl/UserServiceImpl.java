package zjc.chooseGame.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zjc.chooseGame.bean.UserInfo;
import zjc.chooseGame.mapper.UserInfoMapper;
import zjc.chooseGame.service.UserService;
import zjc.utils.SaltedPasswordHashUtils;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserInfoMapper userInfoMapper;

    /**
     * 用户注册
     */
    public boolean register(String registerName, String registerPassword) {
        log.info("registerName:{},registerPassword:{}", registerName, registerPassword);
        var user = new UserInfo();
        user.setUserName(registerName);
        user.setUserPassword(SaltedPasswordHashUtils.hashPasswordWithSalt(registerPassword));
        userInfoMapper.insertUser(user);
        return true;
    }

    @Override
    public boolean login(String loginName, String loginPassword) {
        UserInfo userInfo = userInfoMapper.queryUserInfoByUserName(loginName);
        if(null != userInfo){
            return SaltedPasswordHashUtils.verifyPassword(userInfo.getUserPassword(), loginPassword);
        }
        return false;
    }
}
