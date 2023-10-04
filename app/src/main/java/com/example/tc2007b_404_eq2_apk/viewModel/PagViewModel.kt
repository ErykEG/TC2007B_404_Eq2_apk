package com.example.tc2007b_404_eq2_apk.viewModel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tc2007b_404_eq2_apk.model.OrgRegister
import com.example.tc2007b_404_eq2_apk.model.OrgRegisterResponse
import com.example.tc2007b_404_eq2_apk.model.OrgRespList
import com.example.tc2007b_404_eq2_apk.model.Page
import com.example.tc2007b_404_eq2_apk.model.PageList
import com.example.tc2007b_404_eq2_apk.model.Stringid
import com.example.tc2007b_404_eq2_apk.model.UserLogin
import com.example.tc2007b_404_eq2_apk.model.UserLoginResponse
import com.example.tc2007b_404_eq2_apk.model.UserProtectedResponse
import com.example.tc2007b_404_eq2_apk.model.UserRegister
import com.example.tc2007b_404_eq2_apk.model.UserRegistrationResponse
import com.example.tc2007b_404_eq2_apk.service.OrgService
import com.example.tc2007b_404_eq2_apk.service.PagService
import com.example.tc2007b_404_eq2_apk.service.UserService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class PagViewModel(private val pageService: PagService) : ViewModel() {


    private val _pageResult = MutableStateFlow<PageList?>(null)
    val pageResult: StateFlow<PageList?> = _pageResult


    fun getP(orgI: Stringid) {

        viewModelScope.launch {

            var response: PageList? = null

            try {
                Log.d("API","Recibe: $orgI")
                response =   pageService.loadP(orgI)
                Log.d("ap","LLega: $response")
                _pageResult.value = response

            } catch (e: Exception) {
                Log.d("RESPONSE", e.localizedMessage)
            }
        }

    }


}
