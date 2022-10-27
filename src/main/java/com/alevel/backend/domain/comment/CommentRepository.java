package com.alevel.backend.domain.comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByUserId(Long id);

    @Query(value="SELECT COUNT(*) FROM comment c WHERE c.user_id=user_id",nativeQuery = true)
    Integer myCommentCount(Long id);


}

