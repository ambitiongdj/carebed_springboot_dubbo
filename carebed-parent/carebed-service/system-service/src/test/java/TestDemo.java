import com.carebed.SystemServiceApplication;
import com.carebed.business.service.ITStakeholderGroupDoctorService;
import com.carebed.common.constant.Constants;
import com.carebed.common.utils.StringUtils;
import com.carebed.manage.entity.SysDictData;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={SystemServiceApplication.class})// 指定启动类
public class TestDemo {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ITStakeholderGroupDoctorService stakeholderGroupDoctorService;


    @Test
    public void test(){
        Map entries = redisTemplate.opsForHash().entries(Constants.SYS_DICT_CACHE);
        List<SysDictData> list = StringUtils.cast(entries.get(Constants.SYS_DICT_KEY + "permission_add_order_special"));
        for (SysDictData sysDictData: list) {
            System.out.println(sysDictData.getDictValue());
        }
    }

    @Test
    public void testSGD(){
        String ss = "10,20";
        System.out.println(stakeholderGroupDoctorService.insertTStakeholderGroupDoctors(1L, ss));

    }

}
