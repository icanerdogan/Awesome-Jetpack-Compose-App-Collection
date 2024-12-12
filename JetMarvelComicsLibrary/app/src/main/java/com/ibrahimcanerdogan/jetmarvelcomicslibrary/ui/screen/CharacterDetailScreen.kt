package com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.BookmarkRemove
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.R
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.component.CharacterDetailImage
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.navigation.Destination
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.theme.GrayTransparentBackground
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.viewmodel.CharacterListViewModel
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.viewmodel.LibraryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    navController: NavHostController,
    characterListViewModel: CharacterListViewModel,
    libraryViewModel: LibraryViewModel
) {
    val characterDetail = characterListViewModel.characterDetails.value

    val collection by libraryViewModel.collection.collectAsState()
    val inCollection = collection.map { it.characterId }.contains(characterDetail?.resultId)

    if (characterDetail == null) {
        navController.navigate(Destination.CharacterList.route) {
            popUpTo(Destination.CharacterList.route)
            launchSingleTop = true
        }
    }

    LaunchedEffect(key1 = Unit) {
        libraryViewModel.setCurrentCharacterId(characterDetail?.resultId)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        characterDetail?.resultName ?: "No name",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Destination.CharacterList.route) {
                            popUpTo(Destination.CharacterList.route)
                            launchSingleTop = true
                        }
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        if (!inCollection && characterDetail != null) libraryViewModel.addCharacter(characterDetail)
                    }) {
                        if (!inCollection) Icon(Icons.Default.BookmarkAdd, contentDescription = null)
                        else Icon(Icons.Default.BookmarkRemove, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val imageUrl = characterDetail?.resultThumbnail?.path + "." + characterDetail?.resultThumbnail?.extension
                val comics = characterDetail?.resultComics?.items
                val description = characterDetail?.resultDescription

                CharacterDetailImage(imageUrl)

                description?.let {
                    Text(
                        text = description,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp)
                    )
                }

                if (!comics.isNullOrEmpty()) {
                ExpandableSection(title = stringResource(id = R.string.comics)){
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        ) {
                            items(comics) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = it.name ?: "",
                                        fontStyle = FontStyle.Italic,
                                        fontSize = 12.sp,
                                        modifier = Modifier.padding(4.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ExpandableSection(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable () -> Unit
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = modifier
            .clickable { isExpanded = !isExpanded }
            .background(color = GrayTransparentBackground)
            .fillMaxWidth()
    ) {
        ExpandableSectionTitle(isExpanded = isExpanded, title = title)

        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = isExpanded
        ) {
            content()
        }
    }
}

@Composable
fun ExpandableSectionTitle(modifier: Modifier = Modifier, isExpanded: Boolean, title: String) {

    val icon = if (isExpanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown

    Row(modifier = modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(28.dp),
            imageVector = icon,
            contentDescription = null
        )
        Text(text = title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Medium)
    }
}

