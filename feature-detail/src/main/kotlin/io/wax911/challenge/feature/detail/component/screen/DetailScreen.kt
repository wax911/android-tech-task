package io.wax911.challenge.feature.detail.component.screen

import android.os.Bundle
import io.wax911.challenge.core.component.screen.AbstractScreen
import io.wax911.challenge.core.ui.commit
import io.wax911.challenge.core.ui.model.FragmentItem
import io.wax911.challenge.feature.detail.databinding.DetailScreenBinding
import io.wax911.challenge.navigation.DetailRouter

class DetailScreen : AbstractScreen<DetailScreenBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailScreenBinding.inflate(layoutInflater)
        setContentView(requireBinding().root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setSupportActionBar(requireBinding().bottomAppBar)
        onUpdateUserInterface()
    }

    private fun onUpdateUserInterface() {
        FragmentItem(fragment = DetailRouter.forFragment())
            .commit(requireBinding().contentMain, this)
    }
}