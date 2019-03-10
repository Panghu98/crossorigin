package com.example.demo.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author panghu
 * @Title: User
 * @ProjectName springboot_test
 * @Description: TODO
 * @date 19-3-8 下午9:44
 */

@Data
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
@Entity
@Table(name = "tb_user")
public class User {
    @Id
    private int id;

    @Column
    private String username;

    /**
     * 出现为null的错误的时候就会爆出这个错误
     */
    @NotNull(message = "password不能为空")
    private String password;

    /**
     * 不添加@Column注解依然写入数据库,这个注解只是写入字段名
     */
    @Column(name = "message")
    private String message;
}
