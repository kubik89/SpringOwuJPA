package com.vb.less.demo.dao;

import com.vb.less.demo.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// цей інтерфейс за допомогою класу JpaRepository буде виконувати методи які для класу і БД.
// в Override Methods багато методів під капотом готових, щоб не писати вручну, як колись
// також завдяки JpaRepository і SimpleJpaRepository який в ньому й позначений анотацією @Repository - то
// будуть автоматично створюватися bean-и MovieRepository (а він використовується як поле в MovieController)
public interface MovieRepository extends JpaRepository<Movie, Integer> {

// findByTitle - це готовий метод, але має писатися по конвенції spring, де findBy - це метод, який розуміє,
// що він шукає по полі title всі обєкти що в класі Movie - 'Movie findByTitle(String title);'
// Більше запитів тут - https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
// а також додатково над цим, при потребі, можна дописати додаткову анотацію @Query, яка зробить так само

    @Query("select myMovie from Movie myMovie where myMovie.title like :title")
    Movie findByTitle(String title);

}
