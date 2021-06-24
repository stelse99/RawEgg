package com.example.rawegg.views

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.rawegg.utils.RandomUser

@Composable
fun RandomUserListView(randomUsers: List<RandomUser>){
    // 메모리 관리가 들어간 LazyColumn
    LazyColumn{
//        items(randomUsers){ aRandomUser ->
//            RandomUserView(aRandomUser)
//        }
        items(randomUsers){ RandomUserView(it) }
    }
}

@Composable
fun RandomUserView(randomUser: RandomUser){
    val typography = MaterialTheme.typography
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
//            Box(modifier =
//                Modifier
//                    .size(width = 60.dp, height = 60.dp)
//                    .clip(CircleShape)
//                    .background(Color.Red)
//            )
            ProfileImg(imgUrl = randomUser.profileImage)
            Column{
                Text(text = randomUser.name,
                    style = typography.subtitle1)
                Text(text = randomUser.description,
                    style = typography.body1)
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun ProfileImg(imgUrl: String, modifier: Modifier = Modifier){
    // 이미지 비트맵
    val bitmap : MutableState<Bitmap?> = mutableStateOf(null)

    // 이미지 모디파이어
    val imageModifier = modifier
        .size(50.dp, 50.dp)
//        .clip(RoundedCornerShape(10.dp))
        .clip(CircleShape)

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(imgUrl)
        .into(object : CustomTarget<Bitmap>(){
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                bitmap.value = resource
            }
            override fun onLoadCleared(
                placeholder: Drawable?
            ) { }
        })

    // 비트 맵이 있다면
    bitmap.value?.asImageBitmap()?.let { fetchedBitmap ->
        Image(
            bitmap = fetchedBitmap,
            contentScale = ContentScale.Fit,
            contentDescription = null,
            modifier = imageModifier
        )
    } ?: Image(
            painter = painterResource(id = R.drawable.ic_empty_user_img),
            contentScale = ContentScale.Fit,
            contentDescription = null,
            modifier = imageModifier
        )
}
