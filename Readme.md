# Aplicación Tema 1

La función de esta aplicación es llamar, abrir el mapa, abrir una web y poner una alarma en nuestro teléfono.

**API de Android Compatible:** Android 6.0 (API nivel 23) y superior.

### Configuración del AndroidManifest y del fichero BuildGradle

###### AndroidManifest.

En este fichero tendremos que añadir los siguientes permisos para poder obtener el funcionamiento completo de la app:

```kotlin
    <uses-permission android:name="android.permission.CALL_PHONE" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="com.android.alarm.permission.SET_ALARM" />
```

###### BuildGradle.

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


* Para realizar dicha aplicación, hemos utilizado las siguientes clases:
  1. **Clase MainActivity ->**  En esta clase se definen los botones de la pantalla principal y se gestiona la navegación hacia las diferentes funciones de la aplicación.
  2. **Clase CallPhone ->** En esta clase se permite la realización de la llamadas de teléfono, y para ello solicita permisos y guarda el último número llamado.
  3. **Clase SharedPreferences ->** En esta clase se gestiona el alamacenamiento y recuperación del número de teléfono usando preferencias compartidas.
  4. **Clase NewAlarm->** En esta clase se crea una alarma para dentro de 2 minutos, solicitando los permisos si es necesario.
  5. **Clase Search ->** En esta clase se abre diferentes enlaces web en el navegador cuando se presionan los botones correspondientes.
  6. **Clase Map ->** En esta clase se abre el Google Maps con una ubicación específica tras solicitar los permisos necesarios.
