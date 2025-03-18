package com.projeto.cadastro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projeto.cadastro.ui.theme.CadastroTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


object RetrofitClient {
    private const val BASE_URL = "https://viacep.com.br/ws/"

    val instance: ViaCepService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ViaCepService::class.java)
    }
}




data class endereco(
        val CEP: String,
        val Numero: String,
        val Endereco: String,
        val Estado: String,


)


interface ViaCepService {
    @GET("{cep}/json/")
    fun buscarCep(@Path("cep") cep: String): Call<endereco>
}



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            CadastroTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    //Cadastro()
                }
            }
        }
    }
}




@Composable

fun Cadastro() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,


        ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(80.dp),

            )
        Text(
            "Insira seus dados de endereço.",
            textAlign = TextAlign.Center,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)

        )
        Text(
            "Insira seus dados para continuar.",
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))
        Registros()
    }
}

@Composable
fun Registros() {
    var Rua by remember { mutableStateOf("") }
    var Numero by remember { mutableStateOf("") }
    var CEP by remember { mutableStateOf("") }
    var Estado by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = Rua,
            onValueChange = { Rua = it },
            label = { Text("Rua") },
            shape = RoundedCornerShape(12.dp),
            placeholder = { Text("Digite o nome da sua rua") }
        )



        TextField(
            value = Numero,
            onValueChange = { Numero = it },
            label = { Text("Numero") },
            shape = RoundedCornerShape(12.dp),
            placeholder = {
                Text("Digite o numero da sua casa")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = CEP,
            onValueChange = { CEP = it },
            label = { Text("CEP") },
            placeholder = { Text("Digite seu CEP") },
            shape = RoundedCornerShape(12.dp)
        )

        TextField(
            value = Estado,
            onValueChange = { Estado = it },
            label = { Text("Estado") },
            placeholder = { Text("Digite o Estado em que você mora") },
            shape = RoundedCornerShape(12.dp),

            )




        Spacer(modifier = Modifier.height(1.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF087F73)),
            shape = RoundedCornerShape(8.dp)


        ) {
            Text(text = "Próximo", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(16.dp))
        }}}





