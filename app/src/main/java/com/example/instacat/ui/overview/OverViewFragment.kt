package com.example.cattestproject.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.android.devbyteviewer.domain.Cat
import com.example.instacat.databinding.FragmentOverviewBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class OverViewFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: OverViewViewmodel

    lateinit var binding: FragmentOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOverviewBinding.inflate(inflater)
        viewModel =
            ViewModelProviders.of(this.activity!!, viewModelFactory)
                .get(OverViewViewmodel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val adapter =
            CatGridAdapter(CatGridAdapter.OnClickListener {
                viewModel.displayCatDetails(it)
            })

        binding.photosGrid.adapter = adapter
        viewModel.listResult.observe(viewLifecycleOwner, Observer<List<Cat>> { cats ->
            cats?.apply {
                adapter.submitList(cats)
                viewModel.setStatus(CatsApiStatus.DONE)
            }
        })

        viewModel.navigateToSelectedCat.observe(this, Observer {
            if (null != it) {
                this.findNavController()
                    .navigate(OverViewFragmentDirections.actionOverViewFragmentToDetailFragment(it))
                viewModel.displayCatDetailsComplete()
            }
        })
    }
}