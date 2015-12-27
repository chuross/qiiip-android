package com.github.chuross.qiiip.domain.user

import com.github.chuross.qiiip.domain.user.converter.UserConverter
import com.github.chuross.qiiip.infrastructure.qiita.QiitaV2Api
import rx.Observable
import javax.inject.Inject

class UserRepository {

    @Inject
    lateinit var api: QiitaV2Api

    fun authenticatedUser(): Observable<User> {
        return api.getAuthenticatedUser().map { UserConverter.convertToModel(it) }
    }

}