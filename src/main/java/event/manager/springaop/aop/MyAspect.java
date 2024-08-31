package event.manager.springaop.aop;

import event.manager.springaop.entity.Book;
import event.manager.springaop.util.CustomResponse;
import event.manager.springaop.util.CustomStatus;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;



@Component
@Aspect
@Slf4j
public class MyAspect {
//    Around advice for all add methods
    @Around("Pointcuts.allAddMethods()")
    public Object aroundAddingAdvice(ProceedingJoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Book book = null;
        if (methodSignature.getName().equals("addBook")){
            Object[] arguments = joinPoint.getArgs();
            for (Object arg: arguments){
                if(arg instanceof Book){
                    book = (Book) arg;
                    log.info("Попытка добавить книгу с названием {}", book.getTitle());
                }
            }
        }
        Object result = null;
        try {
            result = joinPoint.proceed();
        }catch (Throwable throwable){
            log.error(throwable.getMessage(), throwable);
            result =  new CustomResponse<>(null, CustomStatus.EXCEPTION);
        }
        log.info("Книга с названием {} успешно сохранена", book.getTitle());
        return result;
    }

}
