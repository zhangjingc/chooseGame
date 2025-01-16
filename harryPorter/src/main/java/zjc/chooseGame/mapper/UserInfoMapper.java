package zjc.chooseGame.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import zjc.chooseGame.bean.UserInfo;

@Repository
@Mapper
public interface UserInfoMapper {
    /**
     * 新增用户
     * @param user
     * @return
     */
    void insertUser(UserInfo user);

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    UserInfo queryUserInfoByUserName(String userName);
}
