package github.leavesczy.matisse

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import github.leavesczy.matisse.internal.MatisseCaptureActivity

/**
 * @Author: CZY
 * @Date: 2023/4/11 16:38
 * @Desc:
 */
class MatisseCaptureContract : ActivityResultContract<MatisseCapture, MediaResource?>() {

    companion object {

        private const val keyRequest = "keyRequest"

        private const val keyResult = "keyResult"

        internal fun getRequest(intent: Intent): MatisseCapture {
            return intent.getParcelableExtra(keyRequest)!!
        }

        internal fun buildResult(mediaResource: MediaResource): Intent {
            val intent = Intent()
            intent.putExtra(keyResult, mediaResource)
            return intent
        }

    }

    override fun createIntent(context: Context, input: MatisseCapture): Intent {
        val intent = Intent(context, MatisseCaptureActivity::class.java)
        intent.putExtra(keyRequest, input)
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): MediaResource? {
        return if (resultCode == Activity.RESULT_OK && intent != null) {
            intent.getParcelableExtra(keyResult)
        } else {
            null
        }
    }

}