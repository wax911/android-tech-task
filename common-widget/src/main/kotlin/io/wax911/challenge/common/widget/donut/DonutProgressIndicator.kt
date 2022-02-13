package io.wax911.challenge.common.widget.donut

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import com.airbnb.paris.extensions.style
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textview.MaterialTextView
import io.wax911.challenge.common.widget.R
import io.wax911.challenge.core.extensions.dp
import io.wax911.challenge.domain.credit.enity.Credit
import io.wax911.challenge.common.widget.donut.presenter.DonutProgressIndicatorPresenter

class DonutProgressIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val scoreInformation = MaterialTextView(context).apply {
        layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        ).also { params ->
            params.gravity = Gravity.CENTER
        }
        style {
            textAlignment = TEXT_ALIGNMENT_CENTER
            textSize = 18f
        }
    }

    private val circularProgressIndicator = CircularProgressIndicator(context).apply {
        layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        ).also { params ->
            params.gravity = Gravity.CENTER_HORIZONTAL
        }
        setIndicatorColor(
            ContextCompat.getColor(context, R.color.primary),
            ContextCompat.getColor(context, R.color.secondary),
            ContextCompat.getColor(context, R.color.primaryInverse),
        )
        indicatorSize = 260.dp
        trackCornerRadius = 16.dp
        trackThickness = 4.dp
        isIndeterminate = true
    }

    private var presenter: DonutProgressIndicatorPresenter? = null

    init {
        onInit(context)
    }

    fun setUpWith(entity: Credit) {
        presenter = DonutProgressIndicatorPresenter(entity)
        presenter?.applyProgressIndicatorProgress(circularProgressIndicator)
        presenter?.applyText(scoreInformation)
    }

    private fun onInit(context: Context) {
        background = ContextCompat.getDrawable(context, R.drawable.ring_background)
        addView(circularProgressIndicator)
        addView(scoreInformation)
    }
}