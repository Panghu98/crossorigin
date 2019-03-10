package com.example.demo.repository;

import com.example.demo.form.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author panghu
 * @Title: UserRepository
 * @ProjectName springboot_test
 * @Description: TODO
 * @date 19-3-8 下午9:58
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    /**
     * 这里的方法命名要规范,一般来说这里IDEA会自动根据要求补充方法名
     * hibernate底层将该方法名映射到对应的基本方法
     * @return
     */
    User findByMessage();
}
