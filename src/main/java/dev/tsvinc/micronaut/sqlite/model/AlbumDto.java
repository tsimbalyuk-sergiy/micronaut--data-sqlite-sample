package dev.tsvinc.micronaut.sqlite.model;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Introspected
public final class AlbumDto {
    private final String albumName;
    private final String artistName;
    private final String genre;
    private final Integer year;
}
