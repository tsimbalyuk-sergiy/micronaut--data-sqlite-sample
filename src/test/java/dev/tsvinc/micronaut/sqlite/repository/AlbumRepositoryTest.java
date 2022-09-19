package dev.tsvinc.micronaut.sqlite.repository;

import com.github.javafaker.Faker;
import dev.tsvinc.micronaut.sqlite.domain.Album;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AlbumRepositoryTest {
    private static final Faker faker = new Faker(Locale.ENGLISH);

    @Inject
    AlbumRepository albumRepository;

    @Order(1)
    @Test
    @DisplayName("Should return null when there is no album")
    void findFirstWhenThereIsNoAlbumThenReturnNull() {
        assertNull(albumRepository.findFirst().orElse(null));
    }

    @Order(2)
    @Test
    @DisplayName("Should return a first album when there is at least one album")
    void findFirstWhenThereIsAtLeastOneAlbumThenReturnAnAlbum() {
        Album album = new Album();
        album.setAlbumName(faker.book().title());
        album.setYear(ThreadLocalRandom.current().nextInt(1900, LocalDate.now().getYear()));
        album.setGenre(faker.music().genre());
        album.setArtistName(faker.name().fullName());
        album = albumRepository.save(album);

        Optional<Album> actual = albumRepository.findFirst();

        assertTrue(actual.isPresent());
        assertEquals(album, actual.get());
    }

    @Order(3)
    @Test
    @DisplayName("Should return only a first album when there is more than one album")
    void findFirstWhenThereIsTwoOrMoreAlbumsThenReturnAnAlbum() {
        Album album = new Album();
        album.setAlbumName(faker.book().title());
        album.setYear(ThreadLocalRandom.current().nextInt(1900, LocalDate.now().getYear()));
        album.setGenre(faker.music().genre());
        album.setArtistName(faker.name().fullName());
        album = albumRepository.save(album);

        Album albumTwo = new Album();
        albumTwo.setAlbumName(faker.book().title());
        albumTwo.setYear(ThreadLocalRandom.current().nextInt(1900, LocalDate.now().getYear()));
        albumTwo.setGenre(faker.music().genre());
        albumTwo.setArtistName(faker.name().fullName());
        albumTwo = albumRepository.save(albumTwo);

        Optional<Album> actual = albumRepository.findFirst();

        assertTrue(actual.isPresent());
        assertEquals(album, actual.get());
        assertNotEquals(albumTwo, actual.get());
    }

    @AfterEach
    void tearDown() {
        albumRepository.deleteAll();
    }
}