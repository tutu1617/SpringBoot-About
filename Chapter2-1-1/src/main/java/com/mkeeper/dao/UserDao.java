package com.mkeeper.dao;

import com.mkeeper.entity.User;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class UserDao {
    // 数据层：查询数据库，这里为了简单用Map模拟

    private static Integer count = 0;

    // 线程安全的Map
    private static Map<Integer, User> userMap = Collections.synchronizedMap(new HashMap<>());

    static {
        // 静态代码块，第一次初始化类时被调用
        // 初始化userMap
        userMap.put(++count, User.builder().userId(count).name("Mkeeper").age(28).address("wuhan").build());
        userMap.put(++count, User.builder().userId(count).name("Jam").age(22).address("beijing").build());
        userMap.put(++count, User.builder().userId(count).name("Li").age(18).address("shanghai").build());
    }

    public User findByUserId(Integer userId){
        return userMap.get(userId);
    }

    public List<User> findList(){
        return new ArrayList<>(userMap.values());
    }

    public User addUser(User user){
        user.setUserId(++count);
        userMap.put(count, user);
        return user;
    }

    public User updateUser(User user){
        userMap.put(user.getUserId(), user);
        return user;
    }

    public boolean deleteByUserId(Integer userId){
        userMap.remove(userId);
        return true;
    }
}
