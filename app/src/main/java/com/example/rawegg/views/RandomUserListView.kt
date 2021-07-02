package com.example.rawegg.views

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.rawegg.utils.RandomUser
import com.example.rawegg.R
import timber.log.Timber

@Composable
fun RandomUserListView (
    randomUsers: List<RandomUser>
) {
    // 메모리 관리가 들어간 LazyColumn
    LazyColumn {
//        items(randomUsers){ aRandomUser ->
//            RandomUserView(aRandomUser)
//        }
        items(randomUsers){
            RandomUserView(it)
        }
    }
}

@Composable
fun RandomUserView (
    randomUser: RandomUser
) {
    val typography = MaterialTheme.typography

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(10.dp),
        elevation = 30.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row (
           modifier = Modifier
               .background(Color.Black)
               .padding(10.dp),
           verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
//            Box(modifier =
//                Modifier
//                    .size(width = 60.dp, height = 60.dp)
//                    .clip(CircleShape)
//                    .background(Color.Red)
//            )
            ProfileImg (imgUrl = randomUser.profileImage)
            Column {
                Text (
                    text = randomUser.name,
                    style = typography.body1
                )
                Text (
                    text = randomUser.description,
                    style = typography.subtitle1
                )
            }
        }
    }
}


@SuppressLint("UnrememberedMutableState")
@Composable
fun ProfileImg (
    imgUrl: String,
    modifier: Modifier = Modifier
) {
    val bitmap : MutableState<Bitmap?> = mutableStateOf(null)

    val imageModifier = modifier
        .size(50.dp, 50.dp)
//        .clip(RoundedCornerShape(10.dp))
        .clip(CircleShape)

    //Toast.makeText(LocalContext.current, "안녕", Toast.LENGTH_SHORT).show()
    //Log.d(TAG,"ProfileImg::Glide")

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(imgUrl)
        .into(
            object : CustomTarget<Bitmap>() {
                override fun onResourceReady (
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    Timber.i("로그::Glide Called")
                    bitmap.value = resource
                }
                override fun onLoadCleared (
                    placeholder: Drawable?
                ) { }
           }
        )
    // 2021.06.25 suchang If it's not working you should check INTERNET Permition.
    // 2021.06.25 suchang How find R.drawable at the sub packages!!!
    //            import import com.example.rawegg.R
    Timber.i("로그::Glide Called $bitmap")
    bitmap.value?.asImageBitmap()?.let { fetchedBitmap ->
        Image (
            bitmap = fetchedBitmap,
            contentScale = ContentScale.Fit,
            contentDescription = null,
            modifier = imageModifier
        )
    } ?: Image (
        painter = painterResource(id = R.drawable.ic_empty_user_img),
        contentScale = ContentScale.Fit,
        contentDescription = null,
        modifier = imageModifier
    )
}
