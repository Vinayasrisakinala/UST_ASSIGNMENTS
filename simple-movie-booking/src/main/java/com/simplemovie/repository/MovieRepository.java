package com.simplemovie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.simplemovie.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
