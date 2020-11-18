import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kebo.MybatisPlusApplication;
import com.kebo.mapper.UserMapper;
import com.kebo.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

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

    @Test
    public void testSelectMapsPage() {
        Page<User> page = new Page<>(1, 5);
        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(page, null);
        //注意：此行必须使用 mapIPage 获取记录列表，否则会有数据类型转换错误
        mapIPage.getRecords().forEach(System.out::print);
        System.out.println(page.getCurrent());
        System.out.println(page.getPages());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
    }

    @Test
    public void testDeleteById() {
        int result = userMapper.deleteById(1L);
        System.out.println(result);
    }

    @Test
    public void deleteBatchIds() {
        int result = userMapper.deleteBatchIds(Arrays.asList(2L, 3L));
        System.out.println(result);
    }

    @Test
    public void testDeleteByMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Helen");
        map.put("age", 18);
        int result = userMapper.deleteByMap(map);
        System.out.println(result);
    }

    /* 测试逻辑删除*/
    @Test
    public void testLogicDelete() {
        int result = userMapper.deleteById(5L);
        System.out.println(result);
    }

    @Test
    public void testDelete() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("name")
                .ge("age", 12)
                .isNotNull("email");
        int result = userMapper.delete(queryWrapper);
        System.out.println(result);
    }

    @Test
    public void testSelectOne() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Sandy");
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    @Test
    public void testSelectCount() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("age", 20, 30);
        Integer count = userMapper.selectCount(queryWrapper);
        System.out.println(count);
    }

    @Test
    public void testSelectList01() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", 2);
        map.put("name", "Jack");
        map.put("age", 20);
        queryWrapper.allEq(map);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::print);
    }

    @Test
    public void testSelectMaps() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.notLike("name", "e")
                .likeRight("email", "t");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::print);
    }

    @Test
    public void testSelectObjs() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", 1, 2, 3);
        //queryWrapper.inSql("id", "select id from user where id < 3");
        List<Object> objects = userMapper.selectObjs(queryWrapper);//返回值是Object列表
        objects.forEach(System.out::println);
    }

    @Test
    public void testSelectListOrderBy() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::print);
    }

    @Test
    public void testSelectListColumn() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name", "age");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }
}

