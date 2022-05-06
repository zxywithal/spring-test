package org.think.in.spring.validation;

import org.springframework.context.MessageSource;
import org.springframework.validation.*;
import org.think.in.spring.ioc.overview.domain.User;

import java.util.List;
import java.util.Locale;

/**
 * 自定义spring {@link Validator}
 * 自定义validator
 */
public class ValidatorDemo {
    public static void main(String[] args) {
        //1.创建Validator
        UserValidator userValidator = new UserValidator();
        //2.判断是否支持目标对象的类型
        User user = new User();
        System.out.println("user 对象是否被UserValidation 支持校验:" + userValidator.supports(user.getClass()));
        //3.创建Error对象
        Errors errors = new BeanPropertyBindingResult(user, "user");
        userValidator.validate(user,errors);

        List<ObjectError> allErrors = errors.getAllErrors();
        //4.获取MessageSource对象
        MessageSource messageSource = ErrorsMessageDemo.createMessage();

        for (ObjectError error : allErrors) {
            String message = messageSource.getMessage(error.getCode(), error.getArguments(), Locale.getDefault());
            System.out.println(message);
        }
    }
    static class UserValidator implements Validator{
        @Override
        public boolean supports(Class<?> clazz) {
            return User.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            User user = (User) target;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
        }
    }
}
