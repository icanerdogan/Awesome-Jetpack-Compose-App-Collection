package com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.R
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.database.model.NoteDBModel
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.model.NoteResult
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.component.EmptyListIcon
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.theme.GrayBackground
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.theme.GrayTransparentBackground
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.viewmodel.LibraryViewModel

@Composable
fun LibraryScreen(
    libraryViewModel: LibraryViewModel
) {
    val charactersInCollection = libraryViewModel.collection.collectAsState()
    val notes = libraryViewModel.notes.collectAsState()

    var expandedElement by remember { mutableIntStateOf(-1) }


    Scaffold { paddingValues ->
        if (charactersInCollection.value.isEmpty()) {
            EmptyListIcon(R.drawable.icon_book)
        }

        LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            items(charactersInCollection.value) { character ->
                Card(
                    modifier = Modifier.fillMaxWidth().height(150.dp).background(GrayTransparentBackground).padding(10.dp).clickable {
                        expandedElement = if (expandedElement == character.modelId) {
                            -1
                        } else {
                            character.modelId
                        }
                    }
                ) {
                    Row {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(10.dp)
                                .fillMaxHeight()
                        ) {
                            Text(
                                modifier = Modifier.padding(bottom = 10.dp),
                                text = character.characterName ?: "No name",
                                maxLines = 2,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = Color.Black
                                )
                            )
                            Text(
                                text = character.characterComics ?: "",
                                maxLines = 3,
                                style = TextStyle(
                                    fontWeight = FontWeight.Normal,
                                    fontStyle = FontStyle.Italic,
                                    fontSize = 14.sp,
                                    color = Color.DarkGray
                                )
                            )
                        }

                        Column(
                            modifier = Modifier
                                .wrapContentWidth()
                                .fillMaxHeight()
                                .padding(10.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                Icons.Outlined.Delete,
                                contentDescription = null,
                                modifier = Modifier.clickable {
                                    libraryViewModel.deleteCharacter(character)
                                }
                            )

                            if (character.modelId == expandedElement)
                                Icon(Icons.Outlined.KeyboardArrowUp, contentDescription = null)
                            else
                                Icon(Icons.Outlined.KeyboardArrowDown, contentDescription = null)
                        }
                    }
                }

                if (character.modelId == expandedElement) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(GrayTransparentBackground)
                    ) {
                        val filteredNotes = notes.value.filter { note ->
                            note.noteId == character.modelId
                        }
                        NotesList(filteredNotes, libraryViewModel)
                        CreateNoteForm(character.modelId, libraryViewModel)
                    }
                }

                HorizontalDivider(
                    color = Color.LightGray,
                    modifier = Modifier.padding(
                        top = 4.dp, bottom = 4.dp, start = 20.dp, end = 20.dp
                    )
                )
            }
        }
    }
}

@Composable
fun NotesList(
    listNote: List<NoteDBModel>,
    libraryViewModel: LibraryViewModel
) {
    for (note in listNote) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(GrayBackground)
                .padding(4.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = note.noteTitle, fontWeight = FontWeight.Bold)
                Text(text = note.noteText)
            }
            Icon(
                Icons.Outlined.Delete,
                contentDescription = null,
                modifier = Modifier.clickable {
                    libraryViewModel.deleteNote(note)
                })
        }
    }
}

@Composable
fun CreateNoteForm(
    characterId: Int,
    libraryViewModel: LibraryViewModel
) {
    var addNoteToElement by remember { mutableIntStateOf(-1) }
    var newNoteTitle by remember { mutableStateOf("") }
    var newNoteText by remember { mutableStateOf("") }

    if (addNoteToElement == characterId) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 15.dp)
        ) {
            Text(text = "Add Note", fontWeight = FontWeight.Bold)
            OutlinedTextField(
                value = newNoteTitle,
                onValueChange = { newNoteTitle = it },
                label = { Text(text = "Note Title") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = newNoteText,
                    onValueChange = { newNoteText = it },
                    label = { Text(text = "Note Content") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )

                if (newNoteTitle.isNotEmpty() && newNoteText.isNotEmpty()) {
                    Button(onClick = {
                        val noteResult = NoteResult(characterId, newNoteTitle, newNoteText)
                        libraryViewModel.addNote(noteResult)
                        newNoteTitle = ""
                        newNoteText = ""
                        addNoteToElement = -1
                    }) {
                        Icon(Icons.Default.Check, contentDescription = null)
                    }
                }

            }
        }
    }

    Button(modifier = Modifier.padding(top = 10.dp), onClick = { addNoteToElement = characterId }) {
        Icon(Icons.Default.Add, contentDescription = null)
    }
}
