package com.message.messageboard.repository;

import com.message.messageboard.entity.Board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
  Long countByDeleted(@Param("deleted") boolean deleted);
}
