package com.simplemovie.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.simplemovie.entity.Show;

public interface ShowRepository extends JpaRepository<Show, Long> {

    List<Show> findByMovieMovieId(Long movieId);
}
