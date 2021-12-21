/*
 * PlayTheater.core
 * DummyData.kt
 * Created by Isato on 20/12/2021 8:44:24
 * Source code available on: https://github.com/i-sato/PlayTheater
 * Copyright (c) 2021. All rights reserved.
 */

@file:Suppress("SpellCheckingInspection")

package isato.made.playtheater.core.data

import isato.made.playtheater.core.data.source.local.entity.GenreEntity
import isato.made.playtheater.core.data.source.local.entity.MovieEntity
import isato.made.playtheater.core.data.source.local.entity.MovieWithGenre
import isato.made.playtheater.core.data.source.remote.response.MovieDetailResponse
import isato.made.playtheater.core.data.source.remote.response.MovieGenreResponse
import isato.made.playtheater.core.data.source.remote.response.MovieResponse
import isato.made.playtheater.core.domain.model.MovieDetailDomain
import isato.made.playtheater.core.domain.model.MovieGenreDomain
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

    fun generateMovieResponses(): List<MovieResponse> {
        val responses = mutableListOf<MovieResponse>()
        responses.add(
            MovieResponse(
                id = "634649",
                title = "Spider-Man: No Way Home",
                overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                posterPath = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                backdropPath = "/VlHt27nCqOuTnuX6bku8QZapzO.jpg",
                releaseDate = "2021-12-15"
            )
        )
        responses.add(
            MovieResponse(
                id = "580489",
                title = "Venom: Let There Be Carnage",
                overview = "After finding a host body in investigative reporter Eddie Brock, the alien symbiote must face a new enemy, Carnage, the alter ego of serial killer Cletus Kasady.",
                posterPath = "/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg",
                backdropPath = "/eENEf62tMXbhyVvdcXlnQz2wcuT.jpg",
                releaseDate = "2021-09-30"
            )
        )
        responses.add(
            MovieResponse(
                id = "512195",
                title = "Red Notice",
                overview = "An Interpol-issued Red Notice is a global alert to hunt and capture the world's most wanted. But when a daring heist brings together the FBI's top profiler and two rival criminals, there's no telling what will happen.",
                posterPath = "/wdE6ewaKZHr62bLqCn7A2DiGShm.jpg",
                backdropPath = "/7ajHGIAYNMiIzejy1LJWdPrcAx8.jpg",
                releaseDate = "2021-11-04"
            )
        )
        return responses
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

    fun generateMovieDetailResponse(): MovieDetailResponse {
        val movieGenreResponse = listOf(
            MovieGenreResponse(id = "28", name = "Action"),
            MovieGenreResponse(id = "12", name = "Adventure"),
            MovieGenreResponse(id = "878", name = "Science Fiction")
        )
        return MovieDetailResponse(
            id = "634649",
            title = "Spider-Man: No Way Home",
            overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
            posterPath = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
            backdropPath = "/VlHt27nCqOuTnuX6bku8QZapzO.jpg",
            releaseDate = "2021-12-15",
            tagline = "The Multiverse unleashed.",
            status = "Released",
            genres = movieGenreResponse
        )
    }

    fun generateMovieDetailDomain(isFavorite: Boolean = true): MovieDetailDomain {
        val genresDomain = listOf(
            MovieGenreDomain(genreId = "28", name = "Action"),
            MovieGenreDomain(genreId = "12", name = "Adventure"),
            MovieGenreDomain(genreId = "878", name = "Science Fiction")
        )
        return MovieDetailDomain(
            movieId = "634649",
            title = "Spider-Man: No Way Home",
            overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
            posterPath = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
            backdropPath = "/VlHt27nCqOuTnuX6bku8QZapzO.jpg",
            releaseDate = "15 Dec 2021",
            tagline = "The Multiverse unleashed.",
            status = "Released",
            genres = genresDomain,
            isFavorite = isFavorite
        )
    }

}