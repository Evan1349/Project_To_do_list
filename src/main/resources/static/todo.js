const newTodoInput = document.getElementById('newTodo');
const addTodoButton = document.getElementById('addTodo');
const todoList = document.getElementById('todoList');
const titleElement = document.querySelector("h1");
const urlParams = new URLSearchParams(window.location.search);
const userName = urlParams.get('username');

// API 端點
const apiUrl = 'http://localhost:8080/api/tasks';

// 取得所有 Todo
function getTodos() {
  fetch(apiUrl + `/byUser/${userName}`)
    .then(response => response.json())
    .then(data => {
      renderTodos(data);
    })
    .catch(error => console.error('Error:', error));
}

// 渲染 Todo 列表
function renderTodos(todos) {
	console.log(todos);
  todoList.innerHTML = '';
  todos.forEach(todo => {
    const li = document.createElement('li');
    li.classList.toggle('completed', todo.completed);

    const checkbox = document.createElement('input');
    checkbox.type = 'checkbox';
    checkbox.checked = todo.completed;
    checkbox.addEventListener('change', () => {
      toggleTodo(todo.taskId, !todo.completed);
    });

    const todoText = document.createElement('span');
    todoText.textContent = todo.taskName;
/*
    const editButton = document.createElement('button');
    editButton.textContent = 'Edit';
    editButton.addEventListener('click', () => {
      editTodo(todo.taskId);
    });
*/
    const deleteButton = document.createElement('button');
    deleteButton.textContent = 'Delete';
    deleteButton.addEventListener('click', () => {
      deleteTodo(todo.taskId);
    });

    li.appendChild(checkbox);
    li.appendChild(todoText);
    li.appendChild(deleteButton);
    todoList.appendChild(li);
  });
}

// 新增 Todo
function addTodo() {
  const taskName = newTodoInput.value.trim();
  if (taskName) {
    
    fetch(apiUrl+`/createTask/${userName}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ taskName, completed: false })
    })
      .then(response => response.json())
      .then(data => {
        newTodoInput.value = '';
        getTodos();
      })
      .catch(error => console.error('Error:', error));
  }
}

// 切換 Todo 狀態
function toggleTodo(taskId, completed) {
  fetch(apiUrl+`/Completed/${taskId}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({ completed })
  })
    .then(response => response.json())
    .then(data => {
      getTodos();
    })
    .catch(error => console.error('Error:', error));
}

// 編輯 Todo
function editTodo(taskId) {
  // 實作編輯 Todo 的功能
}

// 刪除 Todo
function deleteTodo(taskId) {
  fetch(apiUrl+`/deleteTask/${taskId}`, {
    method: 'DELETE'
  })
    .then(response => {
      if (response.status === 204){
		return Promise.resolve([]);
	  }else{
		return response.json();
	  }
    })
    .then(data => {
      getTodos();
    })
    .catch(error => console.error('Error:', error));
}

// 初始化
getTodos();

if (userName) {
  titleElement.textContent += `-  ${userName}'s Todo List  - `;
}

addTodoButton.addEventListener('click', addTodo);