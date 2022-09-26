package com.box.talana.presentation.features.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.box.talana.R
import com.box.talana.BR
import com.box.talana.core.extension.dataBinding
import com.box.talana.core.extension.lifecycleScopeCreate
import com.box.talana.core.funtional.Failure
import com.box.talana.databinding.FragmentHomeBinding
import com.box.talana.domian.model.Feed
import com.box.talana.presentation.base.BaseFragmentWithViewModel
import com.box.talana.presentation.model.MessageDesign
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment :
    BaseFragmentWithViewModel<FragmentHomeBinding, HomeViewModel>(),
    HomeViewState {

    override val binding: FragmentHomeBinding by dataBinding(FragmentHomeBinding::inflate)

    override val viewModel: HomeViewModel by viewModels()

    override val dataBindingViewModel = BR.homeViewModel

    private lateinit var recycler: RecyclerView

    private lateinit var adapter: FeedAdapter

    private var feedsList: List<Feed> = arrayListOf()



    override fun onViewIsCreated(view: View) {

        HomeReducer.instance(viewState = this)

        observeUiStates()

        recycler = binding.recycler

        recycler.layoutManager = LinearLayoutManager(requireActivity())



    }

    private fun observeUiStates() {
        viewModel.setEvent(HomeEvent.GetFeedClick)

        viewModel.apply {
            lifecycleScopeCreate(activity = requireActivity(), method = {
                state.collect { state ->
                    HomeReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = requireActivity(), method = {
                effect.collect { effect ->
                    HomeReducer.selectEffect(effect)
                }
            })
        }

    }

    override fun messageFailure(failure: Failure) {

        closeLoadingDialog()

        val messageDesign: MessageDesign = getUseCaseFailureFromBase(failure)

        showSnackBar(binding.root, getString(messageDesign.idMessage))
    }

    override fun loading() {
        showLoadingDialog()
    }

    override fun detailActivity() {

    }

    override fun listFeed(data: List<Feed>) {

        closeLoadingDialog()

        feedsList = data

        adapter = FeedAdapter(feeds = feedsList) {
                model -> onItemSelected(model)
        }

        recycler.adapter = adapter
    }


    private fun onItemSelected(model: Feed){


        findNavController().navigate(
            R.id.action_navigation_home_to_detailActivity,
            Bundle().apply {
                putSerializable("feed_put", model)
            }
        )



    }
}