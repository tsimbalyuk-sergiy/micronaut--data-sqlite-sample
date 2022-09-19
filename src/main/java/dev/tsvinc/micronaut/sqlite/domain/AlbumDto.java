package dev.tsvinc.micronaut.sqlite.domain;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
public final class AlbumDto {
    private final String albumName;
    private final String artistName;
    private final String genre;
    private final Integer year;
}
