# Clase SharedPreferences

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
