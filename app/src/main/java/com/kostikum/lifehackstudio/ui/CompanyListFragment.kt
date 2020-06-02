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
import com.kostikum.lifehackstudio.entities.Company
import com.kostikum.lifehackstudio.viewmodels.CompaniesListViewModel
import com.kostikum.lifehackstudio.viewmodels.CompaniesListViewModelFactory
import kotlinx.android.synthetic.main.fragment_company_list.view.*

class CompanyListFragment : Fragment() {
    private val viewModel: CompaniesListViewModel by viewModels {
        val activity = requireNotNull(this.activity) { "No activity yet!" }
        CompaniesListViewModelFactory(activity.application)
    }

    private var companyAdapter: CompanyAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.companiesList.observe(viewLifecycleOwner, Observer { companies ->
            companies?.apply { companyAdapter?.companies = companies }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentCompanyListBinding.inflate(inflater, container, false)
        context ?: return binding.root
        binding.lifecycleOwner = viewLifecycleOwner

        companyAdapter = CompanyAdapter()
        binding.root.company_list.adapter = companyAdapter

        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })

        return binding.root
    }

    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}
