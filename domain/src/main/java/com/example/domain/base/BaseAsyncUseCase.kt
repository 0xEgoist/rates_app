package com.example.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseAsyncUseCase<Arg, Res> {
    suspend operator fun invoke(
        arg: Arg,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Res {
        return withContext(dispatcher) { create(arg) }
    }

    protected abstract suspend fun create(arg: Arg): Res
}