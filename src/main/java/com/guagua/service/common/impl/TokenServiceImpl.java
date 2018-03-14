package com.guagua.service.common.impl;

import com.guagua.bean.dto.ResultDto;
import com.guagua.bean.entity.common.User;
import com.guagua.dao.common.UserDao;
import com.guagua.enums.DataDictionary;
import com.guagua.service.BaseService;
import com.guagua.service.common.TokenService;
import com.guagua.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ride
 * @date 18-3-13 下午5:44
 * <p>
 * token service 实现类
 */
@Service("tokenService")
public class TokenServiceImpl extends BaseService implements TokenService {

    private final UserDao userDao;

    @Autowired
    public TokenServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    // 刷新token请求
    public ResultDto refresh(Integer userId) {

        User user = userDao.findById(userId);
        if (user == null) {
            // 更新失败
            return new ResultDto(DataDictionary.REFRESH_TOKEN_FAIL);
        }

        logger.info("##########user ====> {} ##########", user);

        Map<String, Object> newClaims = new HashMap<String, Object>();
        newClaims.put("userId", user.getId());
        newClaims.put("phone", user.getPhone());

        String token = JWTUtils.createToken(newClaims);
        return new ResultDto(DataDictionary.REFRESH_TOKEN_SUCCESS).addData("token", token);
    }
}
