package io.wax911.challenge.core.component.screen

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.viewbinding.ViewBinding
import io.wax911.challenge.core.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import org.koin.androidx.scope.activityScope
import org.koin.core.component.KoinScopeComponent
import kotlin.jvm.Throws

/**
 * Abstraction for activity which enforces view binding
 */
abstract class AbstractScreen<B: ViewBinding> : AppCompatActivity(),
    CoroutineScope by MainScope(), KoinScopeComponent {

    override val scope by activityScope()

    protected var binding: B? = null

    /**
     * Provides a non-nullable reference to [binding]
     *
     * @throws IllegalArgumentException When [binding] is not set to an instance
     */
    @Throws(IllegalArgumentException::class)
    protected fun requireBinding(): B =
        requireNotNull(binding) {
            "Binding has not yet been initialized"
        }


    @TargetApi(Build.VERSION_CODES.O)
    private fun applyDecorations(systemUiOptions: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val controller = ViewCompat.getWindowInsetsController(window.decorView)
            val isLightModeTheme = resources.getBoolean(R.bool.is_light_theme_mode)
            controller?.isAppearanceLightNavigationBars = isLightModeTheme
            controller?.isAppearanceLightStatusBars = isLightModeTheme
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = systemUiOptions or
                    View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR or
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        runCatching {
            setupKoinFragmentFactory(scope)
        }.onFailure {
            setupKoinFragmentFactory()
        }

        // Wanted to go for an all white theme at first
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        //    @Suppress("DEPRECATION")
        //    applyDecorations(window.decorView.systemUiVisibility)
        //}

        super.onCreate(savedInstanceState)

    }

    override fun setSupportActionBar(toolbar: Toolbar?) {
        super.setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}