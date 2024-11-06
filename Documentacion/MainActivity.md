# Clase MainActivity

* En esta clase es la clase main definimos los diferentes botones que se en cuentran en la pantalla del incio de la aplicación.

```kotlin
    lateinit var ibtnPhone : ImageButton
    lateinit var ibtnSearch : ImageButton
    lateinit var ibtnMap : ImageButton
    lateinit var ibtnAlarm : ImageButton

```

### Método que inicia la aplicación.

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    //Se establece el diseño de la clase MainActivity
    setContentView(R.layout.activity_main)

    // Llama al metodo para inicializar eventos de los botones
    initEvent()
}
```

### Metodo que establece la función de cada boton.

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
