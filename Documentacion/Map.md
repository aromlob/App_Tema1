# Clase Map.

Esta clase nos permite abrir el Google Map con una dirección especifica a través de la propia App, en ella definimos los siguentes métodos que permiten realizar la función de la clase.

### Variables definidas.

```kotlin
//Se define los diferentes botones para abrir el mapa y volver al inicio
    lateinit var btnmapa : Button
    lateinit var ibtn_Back : ImageButton
```

### Método InitEvent.

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

### Método RequestPermissions.

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

### Método OpenMap.

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
