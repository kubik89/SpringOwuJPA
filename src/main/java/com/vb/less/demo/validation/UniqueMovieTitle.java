package com.vb.less.demo.validation;

// досить старий метод валідації - через свою анотацію.

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// кожна анотація яку створюємо вручну повинна мати поверх себе 2 інші анотації - Target і Retention
// (в дужка ми вказуємо на якому етапі вони мають відпрацьовувати) - в нашому випадку для ПОЛЯ і при запуску (RUNTIME)
// @Constraint - вказуємо де буде описуватися перевірка (в новому класі **** ) і цим вказуємо, що наша анотація
// буде типу Valid (вона не від Spring бо належить бібліотеці javax)
@Constraint(validatedBy = UniqueMovieTitleValidator.class)
@Target({ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface UniqueMovieTitle {

    String message() default "Title must be UNIQUE";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
