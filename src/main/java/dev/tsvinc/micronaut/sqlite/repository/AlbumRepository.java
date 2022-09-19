package dev.tsvinc.micronaut.sqlite.repository;

import dev.tsvinc.micronaut.sqlite.domain.Album;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {
    Optional<Album> findFirst();
}
