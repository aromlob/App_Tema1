# Clase NewAlarm

Esta clase permite crear una alarma para dentro de 2 minutos, en ella definimos los siguentes métodos que permiten realizar la función de la clase.

### Varibles definidos.

```kotlin
 //Se define los diferentes botones para abrir enlaces web y volver al inicio
    lateinit var ibtn_Back : ImageButton
    lateinit var btn_alarma : Button
```

### Método InitEvent.

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

### Método CreateAlarm.

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
