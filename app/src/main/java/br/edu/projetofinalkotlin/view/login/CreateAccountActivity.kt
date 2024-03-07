package br.edu.projetofinalkotlin.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import br.edu.projetofinalkotlin.R
import br.edu.projetofinalkotlin.databinding.ActivityCreateAccountBinding
import br.edu.projetofinalkotlin.util.DataResult
import br.edu.projetofinalkotlin.viewmodel.login.CreateAccountViewModel

class CreateAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountBinding
    private val viewModel: CreateAccountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureBackBtn()
        configureCreateAccountBtn()
    }

    private fun configureBackBtn() {
        binding.backBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun configureCreateAccountBtn() {
        binding.createAccountBtn.setOnClickListener {
            registerNewUser()
        }
    }

    private fun registerNewUser() {
        val email = binding.createAccountEmailTiet.text.toString()
        val password = binding.createAccountPasswordTiet.text.toString()
        val passwordConfirmation = binding.createAccountPasswordConfirmationTiet.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty() && passwordConfirmation.isNotEmpty()) {
            if (password == passwordConfirmation) {
                viewModel.createNewUserViewModel(email, password).observe(this) { result ->
                    when (result) {
                        is DataResult.Loading -> {
                            showLoading()
                        }

                        is DataResult.Success -> {
                            hideLoading()
                            showMessage(getString(R.string.ca_create_account_toast_success))
                            startActivity(Intent(this, LoginActivity::class.java))
                        }

                        is DataResult.Error -> {
                            hideLoading()
                            showMessage(getString(R.string.ca_create_account_toast_error) + " ${result.exception.message}")
                        }
                    }
                }
            } else {
                showMessage(getString(R.string.ca_create_account_passwords_do_not_match_txt))
            }
        } else {
            showMessage(getString(R.string.ca_create_account_empty_fields_txt))
        }
    }

    private fun showLoading() {
    }

    private fun hideLoading() {
    }

    private fun showMessage(message: String) {
        Toast.makeText(this@CreateAccountActivity, message, Toast.LENGTH_SHORT).show()
    }
}