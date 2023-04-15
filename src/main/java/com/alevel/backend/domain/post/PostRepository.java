package com.alevel.backend.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface PostRepository extends JpaRepository<Post,Long> {

    Optional<Post> findByUserId(Long id);

    @Query(value = "SELECT COUNT(*) FROM post p WHERE p.user_id=user_id", nativeQuery = true)
    Integer myPostCount(Long id);

    List<Post> findByAlcoholNameContaining(String name);

    Optional<Post> findByIdAndStatusTrue(Long id);

    List<Post> findByStatusTrue();

    List<Post> findByVolumeOrSugar(Long volume, Long sugar);
}

