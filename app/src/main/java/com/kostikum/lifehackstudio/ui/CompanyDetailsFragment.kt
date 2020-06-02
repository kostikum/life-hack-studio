package com.kostikum.lifehackstudio.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.kostikum.lifehackstudio.databinding.FragmentCompanyDetailsBinding
import com.kostikum.lifehackstudio.entities.Company
import com.kostikum.lifehackstudio.entities.CompanyDetails
import com.kostikum.lifehackstudio.viewmodels.CompanyDetailsViewModel
import com.kostikum.lifehackstudio.viewmodels.CompanyDetailsViewModelFactory

class CompanyDetailsFragment : Fragment() {
    private val args: CompanyDetailsFragmentArgs by navArgs()

    private val detailsViewModel: CompanyDetailsViewModel by viewModels {
        CompanyDetailsViewModelFactory(requireActivity(), args.companyId)
    }

    private var binding: FragmentCompanyDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentCompanyDetailsBinding.inflate(inflater, container, false).let {
            binding = it
            it.root
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = detailsViewModel
            setListener { view ->
                binding?.viewModel?.companyDetails?.value?.let {
                    navigateToLocationFragment(it, view)
                }
            }
        }
        detailsViewModel.eventNetworkError.observe(viewLifecycleOwner, Observer { isError ->
            if (isError) onNetworkError()
        })
    }

    private fun navigateToLocationFragment(
        companyDetails: CompanyDetails,
        view: View
    ) {
        val direction = CompanyDetailsFragmentDirections
            .actionCompanyDetailsFragmentToLocationFragment(companyDetails.lat, companyDetails.lon)
        view.findNavController().navigate(direction)
    }

    private fun onNetworkError() {
        detailsViewModel.isNetworkErrorShown.value?.let { isShown ->
            if (!isShown) {
                Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
                detailsViewModel.onNetworkErrorShown()
            }
        }
    }
}
