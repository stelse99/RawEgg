package com.example.rawegg.views

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.rawegg.models.database.TodoItem
import com.example.rawegg.viewModels.InputViewModel
import com.example.rawegg.viewModels.TodoViewModel
import com.example.rawegg.viewModels.TodoViewModelFactory


@Composable
fun TodoAddView(navController: NavController) {
    val inputViewModel = InputViewModel()
    val context = LocalContext.current
    val todoViewModel: TodoViewModel = viewModel(
        factory = TodoViewModelFactory(
            context.applicationContext as Application
        )
    )

    Scaffold(
        floatingActionButton = {
            ExtendedFAB {
                insertTodoInDB(
                    inputViewModel.todo.value.toString(),
                    todoViewModel
                )
                Toast.makeText(context, "Added Todo", Toast.LENGTH_SHORT).show()
                navController.navigate("todo_home")
            }

        },
        floatingActionButtonPosition = FabPosition.End,
        // 2021-07-06 suchan Main bottomBar 에 가려져서 임의로 추가 한 것임.
        bottomBar = {
            BottomAppBar(
                backgroundColor = Color.Black
            ) {
                Text("")
            }
        }
    ) {
        InputFieldState(inputViewModel)
    }
}

@Composable
fun InputFieldState(inputViewModel: InputViewModel) {
    val todo: String by inputViewModel.todo.observeAsState("")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.Black)
    ) {
        InputField(todo) {
            inputViewModel.onInputChange(it)
        }
        Spacer(modifier = Modifier.padding(10.dp))
    }
}

@Composable
fun InputField(
    name: String,
    onValChange: ((String) -> Unit)?
) {
    val focusManager = LocalFocusManager.current

    if (onValChange != null) {
        TextField(
            value = name,
            placeholder = {
                Text(text = "Enter todo")
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color(0xff24191c),
                    RoundedCornerShape(12.dp)
                )
                .padding(10.dp),
            textStyle = TextStyle(
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Light,
            ),
            onValueChange = onValChange,
            maxLines = 1,
            singleLine = true,
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
        )
    }
}

@Composable
fun ExtendedFAB(onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        text = {
            Text(
                text = "Save Todo",
                modifier = Modifier
                    .background(Color(0xff24191c)),
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Light
                ),
            )
        },
        backgroundColor = Color(0xff24191c),
        onClick = onClick,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 10.dp,
            pressedElevation = 20.dp,
        ),
        icon = {
            Icon(
                Icons.Filled.Add,"",
                tint = Color.White
            )
        }
    )
}

fun insertTodoInDB(todo: String, todoViewModel: TodoViewModel) {
    if (todo.isNotEmpty()) {
        val todoItem = TodoItem(
            itemName = todo,
            isDone = false
        )

        todoViewModel.addTodo(todoItem)
    }
}

