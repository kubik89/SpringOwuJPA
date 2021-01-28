package com.vb.less.demo.dao;

import com.vb.less.demo.entity.Director;
import com.vb.less.demo.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DirectoryRepository extends JpaRepository<Director, Integer> {

    @Query("select d from Director d join d.movies where d.name like :name")
    Director findMoviesByDirectorName(String name);
}
