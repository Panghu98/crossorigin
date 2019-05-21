JPA+lombok+CrossOrigin
---
## 关于JPA
* JPA（Java Persistence API)是SUN官方推出的Java持久化规范
* 这里需要一个自定义一个Repository继承JpaRepository
```java
public interface UserRepository extends JpaRepository<User,Integer>{}
```
* 查看JPA接口源码   (T指的是实体类,ID指的是实体类)
```java
@NoRepositoryBean
public interface JpaRepository<T, ID> extends PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T> {
    List<T> findAll();

    List<T> findAll(Sort var1);

    List<T> findAllById(Iterable<ID> var1);

    <S extends T> List<S> saveAll(Iterable<S> var1);

    void flush();

    <S extends T> S saveAndFlush(S var1);

    void deleteInBatch(Iterable<T> var1);

    void deleteAllInBatch();

    T getOne(ID var1);

    <S extends T> List<S> findAll(Example<S> var1);

    <S extends T> List<S> findAll(Example<S> var1, Sort var2);
}
```
* 在JpaRepository可以定义自己的方法,但是一定要符合命名规范,一般来说IDEA会补充方法名

## 关于lombok
* 相关的注解
    * @Getter / @Setter<br>
    注解对象为类，加上该注解后就可使用相应的get,set方法
    * @Data<br>
    注解对象为类，加上该注解后就可使用相应的get和set方法,并且添加equals、canEquals、hashCode、toString方法
    * @NotNull<br>
    注解对象为属性，在参数中使用时，如果调用时传了null值，就会抛出空指针异常
    * @NoArgsConstructor<br>
    注解对象为类，创建一个无参构造方法
    * @AllArgsConstructor<br>
    注解对象为类，创建一个全参数构造方法
    * @EqualsAndHashCode<br>
    注解对象为类，重写equals和hashCOde方法
    * @Slf4j<br>
    对象为类，在类方法中直接调用log.XXX即可记录日志
    * @Synchronized <br>
    用于方法，可以锁定指定的对象，如果不指定，则默认创建一个对象锁定
 ## 关于跨域问题（切忌不要在代码和服务器两端同时设置跨域解决方案）
 参考学习博客 [跨域问题产生的原因](https://segmentfault.com/a/1190000015597029)
 <br>官网 [MDN](https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS#The_HTTP_request_headers)
 <br>这里可以去了解同源策略，CSRF攻击（跨站请求伪造）和Jsonp
  <br>解决方案 [使用@CrossOrigin决绝CORS跨域](https://blog.csdn.net/w_linux/article/details/81142413)
  <br>@CrossOrigin源码
```java
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CrossOrigin {

    String[] DEFAULT_ORIGINS = { "*" };

    String[] DEFAULT_ALLOWED_HEADERS = { "*" };

    boolean DEFAULT_ALLOW_CREDENTIALS = true;

    long DEFAULT_MAX_AGE = 1800;


    /**
     * 同origins属性一样
     */
    @AliasFor("origins")
    String[] value() default {};

    /**
     * 所有支持域的集合，例如"http://domain1.com"。
     * <p>这些值都显示在请求头中的Access-Control-Allow-Origin
     * "*"代表所有域的请求都支持
     * <p>如果没有定义，所有请求的域都支持
     * @see #value
     */
    @AliasFor("value")
    String[] origins() default {};

    /**
     * 允许请求头重的header，默认都支持
     */
    String[] allowedHeaders() default {};

    /**
     * 响应头中允许访问的header，默认为空
     */
    String[] exposedHeaders() default {};

    /**
     * 请求支持的方法，例如"{RequestMethod.GET, RequestMethod.POST}"}。
     * 默认支持RequestMapping中设置的方法
     */
    RequestMethod[] methods() default {};

    /**
     * 是否允许cookie随请求发送，使用时必须指定具体的域
     */
    String allowCredentials() default "";

    /**
     * 预请求的结果的有效期，默认30分钟
     */
    long maxAge() default -1;

}
```
```
对于这个注解是不配参数的，但是有时候跨域需要参数配置，而且跨域默认为*，这个是不安全的
```
* 前端代码<br>
[demo.html](https://github.com/Panghu98/JPA-lombok-CrossOrigin/src/main/resources/templates/demo.html)

### 简单跨域问题和复杂跨域问题
#### 普通请求
设置Access-Control-Allow-Origin: *就可以了（该属性@CrossOrigin注解中默认设置为 *）
#### 复杂请求 preflighted request
在发送真正的请求前, 会先发送一个方法为OPTIONS的预请求(preflight request), 用于试探服务端是否能接受真正的请求.
<br> 如果options获得的回应是拒绝性质的，比如404\403\500等http状态，就会停止post、put等请求的发出。
<br>解决方案： 设置与请求处理
* 方案一：在springsecurity中的webSecurityConfig类中配置Options请求
* 在JWT的Filter中设置与请求处理
相关的处理见GitHub[项目模板](https://github.com/Panghu98/springboot-sample/tree/master/src/main/java/com/example/demo/)
