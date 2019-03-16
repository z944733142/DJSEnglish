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
        user.setPhone("13227769909");
        user.setPassword("1008600");
        user.setName("测试");
        user.setMsg("的旧爱受打击扫单欧凡覅欧艾斯");
        user.setImg("null");
        user.setEmail("test@test.com");
        userMapper.insertSelective(user);
    }

}
