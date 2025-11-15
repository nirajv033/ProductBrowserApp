package UI

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun ExitDialog(
    onYes: () -> Unit,
    onNo: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onNo,
        title = { Text("Exit org.example.project.App") },
        text = { Text("Do you want to exit the app?") },
        confirmButton = {
            Button(onClick = onYes) { Text("Yes") }
        },
        dismissButton = {
            Button(onClick = onNo) { Text("No") }
        }
    )
}