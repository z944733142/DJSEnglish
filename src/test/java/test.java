import com.DJSEnglish.dao.UserMapper;
import com.DJSEnglish.pojo.User;
import com.DJSEnglish.util.MD5Util;
import com.DJSEnglish.util.PhoneUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
        user.setUsername("zs944733142");
        user.setPassword(MD5Util.MD5EncodeUtf8("1008600"));
        user.setName("测试");
        user.setMsg("测试人员");
        user.setImg("default.jpg");
        user.setEmail("test@test.com");
        userMapper.insertSelective(user);
    }

    @Test
    public void test4()
    {
        System.out.println(MD5Util.MD5EncodeUtf8("10086000"));
    }
}
