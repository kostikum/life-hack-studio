package com.kostikum.lifehackstudio.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.kostikum.lifehackstudio.databinding.FragmentCompanyDetailsBinding
import com.kostikum.lifehackstudio.viewmodels.CompanyDetailsViewModel
import com.kostikum.lifehackstudio.viewmodels.CompanyDetailsViewModelFactory

class CompanyDetailsFragment : Fragment() {
    private val args: CompanyDetailsFragmentArgs by navArgs()

    private val detailsViewModel: CompanyDetailsViewModel by viewModels {
        val activity = requireNotNull(this.activity) { "No activity yet!" }
        CompanyDetailsViewModelFactory(activity.application, args.companyId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCompanyDetailsBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = detailsViewModel
            }

        detailsViewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })

        return binding.root
    }

    private fun onNetworkError() {
        if (!detailsViewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            detailsViewModel.onNetworkErrorShown()
        }
    }
}
