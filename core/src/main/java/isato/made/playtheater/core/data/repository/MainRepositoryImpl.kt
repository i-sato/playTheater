package isato.made.playtheater.core.data.repository

import isato.made.playtheater.core.data.NetworkBoundResource
import isato.made.playtheater.core.data.Resource
import isato.made.playtheater.core.data.source.local.LocalDataSource
import isato.made.playtheater.core.data.source.remote.RemoteDataSource
import isato.made.playtheater.core.data.source.remote.network.ApiResponse
import isato.made.playtheater.core.data.source.remote.response.MovieResponse
import isato.made.playtheater.core.domain.model.MovieDomain
import isato.made.playtheater.core.domain.repository.MainRepository
import isato.made.playtheater.core.util.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : MainRepository {

    override fun getAllMovies(): Flow<Resource<List<MovieDomain>>> =
        object : NetworkBoundResource<List<MovieDomain>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<MovieDomain>> =
                localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }

            override fun shouldFetch(data: List<MovieDomain>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movies = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(movies)
            }
        }.asFlow()
}