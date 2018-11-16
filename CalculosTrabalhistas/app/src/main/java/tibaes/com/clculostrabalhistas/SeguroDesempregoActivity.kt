package tibaes.com.clculostrabalhistas

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_seguro_desemprego.*

class SeguroDesempregoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seguro_desemprego)

        // incluir o botão de voltar na página
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnCalcularValor.setOnClickListener {
            var resultado = 0.0
            var meses = 0
            var mesesTrabalhados = etMesesTrabalhados.text.toString().toInt()

            var salario = etAntepenultimoSalario.text.toString().toDouble() +
                    etPenultimoSalario.text.toString().toDouble() +
                    etUltimoSalario.text.toString().toDouble()
            var salarioMedio = if(salario>0.0) salario / 3 else 0.0

            when (salarioMedio) {
                in 0.0..1480.25 -> resultado = salarioMedio * 0.8
                in 1480.26..2467.33 -> resultado = ((salarioMedio - 1480.25) * 0.5) + 1184.20
                in 2467.33..Double.MAX_VALUE -> resultado = 1677.74
            }
            txtValorReceber.text =  String.format("%.2f", resultado.toString().toDouble())

            if (mesesTrabalhados >= 24) {
                meses = 5
            } else if (mesesTrabalhados >= 12) {
                meses = 4
            } else if (mesesTrabalhados >= 9 && rbnSegunda.isChecked || mesesTrabalhados >= 6 && rbnTerceira.isChecked) {
                meses = 3
            } else {
                txtValorReceber.text = "0.00"
                exibirMsg()
            }

            txtParcelas.text = meses.toString()
        }

        btnFonte.setOnClickListener {
            val uris = Uri.parse("http://trabalho.gov.br/seguro-desemprego")
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

    private fun exibirMsg() {
        Toast.makeText(this, R.string.avisoSeguroDesemprego, Toast.LENGTH_LONG).show()
    }
}
