import com.DJSEnglish.dao.UserMapper;
import com.DJSEnglish.pojo.User;
import com.DJSEnglish.util.JWTUtil;
import com.DJSEnglish.util.MD5Util;
import com.DJSEnglish.util.PhoneUtil;
import com.auth0.jwt.interfaces.Claim;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class test {
    @Autowired
    UserMapper userMapper;
    @Test
    public void test()
    {
        PhoneUtil.getVerificationCode("18066877585");
    }

    @Test
    public void test2()
    {
        PhoneUtil.judgeCodeIsTrue("996247", "18066877585");
    }

    @Test
    public void test3()
    {
        User user = new User();
        user.setPhone("18066877585");
        user.setPassword(MD5Util.MD5EncodeUtf8("1008600"));
        user.setName("测试");
        user.setMsg("测试人员");
        user.setImg("default.jpg");
        userMapper.insertSelective(user);
    }

    @Test
    public void test4() throws Exception {
        Map<String, Claim> stringClaimMap = JWTUtil.verifyToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjMsImV4cCI6MTU1NjI4MjY1MCwiaWF0IjoxNTU1Njc3ODUwfQ.cmdwZS4pPjanoqEBdth2bAnfInL5x7mLg75uKnF6O6A");
        System.out.println(stringClaimMap.get("id").asInt());

    }
}
