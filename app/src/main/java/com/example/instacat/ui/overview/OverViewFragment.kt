package com.example.cattestproject.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.android.devbyteviewer.domain.Cat
import com.example.instacat.R
import com.example.instacat.databinding.FragmentOverviewBinding
import com.example.instacat.util.NetworkUtils
import com.google.android.material.snackbar.Snackbar

class OverViewFragment : Fragment() {

    lateinit var binding: FragmentOverviewBinding
    private lateinit var networkUtils: NetworkUtils

    private val viewModel: OverViewViewmodel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, OverViewViewmodel.Factory(activity.application, networkUtils))
            .get(OverViewViewmodel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOverviewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        networkUtils = NetworkUtils(activity!!)

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

        viewModel.hasNetwork.observe(viewLifecycleOwner, Observer<Boolean> { hasNetwork ->
            hasNetwork?.let {
                if (!it) {
                    Snackbar.make(
                        binding.root,
                        getString(R.string.no_network_connection),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
            viewModel.hasNetwork.removeObservers(viewLifecycleOwner)
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