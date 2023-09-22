package com.example.tc2007b_404_eq2_apk.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tc2007b_404_eq2_apk.model.OrgRegister
import com.example.tc2007b_404_eq2_apk.model.OrgRegisterResponse
import com.example.tc2007b_404_eq2_apk.model.UserLogin
import com.example.tc2007b_404_eq2_apk.model.UserLoginResponse
import com.example.tc2007b_404_eq2_apk.model.UserProtectedResponse
import com.example.tc2007b_404_eq2_apk.model.UserRegister
import com.example.tc2007b_404_eq2_apk.model.UserRegistrationResponse
import com.example.tc2007b_404_eq2_apk.service.OrgService
import com.example.tc2007b_404_eq2_apk.service.UserService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class OrgViewModel(private val orgService: OrgService) : ViewModel() {


    private val _orgRegisterResult = MutableStateFlow<OrgRegisterResponse?>(null)
    val orgRegisterResult: StateFlow<OrgRegisterResponse?>
        get() = _orgRegisterResult


    fun addOrganization(token: String, org: OrgRegister) {

        viewModelScope.launch {

            var response: OrgRegisterResponse? = null

            try {

                response = orgService.addOrg(token, org)
                _orgRegisterResult.value = response
                response.message?.let { Log.d("RESPONSE", it) }

            } catch (e: Exception) {
                Log.d("RESPONSE", e.localizedMessage)
            }
        }

    }


}
