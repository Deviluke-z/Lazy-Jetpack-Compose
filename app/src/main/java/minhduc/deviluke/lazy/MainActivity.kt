package minhduc.deviluke.lazy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import minhduc.deviluke.lazy.data.MarsPhoto
import minhduc.deviluke.lazy.ui.theme.LazyTheme
import minhduc.deviluke.lazy.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

                    DisplayListItem(marsPhotoList = mainViewModel.dataListResponse)
                    mainViewModel.getData()
                }
            }
        }
    }
}

@Composable
fun DisplayItem(marsPhoto: MarsPhoto) {
    Box(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp)
    ) {
        Image(
            painter = rememberImagePainter(
                data = marsPhoto.image,
                builder = {
                    scale(Scale.FILL)
                    placeholder(R.drawable.ic_launcher_foreground)
                    transformations(CircleCropTransformation())
                }),
            contentDescription = "photo",
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.Center)
        )
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxHeight()
        ) {
            Text(
                text = marsPhoto.name,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = marsPhoto.species,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .background(
                        Color.LightGray
                    )
                    .padding(4.dp)
            )
            Text(
                text = marsPhoto.dateOfBirth,
                style = MaterialTheme.typography.body1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

// not success yet in demo lazy loading
@Composable
fun DisplayListItem(marsPhotoList: List<MarsPhoto>) {
    val listState = rememberLazyListState()
    LazyColumn(state = listState) {
        itemsIndexed(
            items = marsPhotoList
            // items = (1..10) in marsPhotoList
        ) { _, item ->
            DisplayItem(marsPhoto = item)
        }

        /*
        * if (listState.firstVisibleItemIndex < 10) {
        *   // do not load other items
        * } else {
        *   // load next 10 items
        * }
         */

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LazyTheme {
    }
}