package dev.tsvinc.micronaut.sqlite.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "album")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "album_name", nullable = false)
    private String albumName;

    @Column(name = "album_artist_name", nullable = false)
    private String artistName;

    @Column(name = "album_genre", nullable = false)
    private String genre;

    @Column(name = "album_release_year", nullable = false)
    private Integer year;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(id, album.id)
                && Objects.equals(albumName, album.albumName)
                && Objects.equals(artistName, album.artistName)
                && Objects.equals(genre, album.genre)
                && Objects.equals(year, album.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, albumName, artistName, genre, year);
    }
}
