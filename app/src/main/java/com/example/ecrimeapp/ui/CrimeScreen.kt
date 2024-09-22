package com.example.ecrimeapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.ecrimeapp.R
import com.example.ecrimeapp.data.Criminoso
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrimeScreen(viewModel: CrimeViewModel) {
    var vulgo by remember { mutableStateOf("") }
    var crime by remember { mutableStateOf("") }
    var timeCoracao by remember { mutableStateOf("") }
    var qtdPassagens by remember { mutableStateOf("") }
    var bairro by remember { mutableStateOf("") }
    var selectedBandidoId by remember { mutableStateOf<Int?>(null) }

    val criminosoList by viewModel.criminosoList.collectAsState(initial = emptyList())

    val isFormValid = vulgo.isNotBlank() && qtdPassagens.isNotBlank() && crime.isNotBlank() && timeCoracao.isNotBlank() && bairro.isNotBlank()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000000))
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Menó do Movimento",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF44336),
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.align(
                    Alignment.CenterHorizontally
                )
            )

            TextField(
                value = vulgo,
                onValueChange = { vulgo = it },
                label = { Text("Vulgo", color = Color.Black) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White.copy(alpha = 0.9f),
                    unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = crime,
                onValueChange = { crime = it },
                label = { Text("Crime Cometido", color = Color.Black) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White.copy(alpha = 0.9f),
                    unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = timeCoracao,
                onValueChange = { timeCoracao = it },
                label = { Text("Time do Coração", color = Color.Black) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White.copy(alpha = 0.9f),
                    unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                )
            )


            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = qtdPassagens,
                onValueChange = { qtdPassagens = it },
                label = { Text("Quantidade de Passagens", color = Color.Black) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White.copy(alpha = 0.9f),
                    unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = bairro,
                onValueChange = { bairro = it },
                label = { Text("Bairro", color = Color.Black) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White.copy(alpha = 0.9f),
                    unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (isFormValid) {
                        val iconId = Random.nextInt(0, 7)

                        viewModel.addOrUpdateCriminoso(selectedBandidoId, vulgo, qtdPassagens.toIntOrNull() ?: 1, crime, timeCoracao, bairro, iconId)
                        vulgo = ""
                        qtdPassagens = ""
                        crime = ""
                        timeCoracao = ""
                        bairro = ""
                        selectedBandidoId = null
                    }
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336), disabledContainerColor = Color(0xFFF44336).copy(alpha = 0.5f)),
                enabled = isFormValid
            ) {
                Text(if (selectedBandidoId == null) "Adicionar Parça" else "Atualizar Parça", color = Color.White)
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(criminosoList) { criminoso ->
                    CriminosoCard(
                        criminoso = criminoso,
                        onEdit = {
                            vulgo = criminoso.vulgo
                            qtdPassagens = criminoso.qtdPassagens.toString()
                            crime = criminoso.crime
                            timeCoracao = criminoso.timeCoracao
                            bairro = criminoso.bairro
                            selectedBandidoId = criminoso.id
                        },
                        onDelete = { viewModel.deleteBandido(criminoso) }
                    )
                }
            }
        }
    }
}

@Composable
fun CriminosoCard(criminoso: Criminoso, onEdit: () -> Unit, onDelete: () -> Unit) {
    val iconResource = when (criminoso.iconId) {
        0 -> R.drawable.ic_1
        1 -> R.drawable.ic_2
        2 -> R.drawable.ic_3
        3 -> R.drawable.ic_4
        4 -> R.drawable.ic_5
        5 -> R.drawable.ic_6
        6 -> R.drawable.ic_7
        7 -> R.drawable.ic_default
        else -> R.drawable.ic_8
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Ícone de camisa
                Box(
                    modifier = Modifier.size(48.dp),//.padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = iconResource),
                        contentDescription = null,
                        modifier = Modifier.size(500.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = criminoso.vulgo,
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Row {
                IconButton(onClick = onEdit) {
                    Icon(imageVector = Icons.Outlined.Edit, contentDescription = "Editar Jogador")
                }
                IconButton(onClick = onDelete) {
                    Icon(imageVector = Icons.Outlined.Delete, contentDescription = "Excluir Jogador")
                }
            }
        }
    }
}
