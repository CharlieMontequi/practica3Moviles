package com.example.practica3moviles

import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // primero inicializar los elementos
        val textEditNombre = findViewById<EditText>(R.id.editTextTextNombre)
        val textEditapellido = findViewById<EditText>(R.id.editTextTextApellidos)
        val textEditCorreo = findViewById<EditText>(R.id.editTextTextCorreo)
        var imagenPerfil = findViewById<ImageView>(R.id.imageViewPerfil)
        imagenPerfil.setImageResource(R.drawable.fotoperfil)

        // el radio group
        val radioGroupGenero = findViewById<RadioGroup>(R.id.radioGroupGenero)

        //el spinner con sus opciones
        val spinnerPais = findViewById<Spinner>(R.id.spinnerPaises)
        // establecer el arrayadapter con la lsita de paises
        ArrayAdapter.createFromResource(
            this,
            R.array.arrayPaises,
            android.R.layout.simple_spinner_item
        ).also { adaptador ->

            adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinnerPais.adapter = adaptador
        }

        // los check box y agruparlos
        val checkBoxLectura = findViewById<CheckBox>(R.id.checkBoxLectura)
        val checkBoxDeporte = findViewById<CheckBox>(R.id.checkBoxDeporte)
        val checkBoxMusica = findViewById<CheckBox>(R.id.checkBoxMusica)
        val checkBoxArte = findViewById<CheckBox>(R.id.checkBoxArte)

        // se guardan en un array para poder revisarlos todos mas facilmente
        val arrayCheckBoz= listOf(checkBoxArte,checkBoxMusica,checkBoxDeporte,checkBoxDeporte)

        //la seekbar y medir el grado de satisfaccion
        val textViewSatisfaccion = findViewById<TextView>(R.id.textViewSatisfaccion)
        val seekBarSatisfaccion = findViewById<SeekBar>(R.id.seekBarSatisfaccion)
        seekBarSatisfaccion.min = 0
        seekBarSatisfaccion.max= 10
        seekBarSatisfaccion.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // al sacar el nombre de la propia carpeta de strng te ahorrar un textview extra
                textViewSatisfaccion.text = "${getString(R.string.satisfaccion)} $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }


        })

        // el switch y comprobar si se ha suscripto
        val switchSuscripcion = findViewById<Switch>(R.id.suscripcion)

        //el boton y el escuchardos en el que lanzar los avisos
        val botonGuardar = findViewById<Button>(R.id.buttonGuardar)
        val textViewRevisado = findViewById<TextView>(R.id.textViewRevisado)

        botonGuardar.setOnClickListener {

            //coger los datos del genero
            val generoMarcadoId = radioGroupGenero.checkedRadioButtonId
            var generoMarcado : String =""
            if (generoMarcadoId != -1) {
                val selectedRadioButton = findViewById<RadioButton>(generoMarcadoId)
                //Escribo en una variable String la opción seleccionada
                generoMarcado = selectedRadioButton.text.toString()
            }else{
                Toast.makeText(this, "${getString(R.string.avisosGenero)}", Toast.LENGTH_SHORT).show()
            }

            //coger el valor final de seek bar
            val satisfaccionFinal = seekBarSatisfaccion.progress

            // comproar la suscripcion
            val estadoSuscripcion = switchSuscripcion.isActivated
            var suscripcionMensaje :String
            if (estadoSuscripcion == true){
                suscripcionMensaje= getString(R.string.suscripcionPositiva)
            }else{
                suscripcionMensaje= getString(R.string.suscripcionNegativa)
            }

            if ((textEditNombre == null) || (textEditapellido == null)||(textEditCorreo==null)) {
                Toast.makeText(this, "${getString(R.string.avisosNombre)}", Toast.LENGTH_SHORT).show()
            }else{
                textViewRevisado.text="Nombre: ${textEditNombre.text.toString()} ${textEditapellido.text.toString()} \n" +
                        "Correo: ${textEditCorreo.text.toString()}\t Genero: ${generoMarcado.toString()} \t Satisfacción: ${satisfaccionFinal.toString()}\n" +
                        "$suscripcionMensaje"
            }
        }

    }
}