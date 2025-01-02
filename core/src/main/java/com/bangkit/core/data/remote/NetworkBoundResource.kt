package com.bangkit.core.data.remote


import com.bangkit.core.data.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var results : Flow<Result<ResultType>> = flow {
        emit(Result.Loading())
        val dbSource = loadFromDB().first()

        if (shouldFetch(dbSource)){
            emit(Result.Loading())
            when (val apiSource = createCall().first()){
                is ApiResponse.Success -> {
                    saveCallResult(apiSource.data)
                    emitAll(loadFromDB().map { Result.Success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Result.Error("Error Gagal Menambah ke Database"))
                }
            }
        } else {
            emitAll(loadFromDB().map { Result.Success(it) })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Result<ResultType>> = results


}