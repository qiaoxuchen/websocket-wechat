package com.qiao.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiao.redis.RedisService;
import com.qiao.users.bean.ActiveUser;
import com.qiao.users.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

//@Slf4j
@Component
public class TokenToRedis {

    @Autowired
    RedisService redisService;
    @Autowired
    private ObjectMapper mapper;

    /**
     * token 加密
     *
     * @param token token
     * @return 加密后的 token
     */
    public static String encryptToken(String token) {
        try {
            EncryptUtil encryptUtil = new EncryptUtil(Constant.TOKEN_CACHE_PREFIX);
            return encryptUtil.encrypt(token);
        } catch (Exception e) {
            //log.info("token加密失败：", e);
            return null;
        }
    }

    /**
     * token 解密
     *
     * @param encryptToken 加密后的 token
     * @return 解密后的 token
     */
    public static String decryptToken(String encryptToken) {
        try {
            EncryptUtil encryptUtil = new EncryptUtil(Constant.TOKEN_CACHE_PREFIX);
            return encryptUtil.decrypt(encryptToken);
        } catch (Exception e) {
            //log.info("token解密失败：", e);
            return null;
        }
    }


    public String saveTokenToRedis(User user, String token, HttpServletRequest request) throws Exception {
        String ip = IPUtil.getIpAddr(request);

        // 构建在线用户
        ActiveUser activeUser = new ActiveUser();
        activeUser.setUsername(user.getNickname());
        activeUser.setIp(ip);
        activeUser.setToken(token);

        // zset 存储登录用户，score 为过期时间戳
        this.redisService.zadd(Constant.ACTIVE_USERS_ZSET_PREFIX,60d, mapper.writeValueAsString(activeUser));
        // redis 中存储这个加密 token，key = 前缀 + 加密 token + .ip
        this.redisService.set(Constant.TOKEN_CACHE_PREFIX + token + "." + ip, token, 60 * 1000l);

        return activeUser.getId();
    }
}
