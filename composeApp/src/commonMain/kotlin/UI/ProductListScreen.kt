package UI

import Model.Product
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import presentation.ProductViewModel

@Composable
fun ProductListScreen(vm: ProductViewModel, onProductClick: (Product)->Unit = {}) {
    val state by vm.state.collectAsState()
    var query by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(8.dp)) {
        Row {
            OutlinedTextField(value = query, onValueChange = { query = it }, modifier = Modifier.weight(1f))
            Spacer(Modifier.width(8.dp))
            Button(onClick = { vm.search(query) }) { Text("Search") }
        }

        Spacer(Modifier.height(8.dp))

        when {
            state.loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
            state.error != null -> Text("Error: ${state.error}")
            else -> LazyColumn {
                items(state.products) { p ->
                    ProductRow(p, onClick = { onProductClick(p) })
                }
            }
        }
    }
}

// ProductRow unchanged, ensure clickable triggers
@Composable
fun ProductRow(product: Product, onClick: ()->Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)
        .clickable {
            println("ProductRow clicked id=${product.id}")
            onClick()
        }) {
        Row(Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            KamelImage(
                resource = asyncPainterResource(product.thumbnail ?: ""),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
            Spacer(Modifier.width(8.dp))
            Column {
                Text(product.title, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text("${product.brand ?: ""} • $${product.price}")
                Text("Rating: ${product.rating}")
            }
        }
    }
}
