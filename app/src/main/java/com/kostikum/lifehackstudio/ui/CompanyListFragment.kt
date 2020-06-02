package com.kostikum.lifehackstudio.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.kostikum.lifehackstudio.adapters.CompanyAdapter
import com.kostikum.lifehackstudio.databinding.FragmentCompanyListBinding
import com.kostikum.lifehackstudio.viewmodels.CompaniesListViewModel
import com.kostikum.lifehackstudio.viewmodels.CompaniesListViewModelFactory
import kotlinx.android.synthetic.main.fragment_company_list.view.*

class CompanyListFragment : Fragment() {
    private val viewModel: CompaniesListViewModel by viewModels {
        CompaniesListViewModelFactory(requireActivity())
    }

    private var binding: FragmentCompanyListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentCompanyListBinding.inflate(inflater, container, false).let {
            binding = it
            it.root
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val companyAdapter = CompanyAdapter()
        binding?.apply {
            root.company_list.adapter = companyAdapter
            lifecycleOwner = viewLifecycleOwner
        }
        viewModel.companiesList.observe(viewLifecycleOwner, Observer { companies ->
            companies?.apply { companyAdapter.companies = companies }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer { isError ->
            if (isError) onNetworkError()
        })
    }

    private fun onNetworkError() {
        viewModel.isNetworkErrorShown.value?.let { isShown ->
            if (!isShown) {
                Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
                viewModel.onNetworkErrorShown()
            }
        }
    }
}
