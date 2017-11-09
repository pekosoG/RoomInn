package mx.devf.roominn.Components

import android.widget.EditText
import android.text.Editable
import android.text.TextWatcher
import java.lang.ref.WeakReference
import java.math.BigDecimal
import java.text.NumberFormat


/**
 * Created by Luis Rios on 08/11/2017.
 */
//priceEditText.addTextChangedListener(new MoneyTextWatcher(priceEditText));

class MoneyTextWatcher(editText: EditText) : TextWatcher {
    private val editTextWeakReference: WeakReference<EditText>

    init {
        editTextWeakReference = WeakReference<EditText>(editText)
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(editable: Editable) {
        val editText = editTextWeakReference.get() ?: return
        val s = editable.toString()
        editText.removeTextChangedListener(this)
        val cleanString = s.toString().replace("[$,.]".toRegex(), "")
        val parsed = BigDecimal(cleanString).setScale(2, BigDecimal.ROUND_FLOOR).divide(BigDecimal(100), BigDecimal.ROUND_FLOOR)
        val formatted = NumberFormat.getCurrencyInstance().format(parsed)
        editText.setText(formatted)
        editText.setSelection(formatted.length)
        editText.addTextChangedListener(this)
    }
}