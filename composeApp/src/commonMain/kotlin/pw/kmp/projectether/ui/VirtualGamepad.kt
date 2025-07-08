package pw.kmp.projectether.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pw.kmp.projectether.model.data.Direction


@Composable
fun VirtualGamepad(
    onMove: (Direction) -> Unit,
    onJump: () -> Unit,
    onRespawn: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        content()

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { onMove(Direction.UP) }) { Text("↑") }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { onMove(Direction.LEFT) }) { Text("←") }
                Button(onClick = { /* TODO: implement slavic crouch. #7*/ }) { Text("Slavic\nCrouch") }
                Button(onClick = { onMove(Direction.RIGHT) }) { Text("→") }
            }
            Button(onClick = { onMove(Direction.DOWN) }) { Text("↓") }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(48.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onRespawn,
                modifier = Modifier
                    .width(100.dp)
                    .height(42.dp)
            ) {
                Text("Resp")
            }
            Button(
                onClick = onJump,
                modifier = Modifier
                    .width(120.dp)
                    .height(48.dp)
            ) {
                Text("Jump")
            }
        }
    }
}
