package com.box.talana.presentation.features.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.box.talana.BR
import com.box.talana.R
import com.box.talana.core.extension.*
import com.box.talana.core.funtional.Failure
import com.box.talana.databinding.ActivityDetailBinding
import com.box.talana.domian.model.Contact
import com.box.talana.domian.model.Feed
import com.box.talana.presentation.base.BaseActivityWithViewModel
import com.box.talana.presentation.model.MessageDesign
import com.box.talana.presentation.util.ConfigUI
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailActivity :
    BaseActivityWithViewModel<ActivityDetailBinding, DetailViewModel>(),
    DetailViewState {

    override val binding: ActivityDetailBinding by dataBinding(ActivityDetailBinding::inflate)

    override val viewModel: DetailViewModel by viewModels()

    override val dataBindingViewModel = BR.detailViewModel

    private lateinit var feed: Feed


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DetailReducer.instance(viewState = this)

        binding.topBarInclude.btnBack.setOnClickListener {
            onBackPressed()
        }

        observeUiStates()
    }

    private fun observeUiStates() {

        viewModel!!.let {
            feed = ConfigUI.FEED_PUT putFeed this@DetailActivity

            setDetail(feed)
        }

        viewModel.contactId = feed.authorId

        viewModel.setEvent(DetailEvent.AuthorClick)

        viewModel.apply {
            lifecycleScopeCreate(activity = this@DetailActivity, method = {
                state.collect { state ->
                    DetailReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = this@DetailActivity, method = {
                effect.collect { effect ->
                    DetailReducer.selectEffect(effect)
                }
            })
        }

    }


    private fun setDetail(data: Feed) {
        with(binding) {
            topBarInclude.title = data.title
            titleText.text = data.title
            descriptionText.text = data.description.html()

            val dateTimeFormatter = data.published.dateFormat(ConfigUI.DATE_FORMAT_EIGHTEEN).dateFormat(ConfigUI.DATE_FORMAT_NINETEEN)

            binding.dateText.text= "${getString(R.string.published)}: $dateTimeFormatter"

            Glide
                .with(applicationContext)
                .load(data.image)
                .centerCrop()
                .placeholder(R.drawable.default_image)
                .into(binding.feedCardImage);
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

    override fun contact(data: Contact) {
        with(binding) {
            contactText.text = "${data.firstName} ${data.lastName}"
        }
    }


}