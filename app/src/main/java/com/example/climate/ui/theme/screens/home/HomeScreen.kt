package com.example.climate.ui.theme.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.climate.R


@Composable
fun HomeScreen(navController: NavController, posts: List<Post>,onPostClick: (Post) -> Unit){
    LazyColumn {
        items(posts) { post ->
            PostItem(post = post, onPostClick = onPostClick)
        }
    }
}
@Composable
fun PostItem(post: Post,onPostClick: (Post) -> Unit){
    Surface(color = MaterialTheme.colorScheme.background) {
        Box (modifier = Modifier.fillMaxSize()){
            Image(painter = painterResource(id = R.drawable.nature), contentDescription = "home background")
            Column (modifier = Modifier
                .padding(16.dp)
                .clickable { onPostClick(post) }
            ){
                androidx.compose.material3.Text(text = "${post.userName},${post.userProfession}")
                Spacer(modifier = Modifier.height(8.dp))
                androidx.compose.material3.Text(text = post.text, maxLines = 2, overflow = TextOverflow.Ellipsis)
                post.imageUri?.let { imageUri ->
                    Image(
                        painter = rememberImagePainter(imageUri),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(shape = RoundedCornerShape(8.dp)))
//                    contentScale = ContentScale.Crop

                }
            }
        }
    }
}