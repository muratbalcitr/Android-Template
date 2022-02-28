package com.m2lifeApps.movieDb.core.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import coil.loadAny
import com.m2lifeApps.movieDb.R
import com.m2lifeApps.movieDb.core.binding.visible
import kotlinx.android.synthetic.main.view_toolbar.view.*

/**
 * @user: murat.balcı
 */

class Toolbar @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    var onLeftButton: (() -> Unit)? = null
    var onRightButton: (() -> Unit)? = null
    var onRightLeftButton: (() -> Unit)? = null

    var leftIcon: Drawable? = null
    var title: String? = null
    var spot: String? = null
    var rightIcon: Drawable? = null
    var rightLeftIcon: Drawable? = null

    init {
        View.inflate(context, R.layout.view_toolbar, this)

        if (attributeSet != null) {
            val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.Toolbar)
            try {

                if (attributes.hasValue(R.styleable.Toolbar_left_icon)) {
                    leftIcon = ContextCompat.getDrawable(
                        context,
                        attributes.getResourceId(R.styleable.Toolbar_left_icon, 0)
                    )
                }

                if (attributes.hasValue(R.styleable.Toolbar_title_text)) {
                    title = attributes.getString(R.styleable.Toolbar_title_text)
                }
                if (attributes.hasValue(R.styleable.Toolbar_spot_text)) {
                    spot = attributes.getString(R.styleable.Toolbar_spot_text)
                }

                if (attributes.hasValue(R.styleable.Toolbar_right_image)) {
                    rightIcon = ContextCompat.getDrawable(
                        context,
                        attributes.getResourceId(R.styleable.Toolbar_right_image, 0)
                    )
                }
                if (attributes.hasValue(R.styleable.Toolbar_right_left_image)) {
                    rightLeftIcon = ContextCompat.getDrawable(
                        context,
                        attributes.getResourceId(R.styleable.Toolbar_right_left_image, 0)
                    )
                }

                initView(leftIcon, title, rightIcon, spot, rightLeftIcon)
            } finally {
                attributes.recycle()
            }
        }
    }

    private fun initView(
        leftIcon: Drawable?,
        title: String?,
        rigthImage: Drawable?,
        spotText: String?,
        rigthLeftImage: Drawable?
    ) {
        left_button.visibility = View.GONE
        title_text_view.visibility = View.GONE

        leftIcon?.let {
            left_button.visibility = View.VISIBLE
            left_button.setImageDrawable(it)
            left_button.setOnClickListener { onLeftButton?.invoke() }
        }

        title?.let {
            title_text_view.visibility = View.VISIBLE
            title_text_view.text = it
        }
    }

    fun updateLeftButtontVisiblity(visible: Boolean) {
        left_button.visibility = if (visible) View.VISIBLE else View.GONE
    }

    fun updateTitle(@StringRes title: Int) {
        this.title = context.getString(title)
        title_text_view.text = context.getString(title)
        title_text_view.visibility = View.VISIBLE
    }

    fun updateTitleString(title: String) {
        this.title = title
        title_text_view.text = title
        title_text_view.visibility = View.VISIBLE
    }

    fun updateIcon(@DrawableRes icon: Int) {
        left_button.visibility = if (visible) View.VISIBLE else View.GONE
        left_button.loadAny(icon)
        left_button.setOnClickListener {
            onLeftButton?.invoke()
        }
    }
}
