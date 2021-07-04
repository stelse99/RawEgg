package com.example.rawegg.views

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
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
        modifier = Modifier.padding(16.dp)
    ) {
        Text("My ToDo List")
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        CustomCardState(navController, todoViewModel)
        TodoList(list = items, todoViewModel = todoViewModel)
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
                    IconButton(onClick = {
                        todoViewModel.deleteTodo(todo)
                    }) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = null
                        )
                    }
                },
                trailing = {
                    Checkbox(
                        checked = name.value,
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
            Button(onClick = { navController.navigate("todo_add") }) {
                Text(text = "Add Todo")
            }
            Button(onClick = { todoViewModel.deleteAllTodos() }) {
                Text(text = "Clear all")
            }
        }
    }
}
