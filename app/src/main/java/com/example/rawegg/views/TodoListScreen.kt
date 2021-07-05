package com.example.rawegg.views

import android.R
import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.rawegg.models.database.TodoItem
import com.example.rawegg.viewModels.TodoViewModel
import com.example.rawegg.viewModels.TodoViewModelFactory


@ExperimentalMaterialApi
@Composable
fun TodoHomeView(navController: NavController) {
    val context = LocalContext.current
    val todoViewModel: TodoViewModel = viewModel(
        factory = TodoViewModelFactory(context.applicationContext as Application)
    )

    val items = todoViewModel.readAllData.observeAsState(listOf()).value

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.Black)
    ) {
        Text(
            text = "My ToDo List",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        CustomCardState(
            navController = navController,
            todoViewModel = todoViewModel
        )
        TodoList(
            list = items,
            todoViewModel = todoViewModel
        )
        Spacer(modifier = Modifier.padding(top = 32.dp))
    }
}


@ExperimentalMaterialApi
@Composable
fun TodoList(
    list: List<TodoItem>,
    todoViewModel: TodoViewModel
) {
    val context = LocalContext.current

    LazyColumn {
        items(list) { todo ->
            val name = rememberSaveable { mutableStateOf(todo.isDone) }

            ListItem(
                text = { Text(text = todo.itemName) },
                icon = {
                    IconButton(
                        onClick = {
                            todoViewModel.deleteTodo(todo)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null,
                            tint = Color.Gray,
                        )
                    }
                },
                trailing = {
                    Checkbox(
                        checked = name.value,
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color.Gray,
                            uncheckedColor = Color.Gray,
                            checkmarkColor = Color.White
                        ),
                        onCheckedChange = {
                            name.value = it
                            todo.isDone = name.value
                            todoViewModel.updateTodo(todo)

                            Toast.makeText(context, "Updated todo!", Toast.LENGTH_SHORT).show()
                        },
                    )
                }
            )
            Divider()
        }
    }
}

@Composable
fun CustomCardState(
    navController: NavController,
    todoViewModel: TodoViewModel
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xff24191c),
                    contentColor = Color.White,
                ) ,
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 20.dp,
                    disabledElevation = 5.dp
                ),
                shape = RoundedCornerShape(12.dp),
                onClick = { navController.navigate("todo_add") }
            ) {
                Text(
                    text = "Add Todo",
                    fontSize = 20.sp,
                )
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xff24191c),
                    contentColor = Color.White
                ) ,
                onClick = { todoViewModel.deleteAllTodos() }
            ) {
                Text(
                    text = "Clear all",
                    fontSize = 20.sp,
                )
            }
        }
    }
}
