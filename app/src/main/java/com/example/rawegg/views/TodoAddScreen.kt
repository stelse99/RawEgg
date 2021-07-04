package com.example.rawegg.views

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
        modifier = Modifier
            .fillMaxHeight(.9f)
    ) {
        InputFieldState(inputViewModel)
    }
}

@Composable
fun InputFieldState(inputViewModel: InputViewModel) {
    val todo: String by inputViewModel.todo.observeAsState("")

    Column(
        modifier = Modifier.padding(16.dp)
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
            placeholder = { Text(text = "Enter todo") },
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth(),
            onValueChange = onValChange,
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
        text = { Text("Save Todo") },
        onClick = onClick,
        elevation = FloatingActionButtonDefaults.elevation(8.dp)
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

