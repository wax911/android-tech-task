package io.wax911.challenge.domain.landing.repository

import io.wax911.challenge.domain.credit.enity.Credit

interface ILandingRepository {
    suspend fun getLandingScore(): Credit.Landing
}