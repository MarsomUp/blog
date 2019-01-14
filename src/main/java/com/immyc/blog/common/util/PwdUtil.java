package com.immyc.blog.common.util;/**
 * @Description:
 * @Author:mayc
 * @Date:2019/1/10 23:21
 */

import com.immyc.blog.admin.model.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @Description:
 * @Author: mayc
 * @Date: 2019/01/10 23:21
 */
public class PwdUtil {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    public static final String ALGORITHM_NAME = "md5";
    public static final int HASH_ITERATIONS = 2; // 自定义散列次数

    public void encryptPassword(User user) {
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPwd = new SimpleHash(ALGORITHM_NAME, user.getPwd(),
                ByteSource.Util.bytes(user.getSalt()), HASH_ITERATIONS).toHex();
        user.setPwd(newPwd);
    }
}
