# 使用spring中的@Transactional注解时，可能需要注意的地方
前情提要
在编写业务层方法时，会遇到很多需要事务提交的操作，spring框架为我们提供很方便的做法，就是在需要事务提交的方法上添加@Transactional注解，比起我们自己开启事务、提交以及控制回滚，要简单的多。但是在使用的时候容易犯一些错误。我就自己的错误经历总结如下。

枯燥的背景知识（可以忽略）
编程式事务管理&声明式事务管理:

（一）编程式事务管理 在 Spring 出现以前，编程式事务管理对基于 POJO 的应用来说是唯一选择。用过 Hibernate 的人都知道，我们需要在代码中显式调用beginTransaction()、commit()、rollback()等事务管理相关的方法，这就是编程式事务管理。通过 Spring 提供的事务管理 API，我们可以在代码中灵活控制事务的执行。在底层，Spring 仍然将事务操作委托给底层的持久化框架来执行。

（二）声明式事务管理

1）Spring 的声明式事务管理在底层是建立在 AOP 的基础之上的。其本质是对方法前后进行拦截，然后在目标方法开始之前创建或者加入一个事务，在执行完目标方法之后根据执行情况提交或者回滚事务。

2）声明式事务最大的优点就是不需要通过编程的方式管理事务，这样就不需要在业务逻辑代码中掺杂事务管理的代码，只需在配置文件中做相关的事务规则声明（或通过等价的基于标注的方式），便可以将事务规则应用到业务逻辑中。因为事务管理本身就是一个典型的横切逻辑，正是 AOP 的用武之地。Spring 开发团队也意识到了这一点，为声明式事务提供了简单而强大的支持。

3）声明式事务管理曾经是 EJB 引以为傲的一个亮点， Spring 让 POJO 在事务管理方面也拥有了和 EJB 一样的待遇，让开发人员在 EJB 容器之外也用上了强大的声明式事务管理功能，这主要得益于 Spring 依赖注入容器和 Spring AOP 的支持。依赖注入容器为声明式事务管理提供了基础设施，使得 Bean 对于 Spring 框架而言是可管理的；而 Spring AOP 则是声明式事务管理的直接实现者。

4）建议在开发中使用声明式事务，不仅因为其简单，更主要是因为这样使得纯业务代码不被污染，极大方便后期的代码维护。和编程式事务相比，声明式事务唯一不足地方是，后者的最细粒度只能作用到方法级别，无法做到像编程式事务那样可以作用到代码块级别。但是即便有这样的需求，也存在很多变通的方法，比如，可以将需要进行事务管理的代码块独立为方法等等。

从@Transactional的代码当中可以看到，可配置的参数主要有：

事务隔离级别: Isolation isolation() default Isolation.DEFAULT;
事务传播行为:Propagation propagation() default Propagation.REQUIRED;
回滚、不回滚的异常类:rollbackFor() noRollbackFor()
超时:int timeout() default TransactionDefinition.TIMEOUT_DEFAULT;
mysql的innob引擎支持标准sql事务的四个级别:

REPEATABLE READ(默认)

 | READ COMMITTED

 | READ UNCOMMITTED

 | SERIALIZABLE

默认是可以重复读，以repeatable_read 为例，默认情况它使用了Consistent Nonlocking Reads，其实就是多版本快照方式，一个repeatable_read 事务里面，第一次读取从数据库取，然后这个数据放入快照里面，后面的读取都是从快照里面取，这样虽然保证了一个事务里面读取的数据的一致性，但是会出现其他事务已经改了数据库里面的值，而当前事务却还在使用老的数据。如果对于数据的同步要求很高的话，可以在sql上面使用锁，

select … lock in share mode; //共享锁，可同时读，不能同时写

select … for update; // 排他锁，独占

但是用锁，肯定影响性能，至少mysql没有使用锁来实现repeatable-read隔离级别。

如果是SERIALIZABLE级别，mysql倒是会自动的将select转化为select … LOCK IN SHARE MODE，即使用了共享锁。

事务的传播行为: 所谓事务的传播行为是指，如果在开始当前事务之前，一个事务上下文已经存在，此时有若干选项可以指定一个事务性方法的执行行为。在TransactionDefinition定义中包括了如下几个表示传播行为的常量：

1、TransactionDefinition.PROPAGATION_REQUIRED：如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。

