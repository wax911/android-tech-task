package io.wax911.challenge.data.detail.repository

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import io.mockk.every
import io.wax911.challenge.data.AbstractTestCase
import io.wax911.challenge.domain.detail.repository.IDetailRepository
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.test.inject
import org.koin.dsl.module

@RunWith(AndroidJUnit4ClassRunner::class)
class DetailRepositoryTest : AbstractTestCase() {

    private val repository by inject<IDetailRepository>()

    @Before
    fun setUp() {
        onStartUp()
    }

    @After
    fun tearDown() {
        onStop()
    }

    @Test
    fun assert_repository_dependency_can_be_constructed() {
        loadKoinModules(
            module {
                factory {
                    every { settings.clientId.value } returns "1"
                    settings
                }
            }
        )
        assertNotNull(repository)
    }
}