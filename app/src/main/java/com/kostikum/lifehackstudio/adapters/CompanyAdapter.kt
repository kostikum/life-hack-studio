package com.kostikum.lifehackstudio.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kostikum.lifehackstudio.databinding.CompanyItemBinding
import com.kostikum.lifehackstudio.entities.Company
import com.kostikum.lifehackstudio.ui.CompanyListFragmentDirections

class CompanyAdapter : RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder>() {
    var companies: List<Company> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CompanyViewHolder(CompanyItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false))

    override fun getItemCount() = companies.size

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bind(companies[position])
    }

    class CompanyViewHolder(private val binding: CompanyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.company?.let { company ->
                    navigateToClickedCompany(company, it)
                }
            }
        }

        private fun navigateToClickedCompany(
            company: Company,
            view: View
        ) {
            val direction = CompanyListFragmentDirections
                .actionCompanyListFragmentToCompanyDetailsFragment(company.id)
            view.findNavController().navigate(direction)
        }

        fun bind(item: Company) {
            binding.apply {
                company = item
                executePendingBindings()
            }
        }
    }
}


