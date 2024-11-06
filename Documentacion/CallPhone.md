# Clase CallPhone

Esta clase permite realizar una llamada a cualquier número de teléfono, en ella clase definimos los diferentes botones, solicitamos los permisos de llamada y realizamos la llamada.

### Variables definidos.

```kotlin
// Campo de entrada para el número de teléfono
    private lateinit var etNumberPhone: EditText

    // Botón para iniciar la llamada
    private lateinit var btnPhone: Button

    // Botón de imagen para regresar a la pantalla principal
    private lateinit var ibtnBack: ImageButton

    // TextView para mostrar el último número guardado
    private lateinit var tvSavedNumber: TextView

    // Helper de preferencias compartidas para almacenar y recuperar datos
    private lateinit var preferences: SharedPreferences
```

### Método  que inicializa los eventos de los botones de la interfaz.

```kotlin
//Metodo que  inicializa los eventos de los diferentes botones de la interfaz
    private fun initEvent() {
        //Evento para el btn_alarma que crea una alarma
        btn_alarma.setOnClickListener { createAlarm()}
        //Evento para el ibtn_back para volver al inicio de la aplicacion
        ibtn_Back.setOnClickListener {
            val intentReturn = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intentReturn)
            finish()
        }
    }
```

### Método de pedir permiso de llamada al teléfono.

```kotlin
    /**
     * Metodo que solicita permisos para realizar la llamada
     * Verifica que tipo de version de sdk es
     * Y comprueba si los permisos son aceptados o no
     */
    private fun requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                call()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
            }
        } else {
            call()
        }
    }
     /**
     * Metodo que comprueba que los permisos han sido aceptados o no
     */
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            call()
        } else {
            Toast.makeText(this, PERMISSION_MSG, Toast.LENGTH_LONG).show()
        }
    }
```

### Método de realizar la llamada.

```kotlin
/**
     * Metodo que realiza la llamada
     * Verifica si se a introducido un numero o no
     */
    private fun call() {
        val phoneNumber = etNumberPhone.text.toString()
        if (phoneNumber.isNotEmpty()) {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber"))
            startActivity(intent)

            // Almacena el número introducido
            preferences.saveNumber(phoneNumber)
            // Actualiza el textview con el numero introducido
            tvSavedNumber.text = "Número guardado: $phoneNumber"
        } else {
            Toast.makeText(this, ERROR_NUMBER, Toast.LENGTH_SHORT).show()
        }
    }
```
