package com.abe.boilerplatemvvm.base.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.abe.boilerplatemvvm.R
import com.abe.boilerplatemvvm.aide.utils.AppConstants.PrefKeys.KEY_DEFAULT
import com.abe.boilerplatemvvm.aide.utils.AppConstants.PrefKeys.KEY_LANG
import com.abe.boilerplatemvvm.aide.utils.AppConstants.PrefKeys.KEY_THEME
import com.abe.boilerplatemvvm.aide.utils.DialogUtils
import com.abe.boilerplatemvvm.base.viewmodel.BaseViewModel
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.abe.boilerplatemvvm.base.stateUI.ErrorDescription

import com.abe.boilerplatemvvm.base.stateUI.StateViewModel
import java.util.*

@SuppressLint("Registered")
abstract class BaseActivity<BINDING : ViewDataBinding> : AppCompatActivity(), BaseView {

    //    abstract val bindingInflater: (LayoutInflater) -> BINDING
    abstract fun getViewModel(): BaseViewModel?
    abstract fun hasConnectivity(connectivity: Boolean)

    //    protected lateinit var binding: BINDING
//    private var _binding: BINDING? = null
//    protected val binding: BINDING get() = _binding!!
    lateinit var binding: BINDING
    private lateinit var dialog: Dialog
    private var originalSoftInputMode: Int? = null
    private lateinit var inputManager: InputMethodManager
    private var availableNetwork: Network? = null
    private lateinit var connectivityManager: ConnectivityManager
    lateinit var navController: NavController


//    abstract fun setBinding(layoutInflater: LayoutInflater): BINDING

    /**
     * @param newBase the default base context of the application
     * @return overridden configuration with user's selected night mode and language preferences
     */
    private fun getOverrideConfiguration(newBase: Context?): Configuration {
        val configuration = Configuration(newBase?.resources?.configuration)
        val appName = applicationInfo.loadLabel(packageManager).toString()
        val sharedPreferences = getSharedPreferences(appName, Context.MODE_PRIVATE)
        val uiMode = sharedPreferences.getInt(
            KEY_THEME, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        )
        AppCompatDelegate.setDefaultNightMode(uiMode)
        sharedPreferences.getString(KEY_LANG, KEY_DEFAULT)?.let {
            if (it != KEY_DEFAULT) {
                configuration.setLocale(Locale(it))
            }
        }
        return configuration
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        applyOverrideConfiguration(getOverrideConfiguration(newBase))
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        super.applyOverrideConfiguration(overrideConfiguration)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
//        _binding = bindingInflater(layoutInflater)
        dialog = DialogUtils.createProgressDialog(this, false)
        inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        getViewModel()?.let { viewModel ->
//            viewModel.loader.observe(this, Observer {
//                it?.let {
//                    loaderVisibility(it)
//                }
//            })
//            viewModel.error.observe(this, Observer {
//                it?.let {
//                    showToast(it)
//                }
//            })
//        }
        getViewModel()?.outcomeLiveData?.observe(this, Observer {
            when (it) {
                is StateViewModel.Loading -> {
                    if (it.showLoader)
                        loaderVisibility(true)
                }
                is StateViewModel.Success -> loaderVisibility(false)
                is StateViewModel.Failure -> loaderVisibility(false)
                is StateViewModel.NetworkError -> loaderVisibility(false)
            }

        })
        registerNetworkCallback()
        getNavHostId()?.let {
            val navHostFragment = supportFragmentManager.findFragmentById(it) as NavHostFragment
            navController = navHostFragment.navController
        }
    }

    override fun onDestroy() {
        unregisterNetworkCallback()
        super.onDestroy()
    }

    override fun setSoftInputMode(mode: Int) {
        originalSoftInputMode = window.attributes.softInputMode
        window.setSoftInputMode(mode)
    }

    override fun resetSoftInputMode() {
        originalSoftInputMode?.let {
            window.setSoftInputMode(it)
        }
    }

    override fun showKeyboard(editText: AppCompatEditText) {
        editText.post {
            editText.requestFocus()
            inputManager.showSoftInput(editText.rootView, InputMethodManager.SHOW_FORCED)
        }
    }

    override fun hideKeyboard() {
        this.currentFocus?.let {
            inputManager.hideSoftInputFromWindow(it.applicationWindowToken, 0)
        }
    }

    override fun sessionExpire() {
        showToast(getString(R.string.session_expired))
    }

    override fun noConnectivity() {
        showToast(getString(R.string.message_no_network_connected_str))
    }

    override fun loaderVisibility(visibility: Boolean) {
        if (::dialog.isInitialized) {
            if (visibility) {
                if (!dialog.isShowing)
                    dialog.show()
            } else {
                dialog.dismiss()
            }
        }
    }

    override fun showToast(message: String?) {
        message?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onApiError(errorDescription: ErrorDescription) {
        showToast(errorDescription.message)

    }

    override fun navigateToDestination(direction: NavDirections) {
        if (::navController.isInitialized) {
            navController.navigate(direction)
        } else {
            throw IllegalAccessException("Nav Controller not set in activity")
        }

    }

    override fun navigateToDestination(id: Int, args: Bundle) {
        if (::navController.isInitialized) {
            navController.navigate(id, args)
        } else {
            throw IllegalAccessException("Nav Controller not set in activity")
        }

    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            availableNetwork = network
            runOnUiThread { hasConnectivity(true) }
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            if (network == availableNetwork) {
                runOnUiThread { hasConnectivity(false) }
            }
        }
    }

    private fun registerNetworkCallback() {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    private fun unregisterNetworkCallback() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}