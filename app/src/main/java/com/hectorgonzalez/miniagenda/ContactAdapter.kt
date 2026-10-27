package com.hectorgonzalez.miniagenda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private val dataSet: List<Contact>) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView
        val phoneTextView: TextView
        val initialTextView: TextView

        init {
            // Define click listener for the ViewHolder's View
            nameTextView = view.findViewById(R.id.contact_name)
            phoneTextView = view.findViewById(R.id.contact_phone)
            initialTextView = view.findViewById(R.id.contact_initial)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.contact_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.setBackgroundColor(android.graphics.Color.WHITE)

        val contactColors = listOf(
            R.color.contact_blue,
            R.color.contact_green,
            R.color.contact_purple,
            R.color.contact_orange,
            R.color.contact_red,
            R.color.contact_teal,
            R.color.contact_pink
        )

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val contact = dataSet[position]

        viewHolder.nameTextView.text = contact.name
        viewHolder.phoneTextView.text = contact.phone
        viewHolder.initialTextView.text = contact.name.first().uppercase()

        // Queria hacer la agenda un poco mas bonita con colores pero no se
        // se me ha ido la olla, esto es una formula que devuelve un indice
        // de la lista de colores basandose en el nombre de la persona
        // convertido a bits o algo asi.
        val index = (contact.name.lowercase().hashCode() and 0x7FFFFFFF) % contactColors.size
        viewHolder.initialTextView.backgroundTintList =
            android.content.res.ColorStateList.valueOf(
                androidx.core.content.ContextCompat.getColor(
                    viewHolder.itemView.context,
                    contactColors[index]
                )
            )
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}