package com.diego.projfilmes

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //region ScrollView
        val filmes = arrayOf("Homem Formiga", "Guerra Civil", "Homem Aranha", "Guardiões da Galaxia", "Thor: Ragnarok")
        val adapter = ArrayAdapter(applicationContext,
            android.R.layout.simple_spinner_dropdown_item,
            filmes)
        spFilme.adapter = adapter
        spFilme.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selecionado = parent!!.getItemAtPosition(position).toString()
                notificar()
                when(position){
                    0 -> {
                        txtFilme.text = selecionado + "\nForçado a sair de sua própria empresa, Dr. Hank Pym transforma um talentoso ladrão em Homem-Formiga para impedir que seu antigo pupilo consiga replicar a fórmula da roupa que dá o poder do encolhimento, força sobre-humana e a capacidade de controlar um exército de formigas."
                        imgFilme.setImageResource(R.drawable.antman)
                        imgFilme.visibility = View.VISIBLE
                    }
                    1 -> {
                        txtFilme.text = selecionado + "\nO ataque de Ultron faz com que os políticos decidam controlar os Vingadores, já que seus atos afetam toda a humanidade. Tal decisão coloca o Capitão América em rota de colisão com o Homem de Ferro."
                        imgFilme.setImageResource(R.drawable.guerracivil)
                        imgFilme.visibility = View.VISIBLE
                    }
                    2 -> {
                        txtFilme.text = selecionado + "\nDepois de atuar ao lado dos Vingadores, chegou a hora do pequeno Peter Parker voltar para casa e para a sua vida, já não mais tão normal. Lutando diariamente contra pequenos crimes nas redondezas, ele pensa ter encontrado a missão de sua vida quando o terrível vilão Abutre surge amedrontando a cidade. O problema é que a tarefa não será tão fácil como ele imaginava."
                        imgFilme.setImageResource(R.drawable.homemaranha)
                        imgFilme.visibility = View.VISIBLE
                    }
                    3 -> {
                        txtFilme.text = selecionado + "\nO aventureiro do espaço Peter Quill torna-se presa de caçadores de recompensas depois que rouba a esfera de um vilão traiçoeiro, Ronan. Para escapar do perigo, ele faz uma aliança com um grupo de quatro extraterrestres. Quando Quill descobre que a esfera roubada possui um poder capaz de mudar os rumos do universo, ele e seu grupo deverão proteger o objeto para salvar o futuro da galáxia."
                        imgFilme.setImageResource(R.drawable.guardioes)
                        imgFilme.visibility = View.VISIBLE
                    }
                    4 -> {
                        txtFilme.text = selecionado + "\nThor está preso do outro lado do universo. Ele precisa correr contra o tempo para voltar a Asgard e parar Ragnarok, a destruição de seu mundo, que está nas mãos da poderosa e implacável vilã Hela."
                        imgFilme.setImageResource(R.drawable.ragnarok)
                        imgFilme.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    fun notificar() {
        val text = "Projeto Filmes"
        val mNotificationId = 1000

        val channelId = "com.diego.projfilmes"
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)

        val mNotification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(applicationContext, channelId)
        } else {
            Notification.Builder(applicationContext)
        }.apply {
            setContentIntent(pendingIntent)
            setSmallIcon(R.drawable.notification_icon_background)
            setAutoCancel(true)
            setContentTitle(spFilme.selectedItem.toString())
            setContentText(text)
        }.build()
        val nManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nManager.notify(mNotificationId, mNotification)
    }
}
