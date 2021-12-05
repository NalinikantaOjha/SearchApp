package com.masai.searchapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.arlib.floatingsearchview.FloatingSearchView
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import com.masai.searchapp.R
import com.masai.searchapp.models.remote.Address
import com.masai.searchapp.models.remote.ResponseDTO
import com.masai.searchapp.models.remote.Suggestions
import com.masai.searchapp.models.remote.api.ApiService
import com.masai.searchapp.models.remote.api.Network
import com.masai.searchapp.repository.LocationRepository
import com.masai.searchapp.ui.adapter.ResultAdapter
import com.masai.searchapp.viewmodel.LocationViewModel
import com.masai.searchapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel2: LocationViewModel
    lateinit var repository: LocationRepository
    private val addressList = ArrayList<Address>()
    lateinit var addressAdapter: ResultAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userApi = Network.getInstance().create(ApiService::class.java)
        repository = LocationRepository(userApi)
        val wishlistFactory = ViewModelFactory(repository)
        viewModel2 = ViewModelProviders.of(this, wishlistFactory).get(LocationViewModel::class.java)


        floating_search_view.setOnQueryChangeListener(FloatingSearchView.OnQueryChangeListener {
                oldQuery, newQuery ->
            viewModel2.getLocationData(oldQuery,"")
            viewModel2.user.observe(this, Observer {
                Log.d("getdata","response")

                floating_search_view.swapSuggestions(getSuggestions(it))
                addressList.clear()
                addressList.addAll(it.data.addressList)

               setRecycler()
            })

        })
    }

    private fun getSuggestions(it: ResponseDTO?): MutableList<out SearchSuggestion>? {
        val suggestionsList = ArrayList<Suggestions>()
        val list = it?.data?.addressList

        list?.forEach {
            val suggestion = Suggestions(it.addressString)
            suggestionsList.add(suggestion)
        }
        return suggestionsList
    }
    private fun setRecycler() {
        addressAdapter = ResultAdapter(this, addressList)
        recyclerAddress.adapter = addressAdapter


        recyclerAddress.layoutManager = LinearLayoutManager(this)
    }
}