package dev.tsvinc.micronaut.sqlite;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import dev.tsvinc.micronaut.sqlite.repository.AlbumRepository;
import dev.tsvinc.micronaut.sqlite.domain.AlbumDto;
import dev.tsvinc.micronaut.sqlite.domain.Album;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AlbumController {
    private final AlbumRepository albumRepository;

    @Get("/album")
    @Produces(value = MediaType.APPLICATION_JSON)
    public HttpResponse<AlbumDto> getAlbum() {
        return albumRepository
                .findFirst()
                .map(album ->
                        new AlbumDto(album.getAlbumName(), album.getArtistName(), album.getGenre(), album.getYear()))
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }

    @Get("/album-store-random")
    public HttpResponse<AlbumDto> storeRandomAlbum() {
        final var album = new Album(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                ThreadLocalRandom.current().nextInt(1900, 2022));
        return Optional.of(albumRepository.save(album))
                .map(album1 ->
                        new AlbumDto(album.getAlbumName(), album.getArtistName(), album.getGenre(), album.getYear()))
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }
}
