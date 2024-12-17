package com.example.takego


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

// Data model untuk transaksi
data class Transaction(val title: String, val amount: String, val date: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Aktvitas(navController: NavHostController) {
    val transactions = remember {
        listOf(
            Transaction("Beli Mie Gacoan", "Rp 50.000", "12 Nov 2023"),
            Transaction("Beli Siomay", "Rp 30.000", "13 Nov 2023"),
            Transaction("Beli Barang", "Rp 100.000", "15 Nov 2023"),
            Transaction("Beli Capcay", "Rp 50.000", "12 Dec 2023"),
            Transaction("Beli Mie Gacoan", "Rp 50.000", "15 Dec 2023"),
            Transaction("Beli Siomay", "Rp 30.000", "16 Dec 2023"),
            Transaction("Beli Barang", "Rp 100.000", "17 Dec 2023"),
            Transaction("Beli Capcay", "Rp 50.000", "19 Dec 2023"),
            Transaction("Beli Mie Gacoan", "Rp 50.000", "21 Dec 2023"),
            Transaction("Beli Siomay", "Rp 30.000", "22 Dec 2023"),
            Transaction("Beli Barang", "Rp 100.000", "1 Jan 2024"),
            Transaction("Beli Capcay", "Rp 50.000", "12 Jan 2024"),
            Transaction("Beli Mie Gacoan", "Rp 50.000", "14 Jan 2024"),
            Transaction("Beli Siomay", "Rp 30.000", "15 Apr 2024"),
            Transaction("Beli Barang", "Rp 100.000", "8 Mei 2024"),
            Transaction("Beli Capcay", "Rp 50.000", "12 Jun 2024")
        )
    }

    var selectedTransaction by remember { mutableStateOf<Transaction?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Aktivitas", color = Color.White, style = TextStyle(fontSize = 20.sp))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF4CAF50))
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                Text(
                    text = "Riwayat Transaksi",
                    style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black),
                    modifier = Modifier.padding(16.dp)
                )
            }

            items(transactions) { transaction ->
                TransactionItem(
                    transaction = transaction,
                    isSelected = transaction == selectedTransaction,
                    onClick = {
                        selectedTransaction = if (selectedTransaction == transaction) null else transaction
                    }
                )
            }
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.transc),
                    contentDescription = "Transaction Icon",
                    modifier = Modifier.size(40.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = transaction.title,
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                    )
                    Text(
                        text = transaction.date,
                        style = TextStyle(fontSize = 14.sp, color = Color.Gray)
                    )
                }

                Text(
                    text = transaction.amount,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4CAF50))
                )
            }

            if (isSelected) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Detail Pembelian Layanan:\nPembelian pada ${transaction.date},\n" +
                            "Pembayaran menggunaakn cash",

                    style = TextStyle(fontSize = 14.sp, color = Color.Black)
                )
            }
        }
    }
}

