package ar.edu.unlam.tpi.accounts.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.unlam.tpi.accounts.models.CommentaryEntity;

public interface CommentaryRepository extends JpaRepository<CommentaryEntity, Long> {
} 