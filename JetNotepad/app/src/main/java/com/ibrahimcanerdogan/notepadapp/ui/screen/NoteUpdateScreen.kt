package com.ibrahimcanerdogan.notepadapp.ui.screen

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.ibrahimcanerdogan.notepadapp.data.database.NoteEntity
import com.ibrahimcanerdogan.notepadapp.ui.viewmodel.NoteViewModel
import com.ibrahimcanerdogan.notepadapp.ui.widget.NoteInputText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteUpdateScreen(
    noteEntity: NoteEntity,
    viewModel: NoteViewModel,
    onDismissBottomSheet: () -> Unit
) {

    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismissBottomSheet.invoke() },
        sheetState = sheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() }
    ) {
        UpdateBottomSheetContent(
            noteEntity = noteEntity,
            onNoteUpdate = {
                viewModel.updateNote(it)
                onDismissBottomSheet.invoke()
            }
        )
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun UpdateBottomSheetContent(
    noteEntity: NoteEntity,
    onNoteUpdate: (NoteEntity) -> Unit
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var newTitle by remember { mutableStateOf(noteEntity.noteTitle) }
    var newSubtitle by remember { mutableStateOf(noteEntity.noteSubtitle) }
    var newDescription by remember { mutableStateOf(noteEntity.noteDescription) }
    var newImage by remember { mutableStateOf(noteEntity.noteImagePath) }

    // Permission
    val mediaPermissionState = rememberMultiplePermissionsState(
        permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            listOf(READ_MEDIA_IMAGES, READ_MEDIA_VISUAL_USER_SELECTED)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            listOf(READ_MEDIA_IMAGES)
        } else listOf(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE)
    )

    val hasMediaPermission = mediaPermissionState.allPermissionsGranted

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            val data: Intent? = it.data
            if (data != null) newImage = data.data.toString()
        }
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 10.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        NoteInputText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 10.dp),
            text = newTitle,
            label = "Title",
            onTextChange = {
                if (it.all { char -> char.isLetter() || char.isWhitespace() } && it.length <= 10) {
                    newTitle = it
                }
                if (it.length > 10) {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            },
            maxChar = 10
        )

        NoteInputText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 10.dp),
            text = newSubtitle,
            label = "Subtitle",
            onTextChange = {
                if (it.all { char -> char.isLetter() || char.isWhitespace() } && it.length <= 30) {
                    newSubtitle = it
                }
                if (it.length > 30) {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            }, maxChar = 30
        )

        NoteInputText(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(vertical = 5.dp, horizontal = 10.dp),
            text = newDescription,
            label = "Note Description",
            maxLine = 20,
            onTextChange = {
                if (it.all { char -> char.isLetter() || char.isWhitespace() } && it.length <= 500) {
                    newDescription = it
                }
            },
            maxChar = 500
        )

        if (newImage == null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Image,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = "Image Icon"
                    )
                    Text(
                        text = "./root/noteimage.jpg",
                        modifier = Modifier.padding(horizontal = 10.dp),
                        style = MaterialTheme.typography.labelLarge,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

                IconButton(onClick = {
                    if (hasMediaPermission) {
                        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        galleryLauncher.launch(intent)
                    } else {
                        mediaPermissionState.launchMultiplePermissionRequest()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.FileUpload,
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.surface)
                            .clip(CircleShape)
                            .padding(8.dp),
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = "Image Upload Icon"
                    )
                }
            }
        }
        else {
            AsyncImage(
                model = Uri.parse(newImage!!),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .clickable {
                        newImage = null
                    },
                contentDescription = "Uploaded Image!"
            )
        }

        IconButton(
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp)
                .height(60.dp)
                .clip(CircleShape)
                .background(Color.Black)
                .align(Alignment.End),
            onClick = {
                if (newTitle.isNotEmpty() && newSubtitle.isNotEmpty() && newDescription.isNotEmpty()) {
                    val newUpdatedNote = NoteEntity(
                        noteID = noteEntity.noteID,
                        noteTitle = newTitle,
                        noteSubtitle = newSubtitle,
                        noteDescription = newDescription,
                        noteImagePath = newImage
                    )
                    onNoteUpdate.invoke(newUpdatedNote)
                    Toast.makeText(context, "Note Updated!", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.Save,
                tint = Color.White,
                contentDescription = "Update Note Save Icon"
            )
        }
    }
}
