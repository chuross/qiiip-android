package com.github.chuross.qiiip.usecase

interface UseCase<out T> {

    fun exec(success: ((T) -> Unit), fail: ((Throwable) -> Unit)? = null)
}
