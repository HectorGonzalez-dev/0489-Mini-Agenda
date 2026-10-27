package com.hectorgonzalez.miniagenda

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val templateNames = arrayListOf(
        "Juan", "María", "Pedro", "Ana",
        "Carlos", "Laura", "Miguel", "Lucía",
        "David", "Sofía", "Pablo", "Elena",
        "Alejandro", "Carmen", "Daniel", "Paula",
        "Javier", "Marta", "Álvaro", "Isabel"
    )

    private val templateSurnames = arrayListOf(
        "García", "Martínez", "López", "Sánchez",
        "Pérez", "Gómez", "Martín", "Jiménez",
        "Ruiz", "Hernández", "Díaz", "Moreno",
        "Álvarez", "Muñoz", "Romero", "Alonso",
        "Gutiérrez", "Navarro", "Torres", "Domínguez"
    )

    private val contacts = ArrayList<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Generar contactos aleatorios
        // He visto que existe esto llamado repeat, se ve que funciona
        // mas o menos como un bucle for, queria probarlo, parecia oportuno.
        repeat(40) {
            // He visto esta forma de generar valores aleatorios
            // Que comodo no? me flipa
            val randomFullName = templateNames.random() + " " + templateSurnames.random()
            val randomPhoneNumber = (100000000..999999999).random()

            val randomContact = Contact(randomFullName, randomPhoneNumber.toString())
            contacts.add(randomContact)
        }

        val contactAdapter = ContactAdapter(contacts)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewContacts)
        recyclerView.adapter = contactAdapter
    }
}