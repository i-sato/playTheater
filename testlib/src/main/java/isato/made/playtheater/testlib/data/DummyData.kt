/*
 * PlayTheater.testlib
 * DummyData.kt
 * Created by Isato on 21/12/2021 14:42:9
 * Source code available on: https://github.com/i-sato/PlayTheater
 * Copyright (c) 2021. All rights reserved.
 */

package isato.made.playtheater.testlib.data

import isato.made.playtheater.core.data.source.local.entity.GenreEntity
import isato.made.playtheater.core.data.source.local.entity.MovieEntity
import isato.made.playtheater.core.data.source.local.entity.MovieWithGenre
import isato.made.playtheater.core.util.DateConverter

object DummyData {

    fun generateMovieEntities(): List<MovieEntity> {
        val entities = mutableListOf<MovieEntity>()
        entities.add(
            MovieEntity(
                movieId = "634649",
                title = "Spider-Man: No Way Home",
                overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                posterPath = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                backdropPath = "/VlHt27nCqOuTnuX6bku8QZapzO.jpg",
                releaseDate = DateConverter.stringToTimestamp("2021-12-15")
            )
        )
        entities.add(
            MovieEntity(
                movieId = "580489",
                title = "Venom: Let There Be Carnage",
                overview = "After finding a host body in investigative reporter Eddie Brock, the alien symbiote must face a new enemy, Carnage, the alter ego of serial killer Cletus Kasady.",
                posterPath = "/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg",
                backdropPath = "/eENEf62tMXbhyVvdcXlnQz2wcuT.jpg",
                releaseDate = DateConverter.stringToTimestamp("2021-09-30")
            )
        )
        entities.add(
            MovieEntity(
                movieId = "512195",
                title = "Red Notice",
                overview = "An Interpol-issued Red Notice is a global alert to hunt and capture the world's most wanted. But when a daring heist brings together the FBI's top profiler and two rival criminals, there's no telling what will happen.",
                posterPath = "/wdE6ewaKZHr62bLqCn7A2DiGShm.jpg",
                backdropPath = "/7ajHGIAYNMiIzejy1LJWdPrcAx8.jpg",
                releaseDate = DateConverter.stringToTimestamp("2021-11-04")
            )
        )
        return entities
    }

    fun generateMovieEntityDetail(withGenres: Boolean = true): MovieWithGenre {
        val entity = MovieEntity(
            movieId = "634649",
            title = "Spider-Man: No Way Home",
            overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
            posterPath = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
            backdropPath = "/VlHt27nCqOuTnuX6bku8QZapzO.jpg",
            releaseDate = DateConverter.stringToTimestamp("2021-12-15")
        )
        val genres = if (withGenres) {
            listOf(
                GenreEntity("28", "Action"),
                GenreEntity("12", "Adventure"),
                GenreEntity("878", "Science Fiction")
            )
        } else {
            emptyList()
        }
        return MovieWithGenre(entity, genres)
    }

}