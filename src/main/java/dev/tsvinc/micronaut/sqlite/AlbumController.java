package dev.tsvinc.micronaut.sqlite;

import dev.tsvinc.micronaut.sqlite.model.AlbumDto;
import dev.tsvinc.micronaut.sqlite.service.AlbumService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Produces;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AlbumController {
    private final AlbumService albumService;

    @Get("/album/first")
    @Produces(value = MediaType.APPLICATION_JSON)
    public HttpResponse<AlbumDto> getAnyFirstAlbum() {
        return albumService.getAnyFirstAlbum().map(HttpResponse::ok).orElse(HttpResponse.notFound());
    }



    @Get("/album/{name}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public HttpResponse<List<AlbumDto>> getAlbumByName(@PathVariable String name) {
        return albumService.getAlbumByName(name).map(HttpResponse::ok).orElse(HttpResponse.notFound());
    }
}
