package io.wax911.challenge.domain.detail.repository

import io.wax911.challenge.domain.credit.enity.Credit

interface IDetailRepository {
    suspend fun getDetailScore(): Credit.Detail
}