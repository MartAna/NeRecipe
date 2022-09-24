package ru.netology.nerecipe.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import ru.netology.nerecipe.R
import ru.netology.nerecipe.util.ArrayArg

class FilterDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val multiItems = R.array.Category
            val selectedItems = ArrayList<Int>()
            val builder = AlertDialog.Builder(it)

            builder.setTitle(R.string.choosing)
                .setMultiChoiceItems(multiItems, null,
                    DialogInterface.OnMultiChoiceClickListener { _, which, isChecked ->
                        if (isChecked) {
                            selectedItems.add(which)
                        } else if (selectedItems.contains(which)) {
                            selectedItems.remove(which)
                        }
                    })
                .setPositiveButton(R.string.ok,
                    DialogInterface.OnClickListener { _, _ ->
                        findNavController().navigate(
                            R.id.recipesFragment,
                            Bundle().apply {
                                arrayArg = selectedItems
                            }
                        )
                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, _ ->
                        dialog.cancel()
                    })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {
        var Bundle.arrayArg: ArrayList<Int>? by ArrayArg
    }
}
