package br.edu.projetofinalkotlin.viewmodel.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import br.edu.projetofinalkotlin.model.repository.LoginRepository
import br.edu.projetofinalkotlin.util.DataResult
import kotlinx.coroutines.Dispatchers

class LoginViewModel(private val repository: LoginRepository = LoginRepository.instance) :
    ViewModel() {

    fun loginUserViewModel(email: String, password: String) = liveData(Dispatchers.IO) {
        emit(DataResult.Loading)
        val result = repository.loginUser(email, password)
        emit(result)
    }
}