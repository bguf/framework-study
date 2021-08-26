package com.bguf.config;

import com.bguf.aop.LogAspects;
import com.bguf.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 *  AOP：【动态代理】
 *      在程序运行期间动态地将某段代码切入到指定方法指定位置进行运行的编程方式
 *  1. 导入aop模块：spring aop: (spring-aspects)
 *  2. 创建业务逻辑类，在业务逻辑运行的时候，将日志打印（方法运行前，运行结束，出现异常）
 *  3. 定义一个日志切面类，切面类里的方法需要动态感知方法运行的进度，执行响应日志方法
 *      通知方法：
 *          前置通知(@Before)：在目标方法运行前运行
 *          后置通知(@After)：在目标方法运行结束后运行
 *          返回通知(@AfterReturning)：在目标方法正常返回之后运行
 *          异常通知(@AfterThrowing)：在目标方法运行异常后运行，记录异常信息
 *          环绕通知(@Around)：动态代理，手动推进目标方法运行(joinPoint.procced())
 *  4. 给切面类的目标方法标志何时运行（通知注解）
 *  5. 将切面类和业务逻辑类（目标方法所在类）都加入到容器中
 *  6. 必须告诉spring哪个类是切面类（给切面类上加注解@Aspect）
 *  7. 开启aop，配置类加@EnableAspectJAutoProxy
 *
 *  三步：
 *  1. 将业务逻辑组件和切面类都加入到容器中：告诉spring哪个是切面类（@Aspect）
 *  2. 在切面类上的每一个通知方法上标注通知注解，告诉spring何时何地运行（切入点表达式）
 *  3. 开启基于注解的aop模式：@EnableAspectJAutoProxy
 *
 *  AOP原理：看给容器中注册了什么组件，这个组件什么时候工作，工作的功能
 *  1、@EnableAspectJAutoProxy是什么？
 *      给容器中导入AspectJAutoProxyRegistrar，
 *      利用AspectJAutoProxyRegistrar自定义给容器中注册bean
 *      给容器注册一个AnnotationAwareAspectJAutoProxyCreator；
 *  2. AnnotationAwareAspectJAutoProxyCreator：extends 多层父类-->AbstractAutoProxyCreator
 *      这个类继承的父类实现了后置处理器，主要关注后置处理器（bean初始化完成前后做的事情）
 *      这个类继承的父类实现了bean工厂
 * ================
 *      1、传入配置类，创建ioc容器
 *          a. 注册配置类、
 *          b. 刷新容器
 *          c. registerBeanPostProcessors(beanFactory):
 *              注册bean的后置处理器，拦截bean的创建(registerBeanPostProcessors)
 *                  1)、先获取ioc容器已经定义了的需要创建对象的所有BeanPostProcessor
 *                  2）、给容器中加其他beanPostProcessor
 *                  3）、优先注册实现了PriorityOrdered接口的BeanPostProcessor
 *                  4）、再给容器中注册实现了Ordered接口的BeanPostProcessor
 *                  5）、注册没有实现优先级接口的BeanPostProcessor
 *                  6）、注册BeanPostProcessor，实际就是创建BeanPostProcessor，保存在容器中
 *                      创建internalAutoProxyCreator的BeanPostProcessor【AnnotationAwareAspectJAutoProxyCreator】
 *                      1）、创建bean的实例
 *                      2）、populateBean：给bean的各种属性赋值
 *                      3）、initializeBean：初始化bean
 *                          1）、invokeAwareMethods：处理Aware接口的方法回调
 *                          2）、applyBeanPostProcessorBeforeInitialization：应用后置处理器的postProcessorBeforeInitialization
 *                          3）、invokeInitMethods：执行初始化方法
 *                          4）、applyBeanPostProcessorAfterInitialization：应用后置处理器的postProcessorAfterInitialization
 *                      4）、BeanPostProcessor（AnnotationAwareAspectJAutoProxyCreator）创建成功
 *                  7）、把BeanPostProcessor注册到BeanFactory中：beanFactory.addBeanPostProcessor(postProcessor);
 *            ========以上创建和注册AnnotationAwareAspectJAutoProxyCreator流程=======
 *            d. finishBeanFactoryInitialization(beanFactory)：完成BeanFactory初始化工作--创建剩下的单实例bean
 *              1）、遍历获取容器中所有的bean，依次创建对象getBean(beanName)
 *                  getBean->doGetBean()->getSingleton()->
 *              2）、创建bean
 *                  1）、先从缓存中获取当前bean，如果能获取到，说明bean是之前被创建过的，直接使用，否则再创建
 *                      只要创建好的bean都会被缓存起来
 *                  2）、createBean()：创建bean
 *                      1）、resolveBeforeInstantiation(beanName, mbdToUse)：解析BeforeInstantiation
 *                          希望后置处理器在此能够返回一个代理对象，如果能够返回代理对象就使用，如果不能就继续
 *                      2）、doCreateBean(beanName, mbdToUse, args)：真正地去创建一个bean，和c.6流程一样
 *
 * ===============
 *  1. 每个bean创建之前，调用postProcessorBeforeInstantiation()：
 *      关心MathCalCulator和LogAspcet的创建
 *      1）、判断当前bean是否在advisedBeans（保存了所有需要增强的bean）
 *      2）、判断当前bean是否是基础类型的Advice、Pointcut、Advisor、AopInfrastructureBean或者是否是切面（@Aspect)
 *      3）、判断是否需要跳过
 *          1）、获取候选的增强器（切面里面的通知方法）【List<Advisor>】
 *              每一个封装的通知方法的增强器是InstantiationModelAwarePointcutAdvisor
 *              判断每一个增强器是否是AspectJPointcutAdvisor类型
 *          2）、返回false
 *  2. 创建对象后，调用postProcessorAfterInstantiation
 *      return wrapIfNecessary(bean, beanName, cacheKey)；包装如果需要的情况下
 *      1）、获取当前bean的所有增强器（通知方法）
 *          1、找到候选的所有增强器（找哪些通知方法是需要切入当前通知方法的）
 *          2、获取到能在当前bean中使用的增强器
 *          3、对增强器排序
 *      2）、保存当前bean在advisedBeans中
 *      3）、如果当前bean需要增强，创建当前bean的代理对象
 *          1、获取所有增强器（通知方法）
 *          2. 保存增强器到proxyfactory
 *          3. 创建代理对象：spring自动决定
 *              JdkDynamicAopProxy（config）
 *              ObjenesisCglibAopProxy（config）
 *      4）、给容器中返回当前组件使用cglib增强了的代理对象
 *      5）、以后容器中获取到的就是这个组件的代理对象，执行目标方法的时候，代理对象就会执行通知方法的流程
 *  3. 目标方法执行：
 *      容器中保存了组件的代理对象（cglib增强后的对象），这个对象里面保存了详细信息（比如增强器，目标对象，xxx）
 *      1）、CglibAopProxy.intercept()；拦截目标方法的执行
 *      2）、根据ProxyFactory对象获取将要执行的目标方法的拦截器链
 *              1）、创建一个list，用来保存所有拦截器，list的长度=1+自定义的通知方法个数
 *              2）、遍历所有的增强器，将其转为Interceptor
 *              3）、将增强器（通知方法）转为List<MethodInterceptor>
 *                      如果使MethodInterceptor，直接加入到集合中
 *                      如果不是，使用AdvisorAdapter将增强器转为MethodInterceptor
 *                      转换完成返回MethodInterceptor数组
 *      3）、如果没有拦截器链，直接执行目标方法
 *              拦截器链（每一个通知方法又被包装为方法拦截器，利用MethodInterceptor机制）
 *      4）、如果有拦截器链，创建一个CglibMethodInvocation，
 *              把需要执行的目标对象，目标方法，拦截器链等所有信息传过去，并调用cmi的proceed方法
 *      5）、拦截器链的触发过程：
 *              1）、如果没有拦截器执行目标方法，或者拦截器的索引和拦截器数组-1大小一样（指定到了最后一个拦截器）执行目标方法；
 *              2）、链式获取每一个拦截器，拦截器执行invoke方法，每一个拦截器等待下一个拦截器执行完成返回以后再来执行；
 *                      拦截器链的机制，保证通知方法和目标方法执行顺序
 *
 * @author gufb
 * @date 2021/8/24 11:32 AM
 */
@Configuration
@EnableAspectJAutoProxy
public class MainConfigOfAOP {
    @Bean
    public MathCalculator mathCalculator() {
        return new MathCalculator();
    }

    @Bean
    public LogAspects logAspects() {
        return new LogAspects();
    }
}
