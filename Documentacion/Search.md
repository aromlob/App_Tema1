# Clase Search

Esta clase permite abrir diferentes enlaces web en un navegador, en ella definimos los siguentes métodos que permiten realizar la función de la clase.

### Variables definidas.

```kotlin
//Se define los diferentes botones para abrir enlaces web y volver al inicio
    lateinit var btnzara : ImageButton
    lateinit var btnmercadona : ImageButton
    lateinit var btndiarioJaen : ImageButton
    lateinit var ibtn_Back : ImageButton
```

### Método InitEvent.

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

### Método OpenUrl.

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
