package com.teamdagger.daggerhiltlearn.repository

import com.teamdagger.daggerhiltlearn.model.Blog
import com.teamdagger.daggerhiltlearn.retrofit.BlogRetrofit
import com.teamdagger.daggerhiltlearn.retrofit.NetworkMapper
import com.teamdagger.daggerhiltlearn.room.BlogDao
import com.teamdagger.daggerhiltlearn.room.CacheMapper
import com.teamdagger.daggerhiltlearn.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class   MainRepository
constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
){
    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow{
        emit(DataState.Loading)
        delay(1000)
        try{
            val networkBlogs =blogRetrofit.get()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for(blog in blogs){
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlog = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlog)))
        }catch ( e:Exception){
            emit(DataState.Error(e))
        }
    }
}
