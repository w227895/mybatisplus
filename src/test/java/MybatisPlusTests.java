import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kebo.MybatisPlusApplication;
import com.kebo.mapper.UserMapper;
import com.kebo.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @description:
 * @author: kb
 * @create: 2020-11-17 20:01
 **/
@SpringBootTest(classes = MybatisPlusApplication.class)
public class MybatisPlusTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        System.out.println(("----- selectAll method test ------"));
        //UserMapper 中的 selectList() 方法的参数为 MP 内置的条件封装器 Wrapper
        //所以不填写就是无任何条件
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setAge(23);
        user.setName("Helen");
        user.setEmail("249183617@qq.com");
        int result = userMapper.insert(user);
        System.out.println(result);
        System.out.println(user);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(1L);
        user.setAge(28);
        int result = userMapper.updateById(user);
        System.out.println(result);
    }

    //测试乐观锁插件
    @Test
    public void testOptimisticLocker() {
        User user = userMapper.selectById(1328674484424626180L);
        user.setAge(24);
        //返回的结果是1
        userMapper.updateById(user);
    }

    //测试乐观锁失败
    @Test
    public void testOptimisticLockerFail() {
        User user = userMapper.selectById(1328674484424626180L);
        user.setAge(240);
        //模拟获得的版本不对，这样修改就会失效
        user.setVersion(user.getVersion() - 1);
        //返回的结果是0
        userMapper.updateById(user);
    }


    @Test
    public void testSelectById() {
        User user = userMapper.selectById(1328674484424626180L);
        System.out.println(user);
    }

    @Test
    public void testSelectBatchIds() {
        List<User> userList = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        userList.forEach(System.out::print);
    }

    @Test
    public void testSelectByMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Helen");
        map.put("age", 18);
        //map中的key对应的是数据库中的列名。例如数据库user_id，实体类是userId，这时map的key需要填写user_id
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::print);
    }

    @Test
    public void testSelectPage() {
        Page<User> page = new Page<>(1, 5);
        userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::print);
        System.out.println(page.getCurrent());
        System.out.println(page.getPages());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
    }
}

