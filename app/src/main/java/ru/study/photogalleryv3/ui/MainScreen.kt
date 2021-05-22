package ru.study.photogalleryv3.ui

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.squareup.picasso.Picasso
import ru.study.photogalleryv3.model.Photo
import java.lang.Exception

@Composable
fun MainScreen(viewModel: MainViewModel = MainViewModel()) {
    val posts by viewModel.observablePosts().collectAsState(listOf())
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button { viewModel.updatePosts() }
        ItemList(posts = posts)
    }
}

@Composable
fun Button(onUpdate: () -> Unit) {
    Button(
        onClick = onUpdate,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Update"
        )
    }
}

@Composable
fun ItemList(posts: List<Photo>) {
    LazyColumn {
        items(posts) {
            Card(desc = it.caption, urlImage = it.url)
        }
    }
}

@Composable
fun Card(desc: String, urlImage: String?) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .height(150.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            NetworkImage(url = urlImage)
            Text(
                text = desc,
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    }
}

@Composable
fun NetworkImage(url: String?) {
    var image by remember { mutableStateOf<Bitmap?>(null) }

    val target = object : com.squareup.picasso.Target {
        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            image = bitmap
        }

        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}
        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
    }

    Picasso.get()
        .load(url)
        .error(android.R.drawable.stat_notify_error)
        .placeholder(android.R.drawable.stat_notify_error)
        .into(target)

    if (image != null) {
        Image(
            modifier = Modifier
                //.size(120.dp)
                .padding(start = 16.dp, end = 8.dp)
                .height(120.dp)
                .width(120.dp),
            bitmap = image!!.asImageBitmap(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    } else {
        Box(
            modifier = Modifier
                //.size(120.dp)
                .padding(start = 16.dp, end = 8.dp)
                .height(120.dp)
                .width(120.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}