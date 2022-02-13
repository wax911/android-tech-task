package io.wax911.challenge.feature.landing.component.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.wax911.challenge.feature.landing.R
import io.wax911.challenge.core.component.content.AbstractContent
import io.wax911.challenge.feature.landing.component.viewmodel.LandingViewModel
import io.wax911.challenge.feature.landing.databinding.LandingContentBinding
import io.wax911.challenge.navigation.DetailRouter
import io.wax911.challenge.navigation.extensions.forIntent
import org.koin.androidx.viewmodel.ext.android.viewModel

class LandingContent : AbstractContent<LandingContentBinding>(R.layout.landing_content) {

    private val viewModel by viewModel<LandingViewModel>()

    /**
     * Stub to trigger the loading of data, by default this is only called
     *
     * This is called when the fragment reaches it's [onResume] state
     */
    override fun onFetchDataInitialize() {
        viewModel()
    }

    /**
     * Invoke view model observer to watch for changes, this will be called
     * called in [onViewCreated]
     */
    override fun setUpViewModelObserver() {
        viewModel.model.observe(viewLifecycleOwner) {
            requireBinding().donutProgressIndicator.setUpWith(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            requireBinding().stateLayout.onLoadStateChanged(it)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            requireBinding().stateLayout.onErrorStateChanged(it) {
                onFetchDataInitialize()
            }
        }
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null. This will be called between
     * [.onCreate] and [.onViewCreated].
     *
     * A default View can be returned by calling [.Fragment] in your
     * constructor. Otherwise, this method returns null.
     *
     *
     * It is recommended to **only** inflate the layout in this method and move
     * logic that operates on the returned View to [.onViewCreated].
     *
     *
     * If you return a View from here, you will later be called in
     * [.onDestroyView] when the view is being released.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return Return the View for the fragment's UI, or null.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)?.also {
            binding = LandingContentBinding.bind(it)
        }
    }

    /**
     * Called immediately after [.onCreateView]
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     * @param view The View returned by [.onCreateView].
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireBinding().donutProgressIndicator.setOnClickListener {
            val intent = DetailRouter.forIntent(it.context)
            startActivity(intent)
        }
    }
}