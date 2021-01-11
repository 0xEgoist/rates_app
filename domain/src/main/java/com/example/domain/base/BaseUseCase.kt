package com.example.domain.base

abstract class BaseUseCase<Arg, Res> {
    operator fun invoke(arg: Arg): Res {
        return create(arg)
    }

    protected abstract fun create(arg: Arg): Res
}