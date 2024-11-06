# Aplicación Tema 1

La función de esta aplicación es llamar, abrir el mapa, abrir una web y poner una alarma en nuestro teléfono.

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

* Método que inicia la aplicación.
  ```kotlin
  override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      //Se establece el diseño de la clase MainActivity
      setContentView(R.layout.activity_main)

      // Llama al metodo para inicializar eventos de los botones
      initEvent()
  }
  ```
* Metodo que establece la función de cada boton.

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
