package com.utkusarican.moviesapplication.features.details.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utkusarican.moviesapplication.R
import com.utkusarican.moviesapplication.core.ui.BaseFragment
import com.utkusarican.moviesapplication.databinding.FragmentMoreLikeThisBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoreLikeThisFragment : BaseFragment<FragmentMoreLikeThisBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_more_like_this

}