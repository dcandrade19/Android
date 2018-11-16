package tibaes.com.clculostrabalhistas

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_ferias.*
import kotlinx.android.synthetic.main.activity_seguro_desemprego.*

class FeriasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ferias)

        // incluir o botão de voltar na página
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnCalcular.setOnClickListener {
            var resultado = 0.0

            var salarioBruto = etSalarioBruto.text.toString().toDouble()
            var horasExtras = etHorasExtras.text.toString().toDouble()

            resultado = salarioBruto + ((salarioBruto + horasExtras) / 3)
            resultado -= resultado * 0.08

            txtValorReceberF.text = String.format("%.2f", resultado.toString().toDouble())
        }

        btnFonteFerias.setOnClickListener {
            val uris = Uri.parse("https://blog.softwareavaliacao.com.br/calcular-ferias/")
            val intent = Intent(Intent.ACTION_VIEW, uris)
            startActivity(intent)
        }
    }

    // adicionando as funções nos itens do menu
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // caso o botão home seja selecionado (esse é o botão padrão quando inserimos o DisplayHome
        return if (item?.itemId == android.R.id.home) {
            // finalizando a activity
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
