package com.github.chuross.qiiip.usecase

import io.reactivex.Single
import io.reactivex.disposables.Disposable

typealias UseCaseTransformer<T> = (Single<T>) -> Single<T>

interface RxUseCase<T> : UseCase<T>, Disposable {

    fun compose(transformer: UseCaseTransformer<T>) : RxUseCase<T>
}