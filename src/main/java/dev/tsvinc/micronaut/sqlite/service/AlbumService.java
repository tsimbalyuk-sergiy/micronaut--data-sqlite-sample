package dev.tsvinc.micronaut.sqlite.service;

import dev.tsvinc.micronaut.sqlite.model.AlbumDto;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    Optional<AlbumDto> getAnyFirstAlbum();

    Optional<List<AlbumDto>> getAlbumByName(String albumName);
}
