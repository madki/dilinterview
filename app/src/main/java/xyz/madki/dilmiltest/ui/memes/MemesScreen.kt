package xyz.madki.dilmiltest.ui.memes

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import xyz.madki.dilmiltest.R
import xyz.madki.dilmiltest.db.model.Meme
import xyz.madki.dilmiltest.domain.GetMemesUseCase
import xyz.madki.dilmiltest.repos.RequestStatus

@ExperimentalCoilApi
@Composable
fun MemesScreen(
    viewModel: MemesViewModel
) {
    val state: State<GetMemesUseCase.GetMemeResult> = viewModel.memes.collectAsState()
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MemesAppBar()
        },
    ) {
        Box {
            MemesList(memes = state.value.memes)
            if (state.value.requestStatus == RequestStatus.LOADING)
                LoadingBar()
        }
    }
}

@Composable
fun MemesList(
    memes: List<Meme>
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(items = memes, key = { item: Meme -> item.id }) {
            MemeItem(meme = it)
        }
    }
}

@Composable
fun MemeItem(
    meme: Meme
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.secondary,
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Row(modifier = Modifier.animateContentSize()) {
            Box(modifier = Modifier.align(alignment = Alignment.CenterVertically)) {
                MemeImage(meme.url, meme.height, meme.width)
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun MemeImage(
    thumbnailUrl: String,
    height: Int,
    width: Int
) {
    val painter = rememberImagePainter(
        data = thumbnailUrl,
        builder = {
            size(OriginalSize)
        }
    )
    Box(
        modifier = Modifier
            .aspectRatio(width.toFloat() / height.toFloat())
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp, end = 16.dp)
            .background(MaterialTheme.colors.secondary, shape = RoundedCornerShape(0.dp))
    )
    Image(
        painter = painter,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp, end = 16.dp),
        contentDescription = "Food item thumbnail picture",
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun LoadingBar() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun MemesAppBar() {
    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Home,
                modifier = Modifier.padding(horizontal = 12.dp),
                contentDescription = "Action icon"
            )
        },
        title = { Text(stringResource(R.string.app_name)) },
        backgroundColor = MaterialTheme.colors.background
    )
}