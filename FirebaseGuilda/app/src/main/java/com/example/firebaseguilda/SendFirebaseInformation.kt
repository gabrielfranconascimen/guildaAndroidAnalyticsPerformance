package com.example.firebaseguilda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.perf.FirebasePerformance
import com.google.firebase.perf.metrics.AddTrace
import com.google.firebase.perf.metrics.Trace
import kotlinx.android.synthetic.main.activity_send_firebase_information.*

class SendFirebaseInformation : AppCompatActivity() {

    private lateinit var trace: Trace

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_firebase_information)

        setupView(this.intent.getStringExtra("nickname"))
        trace = FirebasePerformance.getInstance().newTrace("Guilda");
        trace.start()
    }

    @AddTrace(name = "Configurando componentes")
    private fun setupView(nickname: String?) {
        val message = "My nickname: $nickname"
        nicknameLabel.text = message

        sendAndroidButton.setOnClickListener{ sendFirebaseMessage(true)}
        sendIosButton.setOnClickListener{ sendFirebaseMessage(false)}
    }

    private fun sendFirebaseMessage(isAndroid: Boolean) {

        if (isAndroid) {
            trace.incrementMetric("Android", 1)
        } else {
            trace.incrementMetric("iOS", 1)
        }

    }

    override fun onDestroy() {
        trace.stop()
        super.onDestroy()
    }
}
