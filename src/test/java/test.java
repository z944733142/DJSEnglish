import com.DJSEnglish.util.PhoneUtil;
import org.junit.Test;

public class test {

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
}
