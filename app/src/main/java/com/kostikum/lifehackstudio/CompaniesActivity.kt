package com.kostikum.lifehackstudio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.kostikum.lifehackstudio.network.NetProvider
import com.kostikum.lifehackstudio.network.NetworkCompany
import com.kostikum.lifehackstudio.network.NetworkCompanyDetails
import kotlinx.coroutines.*

class CompaniesActivity : AppCompatActivity() {

    var companies: List<NetworkCompanyDetails>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelJob = SupervisorJob()
        val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
        viewModelScope.launch {
            refresh()
        }

        val helloTextView = findViewById<TextView>(R.id.hello)

        helloTextView.setOnClickListener {
            if (companies != null) {
                helloTextView.text = companies.toString()
            }
        }
    }

    private suspend fun refresh() {
        withContext(Dispatchers.IO) {
            companies = NetProvider.service.getCompanyAsync(3).await()

        }
    }
}
