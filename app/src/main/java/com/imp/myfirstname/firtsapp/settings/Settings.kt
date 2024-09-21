package com.imp.myfirstname.firtsapp.settings

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.imp.myfirstname.databinding.ActivitySettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class Settings : AppCompatActivity() {
    companion object {
        const val VOLUME = "volume_value"
        const val DARK_MODE = "dark_mode"
        const val BLUETOOTH = "bluetooth_active"
        const val VIBRATION = "vibration_status"
    }

    private lateinit var binding:ActivitySettingsBinding
    private var firstTime:Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        CoroutineScope(Dispatchers.IO).launch {
            getSettings().filter { firstTime }.collect{settingsModel ->
                //Flujo de datos de tipo "SettingsModel"
                runOnUiThread{
                    binding.switchVibration.isChecked = settingsModel.vibration
                    binding.switchBluetooth.isChecked = settingsModel.bluetooth
                    binding.switchDarkMode.isChecked = settingsModel.darkMode
                    binding.rangeVolume.setValues(settingsModel.volume.toFloat())
                    firstTime = !firstTime
                }
            }
        }

        initUI()
    }
    private fun initUI() {
        binding.rangeVolume.addOnChangeListener{_,value,_ ->
            Log.i("Volumen","Valor del volumen $value")
            CoroutineScope(Dispatchers.IO).launch {
                saveVolume(value.toInt())
            }
        }
        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            CoroutineScope(Dispatchers.IO).launch {
                if(isChecked) {
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
                    delegate.applyDayNight()
                } else {
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
                    delegate.applyDayNight()
                }
                saveChecks(DARK_MODE,isChecked)
            }
        }
        binding.switchBluetooth.setOnCheckedChangeListener { _, isChecked ->
            CoroutineScope(Dispatchers.IO).launch { saveChecks(BLUETOOTH,isChecked) }
        }
        binding.switchVibration.setOnCheckedChangeListener{_,isChecked ->
            CoroutineScope(Dispatchers.IO).launch { saveChecks(VIBRATION,isChecked) }
        }
    }

    private suspend fun saveVolume(value:Int) {
        dataStore.edit {
            //Dentro de la funcion (dependiendo el tipo de dato) se coloca la key, luego se le asigna su valor
            it[intPreferencesKey(VOLUME)] = value
        }
    }
    private suspend fun saveChecks(key:String,value:Boolean) {
        dataStore.edit {
            it[booleanPreferencesKey(key)] = value
        }
    }
    private fun getSettings():Flow<settingsModel> {
        return dataStore.data.map {preferences ->
            settingsModel(
                //Operador que en el caso que el valor sea nulo "null" devuelva un valor por defecto
                volume = preferences[intPreferencesKey(VOLUME)] ?: 50,
                bluetooth = preferences[booleanPreferencesKey(BLUETOOTH)] ?: false,
                darkMode = preferences[booleanPreferencesKey(DARK_MODE)] ?: false,
                vibration = preferences[booleanPreferencesKey(VIBRATION)] ?: true
            )
        }
    }
}