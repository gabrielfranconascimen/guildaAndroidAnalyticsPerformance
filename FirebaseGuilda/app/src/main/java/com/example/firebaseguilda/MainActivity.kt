package com.example.firebaseguilda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.CrashlyticsRegistrar
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var analytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        analytics = FirebaseAnalytics.getInstance(this)
        analytics.setUserId("user")

        setupClick()
    }


    private fun setupClick() {
        val bundle = Bundle()

        bundle.putString("botao", "criando_acao_de_enviar")
        analytics.logEvent("Configuracao", bundle)

        saveNickname.setOnClickListener {

            if (nickname.text.isNotEmpty())
                sendNickname(nickname.text.toString())
            else {
                FirebaseCrashlytics.getInstance().setCustomKey("key_name", "Faltando nome")

//                FirebaseCrashlytics.getInstance().log("Faltando parametros")
                FirebaseCrashlytics.getInstance().recordException(GuildaException("Faltando parametros"))
            }

        }
    }


    private fun sendNickname(nickname: String) {
        val intent = Intent(this, SendFirebaseInformation::class.java)
        intent.putExtra("nickname", nickname)

        val bundle = Bundle()

        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1")
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, nickname)

        analytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)

        startActivity(intent)
    }
}
