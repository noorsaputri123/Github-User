package com.rie.githubuser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rie.githubuser.config.ApiConfig
import com.rie.githubuser.config.Event
import com.rie.githubuser.response.ItemsSearch
import com.rie.githubuser.response.ResponseSearchDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailViewModel : ViewModel() {

    private val _userDetail = MutableLiveData<ResponseSearchDetail>()
    val userDetail: LiveData<ResponseSearchDetail> = _userDetail

    private val _followers = MutableLiveData<List<ItemsSearch>>()
    val followers: LiveData<List<ItemsSearch>> = _followers

    private val _following = MutableLiveData<List<ItemsSearch>>()
    val following: LiveData<List<ItemsSearch>> = _following

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _toastText = MutableLiveData<Event<String>>()
    val toastText: LiveData<Event<String>> = _toastText

    fun getFollowers(username: String?) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowers(username)
        client.enqueue(object : Callback<List<ItemsSearch>> {
            override fun onResponse(
                call: Call<List<ItemsSearch>>,
                response: Response<List<ItemsSearch>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _followers.value = response.body()
                    _toastText.value = Event("Success")
                } else {
                    _toastText.value = Event("Tidak ada data yang ditampilkan!")
                }
            }
            override fun onFailure(call: Call<List<ItemsSearch>>, t: Throwable) {
                _isLoading.value = false
                _toastText.value = Event("onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getFollowing(username: String?) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : Callback<List<ItemsSearch>> {
            override fun onResponse(
                call: Call<List<ItemsSearch>>,
                response: Response<List<ItemsSearch>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _following.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: gagal")
                }
            }
            override fun onFailure(call: Call<List<ItemsSearch>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getUser(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserDetail(username)
        client.enqueue(object : Callback<ResponseSearchDetail> {
            override fun onResponse(
                call: Call<ResponseSearchDetail>,
                response: Response<ResponseSearchDetail>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _userDetail.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: gagal")
                }
            }
            override fun onFailure(call: Call<ResponseSearchDetail>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    companion object{
        private const val TAG = "DetailViewModel"
    }
}