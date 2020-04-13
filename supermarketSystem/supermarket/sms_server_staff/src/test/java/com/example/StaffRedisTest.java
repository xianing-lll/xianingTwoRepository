package com.example;

import com.example.dao.EmployeeInformationDao;
import com.example.domain.EmployeeInformation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;

import java.util.*;

/**
 * 当你的redis数据库里面本来存的是字符串数据或者你要存取的数据就是字符串类型数据的时候，那么你就使用StringRedisTemplate即可。
 * 　　　但是如果你的数据是复杂的对象类型，而取出的时候又不想做任何的数据转换，直接从Redis里面取出一个对象，那么使用RedisTemplate是更好的选择
 * redisTemplate和stringRedisTemplate所操作的数据不互通
 */
@SpringBootTest
public class StaffRedisTest {

//    @Autowired(required = false)
//    EmployeeInformationDao employeeInformationDao;
//
//    @Autowired
//    StringRedisTemplate stringRedisTemplate;  //当value值为字符串时使用
//
//    @Autowired
//    RedisTemplate redisTemplate; //value值为非字符串时使用
//
//
//
//    //把员工类以Map<id, Employee>的形式存入
//    @Test
//    public void test1(){
//        ArrayList<EmployeeInformation> employeeInformations= (ArrayList<EmployeeInformation>) employeeInformationDao.findAllEmployeeInformation();
//
//        Map<String, String> employeeInformationMap = new HashMap<>();
//        for (EmployeeInformation e:employeeInformations
//             ) {
//            System.out.println(e);
//            employeeInformationMap.put(String.valueOf(e.getEmployeeId()),String.valueOf(e));
//        }
//        stringRedisTemplate.opsForHash().putAll("employeeInformationMap",employeeInformationMap);
//    }
//
//    //从redis中读取员工信息，员工数量
//    @Test
//    public void test2(){
//        int size= Math.toIntExact(stringRedisTemplate.opsForHash().size("employeeInformationMap"));
//        String em= (String) stringRedisTemplate.opsForHash().get("employeeInformationMap","1003");
//        System.out.println(size);
//        System.out.println(em);
//        System.out.println(stringRedisTemplate.opsForValue().get("1003"));
//    }
//
//    //RedisTemplate应用，适用于复杂数据类型
//    @Test
//    public void findinformationFromRedisByRedisTemplate(){
//        Map<Integer,EmployeeInformation> employeeInformationMapTwo=new HashMap<>();
////        EmployeeInformation employeeInformation=  employeeInformationDao.findEmployeeInformationByEmployeeId(1003);
////
////        redisTemplate.opsForValue().set("employeeInformation",employeeInformation);
//        EmployeeInformation employeeInformation1= (EmployeeInformation) redisTemplate.opsForValue().get("employeeInformation");
//        System.out.println(employeeInformation1.getEmployeeId());
//        System.out.println(redisTemplate.opsForHash().get("employeeInformationMap2",1003));
//        ArrayList<EmployeeInformation> employeeInformations= (ArrayList<EmployeeInformation>) redisTemplate.opsForHash().values("employeeInformationMap2");
//        for (EmployeeInformation e:employeeInformations
//             ) {
//            System.out.println(e.getEmployeeId());
//        }
//
//        Map<Integer,EmployeeInformation> employeeInformationMap=new HashMap<>();
//        employeeInformationMap=redisTemplate.opsForHash().entries("employeeInformationMap2");
//        for (EmployeeInformation e:employeeInformationMap.values()
//             ) {
//            System.out.println(e);
//        }
//    }
//    //使用自定义序列化器测试redisTemplate
//    @Test
//    public void testredisTemplate(){
//        Map<Integer,EmployeeInformation> employeeInformationMapTwo=new HashMap<>();
//        EmployeeInformation employeeInformation=  employeeInformationDao.findEmployeeInformationByEmployeeId(1003);
//        redisTemplate.opsForValue().set("employeeInformation3",employeeInformation);
//
//    }
//    //使用自定义序列化器测试redisTemplate,读取
//    @Test
//    public void testduqu(){
//        EmployeeInformation employeeInformation= (EmployeeInformation) redisTemplate.opsForValue().get("employeeInformation2");
//        System.out.println(employeeInformation);
//    }
//
//    //delete
//    @Test
//    public void updateredis(){
//        BoundHashOperations boundHashOperations=stringRedisTemplate.boundHashOps("employeeInformationMap");
//        //删除两个key
//        boundHashOperations.delete("1008","1009");
//        //查看1008，1009是否存在
//        System.out.println(boundHashOperations.get("1008"));
//        System.out.println(boundHashOperations.get("1009"));
//    }
//
//    //redis Map中添加一个员工,查找所有员工，以及判断员工是否存在
//    @Test
//    public void addredis(){
//        BoundHashOperations boundHashOperations=stringRedisTemplate.boundHashOps("employeeInformationMap");
//        EmployeeInformation employeeInformation=employeeInformationDao.findEmployeeInformationByEmployeeId(1008);
//        boundHashOperations.put("1008",employeeInformation.toString());
//
//        System.out.println(boundHashOperations.hasKey("1009"));
//        System.out.println(stringRedisTemplate.opsForHash().hasKey("employeeInformationMap","1008"));
//        System.out.println("所以员工信息："+stringRedisTemplate.opsForHash().values("employeeInformationMap"));
//
//    }
//
//    //redis事务，集群模式不支持事务，以下方法针对单个redis
//    @Test
//    public void fenBuShiSock(){
//        redisTemplate.opsForValue().set("key1","value1");
//        List list= (List) redisTemplate.execute(new SessionCallback() {
//            @Override
//            public Object execute(RedisOperations redisOperations) throws DataAccessException {
//                //监控key1共享值
//                redisOperations.watch("key1");
//                //开启事务，在exec命令执行前，全部都只能进入队列
//                redisOperations.multi();
//                redisOperations.opsForValue().set("key2","value2");
//                System.out.println("key2值"+redisOperations.opsForValue().get("key2"));
//                redisOperations.opsForValue().set("key3","value3");
//                System.out.println("key2值"+redisOperations.opsForValue().get("key3"));
//                return redisOperations.exec();
//            }
//        });
//
//        System.out.println(list);
//    }
//
//    //redis流水线
//    @Test
//    public void liuShuiXian(){
//        redisTemplate.opsForValue().set("key1","value1");
//        Long start=System.currentTimeMillis();
//        List list= (List) redisTemplate.executePipelined(new SessionCallback() {
//            @Override
//            public Object execute(RedisOperations redisOperations) throws DataAccessException {
//
//                for (int i = 0; i < 100000; i++) {
//
//                    redisOperations.opsForValue().set("key"+i,"123"+i);
//                    String value= (String) redisOperations.opsForValue().get("key"+i);
//                    //System.out.println(value);
//                }
//                return null;
//            }
//        });
//        Long end=System.currentTimeMillis();
//        System.out.println(end-start);
//        System.out.println(list.size());
//    }
//    @Test
//    public void find(){
//        for (int i = 0; i < 100; i++) {
//            System.out.println(redisTemplate.opsForValue().get("key"+i));
//        }
//    }
}
