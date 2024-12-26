package com.simformsolutions.oneloop.domain.repository

import com.simformsolutions.oneloop.common.dispatcher.IoDispatcher
import com.simformsolutions.oneloop.domain.remote.apiresult.ApiResult
import com.simformsolutions.oneloop.domain.remote.response.UserResponse
import com.simformsolutions.oneloop.domain.remote.service.UserService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val userService: UserService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : UsersRepository {
    override suspend fun loadUsers(page: Int): ApiResult<UserResponse> =
        withContext(ioDispatcher) {
            userService.loadUsers(page)
        }
}