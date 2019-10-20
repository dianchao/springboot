package com.dianchao;

import com.dianchao.bean.Employee;
import com.dianchao.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootCacheAppTests {
    @Autowired
    EmployeeMapper employeeMapper;

    //操作k-v都是Object
    @Autowired
    RedisTemplate redisTemplate;

    //操作k-v都是字符串
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<Object, Employee> employeeRedisTemplate;

    @Test
    public void contextLoads(){
        System.out.println(employeeMapper.getEmpById(1));
    }

    /**
     * Redis常见的五大数据类型
     * String(字符串)、List(列表)、Set(集合)、Hash(散列)、ZSet(有序集合)
     */
    @Test
    public void test01(){
        stringRedisTemplate.opsForValue().append("msg", "hello");
        System.out.println(stringRedisTemplate.opsForValue().get("msg"));

        stringRedisTemplate.opsForList().leftPush("list", "1");
        stringRedisTemplate.opsForList().leftPush("list", "2");
    }

    /**
     * 测试保存对象
     */
    @Test
    public void test02(){
        Employee emp = employeeMapper.getEmpById(1);
        //默认使用jdk序列化机制，将序列化后的数据保存到redis中
        //redisTemplate.opsForValue().set("emp-1", emp);

        //1.将数据以json字符串的方式存储
        //2.自定义RedisTemplate，指定序列化器
        employeeRedisTemplate.opsForValue().set("emp-1", emp);
    }
}
