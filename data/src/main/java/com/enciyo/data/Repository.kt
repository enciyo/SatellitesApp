package com.enciyo.data

import com.enciyo.domain.Repository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RepositoryImp @Inject constructor(
    private val resourceDataSource: ResourceDataSource,
) : Repository {


}