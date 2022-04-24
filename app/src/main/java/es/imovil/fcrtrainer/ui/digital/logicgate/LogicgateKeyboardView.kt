package es.imovil.fcrtrainer.ui.digital.logicgate

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import es.imovil.fcrtrainer.R
import kotlin.math.max
import kotlin.math.min

class LogicgateKeyboardView(context: Context?, attributeSet: AttributeSet) : LinearLayout(context, attributeSet), View.OnClickListener {

    private var mEditText: EditText? = null // this receives the keystrokes

    private fun getLayoutId(): Int {
        return R.layout.logicgate_keyoard
    }

    override fun onClick(view: View?) {
        if (mEditText == null) {

        }
        val button = view as Button
        val keyPressed = button.text
        val start = mEditText!!.selectionStart
        val end = mEditText!!.selectionEnd
        val realStart = min(start, end)
        val realEnd = max(start, end)
        val text = mEditText!!.editableText
        if (button.id == R.id.solucion) {
            if (text.isEmpty() || realStart - 1 < 0) return
            if (start == end) {
                text.delete(start - 1, start)
            } else {
                text.delete(realStart, realEnd)
            }
        } else {
            text.replace(
                realStart, realEnd,
                keyPressed, 0, keyPressed.length
            )
        }
    }

    companion object {
        private const val delChar = "◀"
    }

    init {
        val layoutId = getLayoutId()
        val inflater = context
            ?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rootView = layoutId.let { inflater.inflate(it, this, true) }
        if (!isInEditMode) {
            val allButtons = rootView?.touchables
            if (allButtons != null) {
                for (v in allButtons) {
                    v.setOnClickListener(this)
                }
            }
            if (rootView != null) {
                (rootView.findViewById<View>(R.id.solucion) as Button).text = delChar
            }
        }
    }
}