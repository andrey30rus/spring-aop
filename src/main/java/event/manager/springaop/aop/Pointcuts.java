package event.manager.springaop.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    @Pointcut("execution(* event.manager.springaop.service.BookService.get*(..))")
    public void allGetMethods(){}

    @Pointcut("execution(* event.manager.springaop.service.BookService.add*(..))")
    public void allAddMethods(){}
}
