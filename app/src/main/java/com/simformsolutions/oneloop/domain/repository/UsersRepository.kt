package com.simformsolutions.oneloop.domain.repository

import com.simformsolutions.oneloop.domain.remote.apiresult.ApiResult
import com.simformsolutions.oneloop.domain.remote.response.UserResponse
import retrofit2.http.Query

interface UsersRepository {
    suspend fun loadUsers(@Query("page") page: Int): ApiResult<UserResponse>
}
