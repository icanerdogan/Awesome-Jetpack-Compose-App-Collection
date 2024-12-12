package com.ibrahimcanerdogan.notepadapp.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibrahimcanerdogan.notepadapp.data.database.NoteEntity
import com.ibrahimcanerdogan.notepadapp.ui.screen.NoteUpdateScreen
import com.ibrahimcanerdogan.notepadapp.ui.viewmodel.NoteViewModel
import com.ibrahimcanerdogan.notepadapp.ui.widget.DeleteAlertDialog

@Composable
fun NoteListView(
    noteList: List<NoteEntity>,
    viewModel: NoteViewModel
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        reverseLayout = false
    ) {
       items(noteList) { note ->
           NoteRow(
               noteEntity = note,
               viewModel = viewModel
           )
       }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    noteEntity: NoteEntity,
    viewModel: NoteViewModel
) {
    // Delete Alert Dialog
    var showAlertDialog by remember { mutableStateOf(false) }
    if (showAlertDialog) {
        DeleteAlertDialog(
            onDismissRequest = { showAlertDialog = false },
            onConfirmation = { viewModel.removeNote(noteEntity) },
            dialogTitle = "Sil",
            dialogText = "${noteEntity.noteTitle} başlıklı not silinecektir. Notu silmek istediğinize emin misiniz?"
        )
    }

    // Update Bottom Sheet
    var showBottomSheet by remember { mutableStateOf(false) }
    if (showBottomSheet) {
       NoteUpdateScreen(noteEntity = noteEntity, viewModel = viewModel) {
           showBottomSheet = false
       }
    }

    Card(
        modifier = modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(15.dp))
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .combinedClickable(
                    onClick = {},
                    onLongClick = {
                        showBottomSheet = true
                    }
                )
                .padding(vertical = 8.dp, horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(0.8F),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = noteEntity.noteTitle,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black
                )
                Text(
                    text = noteEntity.noteSubtitle,
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    text = noteEntity.noteEntryDate,
                    modifier = Modifier.padding(horizontal = 1.dp, vertical = 3.dp),
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 10.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.LightGray
                )
            }

            Column(
                modifier = Modifier.weight(0.2F),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                IconButton(onClick = { showAlertDialog = true }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Note Icon"
                    )
                }
            }

        }
    }

}