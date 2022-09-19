package dev.tsvinc.micronaut.sqlite.service;

import dev.tsvinc.micronaut.sqlite.domain.Album;
import dev.tsvinc.micronaut.sqlite.model.AlbumDto;
import dev.tsvinc.micronaut.sqlite.repository.AlbumRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;

    @Override
    public Optional<AlbumDto> getAnyFirstAlbum() {
        return albumRepository.findFirst().map(AlbumServiceImpl::mapAlbumToAlbumDto);
    }

    @Override
    public Optional<List<AlbumDto>> getAlbumByName(String albumName) {
        return Optional.ofNullable(albumRepository.findByAlbumNameEquals(albumName))
                .map(albums -> albums.stream()
                        .map(AlbumServiceImpl::mapAlbumToAlbumDto)
                        .collect(Collectors.toList()))
                .map(Optional::of)
                .orElse(null);
    }

    private static AlbumDto mapAlbumToAlbumDto(Album album) {
        return AlbumDto.builder()
                .albumName(album.getAlbumName())
                .artistName(album.getArtistName())
                .genre(album.getGenre())
                .year(album.getYear())
                .build();
    }
}
