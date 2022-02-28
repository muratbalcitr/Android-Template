package com.m2lifeApps.movieDb.core.platform

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.m2lifeApps.movieDb.R
import com.m2lifeApps.movieDb.core.extensions.observeEvent
import io.reactivex.rxjava3.disposables.CompositeDisposable

const val ARG_PAGE_TITLE = "ARG_PAGE_TITLE"

abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int,
    private val viewModelClass: Class<VM>
) : Fragment() {
    val viewModel by lazy {
        ViewModelProvider(this).get(viewModelClass)
    }
    lateinit var binding: DB
    lateinit var viewModelProvider: ViewModelProvider
    lateinit var pageTitle: String
    internal var disposable = CompositeDisposable()

    abstract fun getDeepLinkName(): String
    abstract fun onInitDataBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSoftInput()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitDataBinding()
        observeEvent(viewModel.baseEvent, ::onViewEvent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        showProgressView()

        return binding.root
    }

    private fun onViewEvent(event: BaseViewEvent) {
        when (event) {
            BaseViewEvent.ShowCommonNetworkError -> {}
            BaseViewEvent.ShowConnectivityError -> {}
            is BaseViewEvent.ShowCustomError -> {
                showError(event.message)
            }
        }
    }

    private fun showError(error: String) {
        try {
            (requireActivity() as? AppCompatActivity)?.let {
                Toast.makeText(it, error, Toast.LENGTH_LONG).show()
            }
        } catch (e: IllegalStateException) {
        }
    }

    internal fun hideSoftInput() {
        activity?.let {
            (it.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
                it.currentFocus
                hideSoftInputFromWindow(
                    (it.currentFocus ?: View(requireContext())).windowToken,
                    0
                )
            }
        }
    }

    internal fun showSoftInput() {
        val inputManager =
            requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }

    internal fun showProgressView() {
        // Todo add progressView State
    }
    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return when (transit) {
            FragmentTransaction.TRANSIT_FRAGMENT_FADE -> if (enter) {
                AnimationUtils.loadAnimation(activity, R.anim.fade_in_left)
            } else {
                AnimationUtils.loadAnimation(activity, R.anim.fade_out_left)
            }
            FragmentTransaction.TRANSIT_FRAGMENT_CLOSE -> if (enter) {
                AnimationUtils.loadAnimation(activity, R.anim.fade_in_left)
            } else {
                AnimationUtils.loadAnimation(activity, R.anim.fade_out_left)
            }
            FragmentTransaction.TRANSIT_FRAGMENT_OPEN -> if (enter) {
                AnimationUtils.loadAnimation(activity, R.anim.fade_in_left)
            } else {
                AnimationUtils.loadAnimation(activity, R.anim.fade_out_left)
            }
            else -> if (enter) {
                AnimationUtils.loadAnimation(activity, R.anim.fade_in_left)
            } else {
                AnimationUtils.loadAnimation(activity, R.anim.fade_out_left)
            }
        }
    }
}
