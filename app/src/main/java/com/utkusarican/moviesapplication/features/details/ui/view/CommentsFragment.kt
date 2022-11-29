package com.utkusarican.moviesapplication.features.details.ui.view

import com.utkusarican.moviesapplication.R
import com.utkusarican.moviesapplication.core.ui.BaseFragment
import com.utkusarican.moviesapplication.databinding.FragmentCommentsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsFragment : BaseFragment<FragmentCommentsBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_comments

}