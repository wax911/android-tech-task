package io.wax911.challenge.feature.detail.component.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.wax911.challenge.core.component.content.AbstractContent
import io.wax911.challenge.domain.credit.enity.Credit
import io.wax911.challenge.feature.detail.R
import io.wax911.challenge.feature.detail.component.viewmodel.DetailViewModel
import io.wax911.challenge.feature.detail.databinding.DetailContentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailContent : AbstractContent<DetailContentBinding>(R.layout.detail_content) {

    private val viewModel by viewModel<DetailViewModel>()

    private fun populateInfoCards(detail: Credit.Detail) {
        // Ideally I would've moved all of this into a the widget with the model
        requireBinding().creditUsed.setUpUsing(
            R.drawable.ic_account_balance_wallet,
            "Credits used",
            "Dashboard Status: ${detail.dashboardStatus}",
            detail.info.percentageCreditUsed
        )

        requireBinding().todoCompleted.setUpUsing(
            R.drawable.ic_edit_note,
            "Todos Completed",
            "Persona Type: ${detail.personaType}",
            detail.summary.percentageTodoCompleted
        )
    }

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
            populateInfoCards(it)
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
            binding = DetailContentBinding.bind(it)
        }
    }
}