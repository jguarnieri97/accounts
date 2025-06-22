package ar.edu.unlam.tpi.accounts.persistence.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tpi.accounts.models.LabelEntity;

@Repository
public interface LabelDAO extends JpaRepository<LabelEntity, Long> {
    Optional<LabelEntity> findByTag(String tag);
}