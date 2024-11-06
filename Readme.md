# Aplicación Tema 1

La función de esta aplicación es llamar, abrir el mapa, abrir una web y poner una alarma en nuestro teléfono.

**API de Android Compatible:** Android 6.0 (API nivel 23) y superior.

### Configuración del AndroidManifest y del fichero BuildGradle

###### AndroidManifest

En este fichero tendremos que añadir los siguientes permisos para poder obtener el funcionamiento completo de la app:

```kotlin
    <uses-permission android:name="android.permission.CALL_PHONE" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="com.android.alarm.permission.SET_ALARM" />
```

###### BuildGradle

En este fichero configuraremos lo siguente:

* La version del **sdk** de la app

```kotlin
defaultConfig {
        applicationId = "com.example.apptema1"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    viewBinding {
        enable = true
    }
```

* Estableceremos las **dependencias** necesarias para el funcionamiento de la app

```kotlin
dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)
}
```

### Clase MainActivity

* En esta clase es la clase main definimos los diferentes botones que se en cuentran en la pantalla del incio de la aplicación.

```kotlin
    lateinit var ibtnPhone : ImageButton
    lateinit var ibtnSearch : ImageButton
    lateinit var ibtnMap : ImageButton
    lateinit var ibtnAlarm : ImageButton

```

###### Método que inicia la aplicación.

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    //Se establece el diseño de la clase MainActivity
    setContentView(R.layout.activity_main)

    // Llama al metodo para inicializar eventos de los botones
    initEvent()
}
```

###### Metodo que establece la función de cada boton.

```kotlin
private fun initEvent(){

        //Se asigna a esta variable el boton del telofono desde el layout
        ibtnPhone = findViewById(R.id.ibtn_phone)

        //Se define el funcionamiento del boton cuando pulsemos sobre el
        ibtnPhone.setOnClickListener {
            val intent = Intent(this, CallPhone::class.java)
            startActivity(intent)
        }

        //Se asigna a esta variable el boton del buscar desde el layout
        ibtnSearch = findViewById(R.id.ibtn_search)

        //Se define el funcionamiento del boton cuando pulsemos sobre el
        ibtnSearch.setOnClickListener {
            val intent = Intent(this, Search::class.java)
            startActivity(intent)
        }

        //Se asigna a esta variable el boton del mapa desde el layout
        ibtnMap = findViewById(R.id.ibtn_map)

        //Se define el funcionamiento del boton cuando pulsemos sobre el
        ibtnMap.setOnClickListener {
            val intent = Intent(this, Map::class.java)
            startActivity(intent)
        }

        //Se asigna a esta variable el boton del alarma desde el layout
        ibtnAlarm = findViewById(R.id.ibtn_alarm)

        //Se define el funcionamiento del boton cuando pulsemos sobre el
        ibtnAlarm.setOnClickListener {
            val intent = Intent(this, NewAlarm::class.java)
            startActivity(intent)
        }
   }
```

### Clase CallPhone

Esta clase permite realizar una llamada a cualquier número de teléfono, en ella clase definimos los diferentes botones, solicitamos los permisos de llamada y realizamos la llamada.

###### Variables definidos.

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

###### Método  que inicializa los eventos de los botones de la interfaz.

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

###### Método de pedir permiso de llamada al teléfono.

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

###### Método de realizar la llamada.

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

### Clase SharedPreferences

Esta clase la hemos utilizado en CallPhone, ya que esta clase establece metodos para guardar y recuperar el número de teléfono.

```kotlin
/**
 * Clase SharedPrefences -> Establece metodos de guardar y recuperar el numero de telefono
 * Hereda de la clase AppCompatActivity
 *
 * @author Ángela Romero Lobo
 * @version 1.0.0
 */
class SharedPreferences (context : Context) {

    //Constantes de la clase
    companion object {
        private const val PREFS_NAME = "myPhone"
        private const val NUMBER = "number"
    }

    // Variable que se establece para poder accdeder y modificar los datos almacenados
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    // Metodo que se le pasa el numero que quieres guardar
    fun saveNumber(number: String) {
        prefs.edit().putString(NUMBER, number).apply()
    }

    // Metodo para recuperar el número almacenado
    fun getNumber(): String {
        return prefs.getString(NUMBER, "") ?: ""
    }
}
```

### Clase NewAlarm

Esta clase permite crear una alarma para dentro de 2 minutos, en ella definimos los siguentes métodos que permiten realizar la función de la clase.

###### Varibles definidos.

```kotlin
 //Se define los diferentes botones para abrir enlaces web y volver al inicio
    lateinit var ibtn_Back : ImageButton
    lateinit var btn_alarma : Button
