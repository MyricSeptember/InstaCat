package com.example.cattestproject.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.cattestproject.util.lifecycleAwareHandler
import com.example.instacat.databinding.FragmentDetailBinding
import com.example.instacat.util.ExoUtil

class DetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding
    private lateinit var exoUtil: ExoUtil

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = requireNotNull(activity).application
        binding.lifecycleOwner = this

        val marsProperty = DetailFragmentArgs.fromBundle(arguments!!).selectedCat
        val viewModelFactory = DetailViewModelFactory(marsProperty, application)
        binding.viewModel = ViewModelProviders.of(
            this, viewModelFactory
        ).get(DetailViewModel::class.java)

        exoUtil = ExoUtil(activity!!)

        binding.soundButton.setOnClickListener {
            lifecycleAwareHandler(this, exoUtil)
        }
    }
}