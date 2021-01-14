package com.vb.less.demo.dao;

import com.vb.less.demo.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

// цей інтерфейс за допомогою класу JpaRepository буде виконувати методи які для класу і БД.
// в Override Methods багато методів під капотом готових, щоб не писати вручну, як колись
// також завдяки JpaRepository і SimpleJpaRepository який в ньому й позначений анотацією @Repository - то
// будуть автоматично створюватися bean-и MovieRepository (а він використовується як поле в MovieController)
public interface MovieRepository extends JpaRepository<Movie, Integer> {


}