2、TransactionDefinition.PROPAGATION_REQUIRES_NEW：创建一个新的事务，如果当前存在事务，则把当前事务挂起。

3、TransactionDefinition.PROPAGATION_SUPPORTS：如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。

4、TransactionDefinition.PROPAGATION_NOT_SUPPORTED：以非事务方式运行，如果当前存在事务，则把当前事务挂起。

5、TransactionDefinition.PROPAGATION_NEVER：以非事务方式运行，如果当前存在事务，则抛出异常。

6、TransactionDefinition.PROPAGATION_MANDATORY：如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。

7、TransactionDefinition.PROPAGATION_NESTED：如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于TransactionDefinition.PROPAGATION_REQUIRED。

这里需要指出的是，前面的六种事务传播行为是 Spring 从 EJB 中引入的，他们共享相同的概念。而 PROPAGATION_NESTED是 Spring 所特有的。以 PROPAGATION_NESTED 启动的事务内嵌于外部事务中（如果存在外部事务的话），此时，内嵌事务并不是一个独立的事务，它依赖于外部事务的存在，只有通过外部的事务提交，才能引起内部事务的提交，嵌套的子事务不能单独提交。如果熟悉 JDBC 中的保存点（SavePoint）的概念，那嵌套事务就很容易理解了，其实嵌套的子事务就是保存点的一个应用，一个事务中可以包括多个保存点，每一个嵌套子事务。另外，外部事务的回滚也会导致嵌套子事务的回滚。

总结：
Transactional 注解可以被应用于接口定义和接口方法、类定义和类的 public 方法上。
“proxy-target-class” 属性值来控制是基于接口的还是基于类的代理被创建。 <tx:annotation-driven transaction-manager=“transactionManager” proxy-target-class=“true”/> 注意：proxy-target-class属性值决定是基于接口的还是基于类的代理被创建。如果proxy-target-class 属性值被设置为true，那么基于类的代理将起作用（这时需要cglib库）。如果proxy-target-class属值被设置为false或者这个属性被省略，那么标准的JDK 基于接口的代理。
注解@Transactional cglib与java动态代理最大区别是代理目标对象不用实现接口,那么注解要是写到接口方法上，要是使用cglib代理，这是注解事物就失效了，为了保持兼容注解最好都写到实现类方法上。
Spring团队建议在具体的类（或类的方法）上使用 @Transactional 注解，而不要使用在类所要实现的任何接口上。在接口上使用 @Transactional 注解，只能当你设置了基于接口的代理时它才生效。因为注解是 不能继承的，这就意味着如果正在使用基于类的代理时，那么事务的设置将不能被基于类的代理所识别，而且对象也将不会被事务代理所包装。
@Transactional 的事务开启 ，或者是基于接口的 或者是基于类的代理被创建。所以在同一个类中一个方法调用另一个方法有事务的方法，事务是不会起作用的。 原因：（这也是为什么在项目中有些@Async并没有异步执行） spring 在扫描bean的时候会扫描方法上是否包含@Transactional注解，如果包含，spring会为这个bean动态地生成一个子类（即代理类，proxy），代理类是继承原来那个bean的。此时，当这个有注解的方法被调用的时候，实际上是由代理类来调用的，代理类在调用之前就会启动transaction。然而，如果这个有注解的方法是被同一个类中的其他方法调用的，那么该方法的调用并没有通过代理类，而是直接通过原来的那个bean，所以就不会启动transaction，我们看到的现象就是@Transactional注解无效。
Tips： 使用 ~for update ~实现实现悲观锁的时候，需要注意锁的级别，Mysql InnoDB 默认行级锁。行级锁都是基于索引的，如果一条sql语句用不到索引，是不会使用行级锁的，会使用表级，把整张表锁住，这点需要注意。

Tips： 使用乐观锁时多数实现方法是使用版本号，或者时间戳。但是如果事务的隔离级别允许重复读（比如：REPEATABLE_READ；mysql InnoDB默认也是这个级别）,那么使用乐观锁是查询不出版本或者时间戳的变化的,但是oracle的话默认就可以。

Tips： spring默认的事务隔离级别为底层数据区的隔离级别。所以，如果你用的是Mysql的InnoDB引擎，那么级别就是：REPEATABLE READ；如果你用的是oracle，那么级别就是READ COMMITED。

Tips： spring的@Transactional注解事务创博行为默认值为：PROPAGATION_REQUIRED