```

###### Método InitEvent.

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

###### Método CreateAlarm.

```kotlin
//Metodo que crea una alarma para dentro de 2 minutos
    private fun createAlarm() {
        //Se obtiene la hora actual del dispositivo
        val calendar = Calendar.getInstance()
        val alarmHour = calendar.get(Calendar.HOUR_OF_DAY)
        //Minutos del sistema + 2 minutos
        val alarmMin = calendar.get(Calendar.MINUTE) + 2

        //Sr configura la alarma con la hora y los minutos del sistema
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_HOUR, alarmHour)
            putExtra(AlarmClock.EXTRA_MINUTES, alarmMin)
            putExtra(AlarmClock.EXTRA_MESSAGE, "La alarma saltará a las $alarmHour:$alarmMin")
        }

        //Verifica si el permiso de alarma esta aceptado o no
        if (ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.SET_ALARM
            ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SET_ALARM), 1)
        } else {
            startActivity(intent)
        }
    }
```

### Clase Search

Esta clase permite abrir diferentes enlaces web en un navegador, en ella definimos los siguentes métodos que permiten realizar la función de la clase.

###### Variables definidas.

```kotlin
//Se define los diferentes botones para abrir enlaces web y volver al inicio
    lateinit var btnzara : ImageButton
    lateinit var btnmercadona : ImageButton
    lateinit var btndiarioJaen : ImageButton
    lateinit var ibtn_Back : ImageButton
```

###### Método InitEvent.

```kotlin
//Metodo que  inicializa los eventos de los diferentes botones de la interfaz
    private fun initEvent() {

        // Botones que al pulsar en ellos, abren las URL correspondientes en el navegador
        btnzara.setOnClickListener{
            openUrl(URL_ZARA)
        }
        btnmercadona.setOnClickListener {
            openUrl(URL_MERCADONA)
        }
        btndiarioJaen.setOnClickListener {
            openUrl(URL_PERIODICO)
        }

        //Se establece el funcionamiento del boton cuando pulsemos sobre el, volviendo al inicio
        ibtn_Back.setOnClickListener {
            val intentReturn = Intent(this, MainActivity::class.java)
            startActivity(intentReturn)
            finish()
        }
    }
```

###### Método OpenUrl.

```kotlin
/**
     * Metodo que intenta abrir una URL en el navegador
     * Muestra un mensaje si no hay navegador disponible
     */
    private fun openUrl(url: String){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        if (intent != null) {
            startActivity(intent)
        }else {
            Toast.makeText(this, NOT_FOUND, Toast.LENGTH_LONG).show()
        }
    }
```

### Clase Map.

Esta clase nos permite abrir el Google Map con una dirección especifica a través de la propia App, en ella definimos los siguentes métodos que permiten realizar la función de la clase.

###### Variables definidas.

```kotlin
//Se define los diferentes botones para abrir el mapa y volver al inicio
    lateinit var btnmapa : Button
    lateinit var ibtn_Back : ImageButton
```

###### Método InitEvent.

```kotlin
//Metodo que  inicializa los eventos de los diferentes botones de la interfaz
    private fun initEvent() {
        //Se establece el funcionamiento del boton cuando pulsemos sobre el
        btnmapa.setOnClickListener{requestPermissions()}

        //Se establece el funcionamiento del boton cuando pulsemos sobre el
        ibtn_Back.setOnClickListener {
            val intentReturn = Intent(this, MainActivity::class.java)
            // Finaliza la actividad actual
            finish()
            // Inicia la actividad principal
            startActivity(intentReturn)
            finish()
        }
    }
```

###### Método RequestPermissions.

```kotlin
// Metodo para solicitar permisos de ubicación del usuario
    private fun requestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            openMap(LOCATION)
        }else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }
//Metodo que comprueba que los permisos estan activados
    private val requestPermissionLauncher = registerForActivityResult( ActivityResultContracts.RequestPermission() ){ isGranted ->

        //Abrira el mapa si los permisos estan activos, si no mostrara un mensaje de que se necesitan habilitar los permisos
        if (isGranted) {
            openMap(LOCATION)
        }else{
            Toast.makeText(this, PERMISION, Toast.LENGTH_LONG).show()
        }
    }
```

###### Método OpenMap.

```kotlin
//Metodo que se encarga de abrir el mapa
    private fun openMap(locationMap : String){
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(locationMap)
            setPackage("com.google.android.apps.maps")
        }
        startActivity(intent)
    }
```
