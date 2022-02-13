package io.wax911.challenge.common.widget.donut.presenter

import android.text.SpannableStringBuilder
import androidx.core.content.ContextCompat
import androidx.core.text.color
import androidx.core.text.scale
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textview.MaterialTextView
import io.wax911.challenge.common.widget.R
import io.wax911.challenge.core.extensions.dp
import io.wax911.challenge.domain.credit.enity.Credit

internal class DonutProgressIndicatorPresenter(private val entity: Credit) {

    fun applyText(materialTextView: MaterialTextView) {
        val context = materialTextView.context
        val tint = ContextCompat.getColor(context, R.color.secondary)

        materialTextView.text = SpannableStringBuilder()
            .append("Your credit score is")
            .append("\n")
            .scale(1.2f.dp) {
                color(tint) {
                    append(entity.score.toString())
                }
            }
            .append("\n")
            .append("out of ${entity.maximum}")
    }

    fun applyProgressIndicatorProgress(progressIndicator: CircularProgressIndicator) {
        progressIndicator.max = entity.maximum
        progressIndicator.setProgressCompat(entity.score, true)
    }
}