package dev.turnr.trainerdex.ocr

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import dev.turnr.trainerdex.R
import java.text.DecimalFormatSymbols
import java.util.*

class Debug : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when (intent?.action) {
            Intent.ACTION_SEND -> {
                handleSentImage(intent)
            }
        }
    }

    private fun handleSentImage(intent: Intent) {

        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        (intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let {
            Log.d("Uri path: ", it.toString())
            val image = InputImage.fromFilePath(this, it)

            val result = recognizer.process(image)
                .addOnSuccessListener { visionText ->
                    Log.i("ocr.debug.info", Locale.getDefault().language)
                    Log.i("ocr.debug.result", visionText.text)

                    val totalXPRegex = getString(R.string.regex_total_xp).toRegex()
                    Log.d("ocr.debug.totalXPRegex", totalXPRegex.toString())
                    val totalXPBigInt = totalXPRegex.find(visionText.text)?.value?.replace(
                        DecimalFormatSymbols.getInstance().groupingSeparator.toString(), ""
                    )
                        ?.toBigInteger()

                    Log.i("ocr.debug.result.total_xp", totalXPBigInt.toString())
                }
                .addOnFailureListener { _ -> Log.e("ocr.debug.error", "OCR Failed") }
        }
    }
}
