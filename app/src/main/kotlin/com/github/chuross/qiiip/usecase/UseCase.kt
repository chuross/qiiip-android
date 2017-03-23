package com.github.chuross.qiiip.usecase

import io.reactivex.Single

interface UseCase<T> {

    fun exec() : Single<T>
}