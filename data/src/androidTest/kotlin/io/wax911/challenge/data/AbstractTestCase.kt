package io.wax911.challenge.data

import androidx.test.platform.app.InstrumentationRegistry
import io.mockk.mockk
import io.wax911.challenge.data.android.database.Store
import io.wax911.challenge.data.core.koin.dataModules
import io.wax911.challenge.data.settings.IClientSettings
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

abstract class AbstractTestCase : KoinTest {

    protected val settings = mockk<IClientSettings>()

    protected fun onStartUp() {
        startKoin {
            androidContext(
                InstrumentationRegistry.getInstrumentation().context
            )
            modules(dataModules)
            loadKoinModules(
                module {
                    single {
                        Store.createInMemory(
                            context = androidContext()
                        )
                    }
                }
            )
        }
    }

    protected fun onStop() {
        stopKoin()
    }
}