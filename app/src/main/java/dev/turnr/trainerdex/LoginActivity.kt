package dev.turnr.trainerdex

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class LoginActivity : AppCompatActivity() {
    lateinit var etUsername: EditText
    val MIN_USERNAME_LENGTH = 3
    val MAX_USERNAME_LENGTH = 15
    lateinit var etPassword: EditText
    val MIN_PASSWORD_LENGTH = 8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    fun initView() {
        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)

        // To show back button in actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun validateInput(): Boolean {
        // Username checks
        if (etUsername.text.toString().length < MIN_USERNAME_LENGTH) {
            etUsername.error = getString(R.string.error_username_too_short, MIN_USERNAME_LENGTH)
            return false
        }
        if (etUsername.text.toString().length > MAX_USERNAME_LENGTH) {
            etUsername.error = getString(R.string.error_username_too_long, MAX_USERNAME_LENGTH)
            return false
        }
        if (!isUsernameValid(etUsername.text.toString())) {
            etUsername.error = getString(R.string.error_username_invalid_characters)
            return false
        }

        // Password checks
        if (etPassword.text.toString().length < MIN_PASSWORD_LENGTH) {
            etPassword.error = getString(R.string.error_password_too_short, MIN_PASSWORD_LENGTH)
        }

        return true
    }

    private fun isUsernameValid(username: String): Boolean {
        return Regex("^[A-Za-z0-9]{3,15}$").matches(username)
    }

    fun performLogIn(v: View) {
        if (validateInput()) {
            val username: String = etUsername.text.toString()
            val password: String = etPassword.text.toString()

            val loginUrl = "https://trainerdex.app/accounts/login/"

            // Call API
            // 1. Get CRFS token

            val doc: Document = Jsoup.connect(loginUrl).get()
            val csrfToken: String =
                doc.select("input[name=csrfmiddlewaretoken]").attr("value").toString()

            // 2. POST login url

            var requestParams = HashMap<String, String>()
            requestParams["csrfmiddlewaretoken"] = csrfToken
            requestParams["login"] = username
            requestParams["password"] = password
            requestParams["remember"] = "on"

            val requestUrl: String = loginUrl

            /// do request


            // 3. Store session cookie
            // Success


        }
    }

}