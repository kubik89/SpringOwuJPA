package com.vb.less.demo.validation;

import com.vb.less.demo.entity.Movie;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

// цей клас створюємо для того, щоб описувати перевірки (Validators) для класів.
// Бо можна використовувати багато однакових валідаторів, а ми не хочемо дублювати код.
// Тому будемо звертатися до нашого валідатора, як до класу.
public class MovieValidator implements Validator {

    @Override
// перевірка чи клас який прийшов (який проходить валідність) співпадає із Movie,
// якщо так, то заходимо в MovieValidator клас і його методи
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(Movie.class);
    }
// а тут описуємо на які помилки на валідність буде реагувати клас
// чи Title не пустий і чи починається з Великої літери
    @Override
    public void validate(Object o, Errors errors) {
        Movie movie = (Movie) o;
        char firstLetter = movie.getTitle().charAt(0);
// якщо в полі title перша літера НЕ велика, то показати помилку
        if (StringUtils.isNotBlank(movie.getTitle()))
        if (!CharUtils.isAsciiAlphaUpper(firstLetter)) {
            errors.rejectValue("title", "title-capital-error", "Value must have first CAPITAL letter(from MovieValidator)");
        }
    }
}
