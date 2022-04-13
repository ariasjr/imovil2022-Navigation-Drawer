package es.imovil.fcrtrainer.ui.codes.hexadecimal

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import es.imovil.fcrtrainer.R

class HexadecimalKeyboardView(context: Context?, attributeSet: AttributeSet) : LinearLayout(context, attributeSet), View.OnClickListener {

    private var mEditText: EditText? = null // this receives the keystrokes

    private fun getLayoutId(context: Context, attrs: AttributeSet): Int {
        return R.layout.hexadecimal_keyoard
    }

    fun assignEditText(textView: EditText?) {
        mEditText = textView
    }

    override fun onClick(view: View?) {
        if (mEditText == null) {
            // No textView assigned. By default, we look for one with id answer
            val parent = parent as View
            mEditText = parent.findViewById(R.id.text_view_answer)
            if (mEditText == null) { // not found
                return
            }
        }
        val button = view as Button
        val keyPressed = button.text
        val start = mEditText!!.selectionStart
        val end = mEditText!!.selectionEnd
        val realStart = Math.min(start, end)
        val realEnd = Math.max(start, end)
        val text = mEditText!!.editableText
        if (button.id == R.id.key_delete) {
            if (text.length <= 0 || realStart - 1 < 0) return
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
        val layoutId = context?.let { getLayoutId(it, attributeSet) }
        val inflater = context
            ?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rootView = layoutId?.let { inflater.inflate(it, this, true) }
        if (!isInEditMode) {
            val allButtons = rootView?.touchables
            if (allButtons != null) {
                for (v in allButtons) {
                    v.setOnClickListener(this)
                }
            }
            if (rootView != null) {
                (rootView.findViewById<View>(R.id.key_delete) as Button).text = HexadecimalKeyboardView.delChar
            }
        }
    }
}