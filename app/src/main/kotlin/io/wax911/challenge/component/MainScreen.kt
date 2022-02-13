package io.wax911.challenge.component

import android.os.Bundle
import io.wax911.challenge.core.component.screen.AbstractScreen
import io.wax911.challenge.core.ui.commit
import io.wax911.challenge.core.ui.model.FragmentItem
import io.wax911.challenge.databinding.MainScreenBinding
import io.wax911.challenge.navigation.LandingRouter

class MainScreen : AbstractScreen<MainScreenBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainScreenBinding.inflate(layoutInflater)
        setContentView(requireBinding().root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setSupportActionBar(requireBinding().bottomAppBar)
        onUpdateUserInterface()
    }

    private fun onUpdateUserInterface() {
        FragmentItem(fragment = LandingRouter.forFragment())
            .commit(requireBinding().contentMain, this)
    }
}