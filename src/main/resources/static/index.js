const loginForm = document.querySelector('#login-form');
const usernameInput = document.querySelector('#username');
const passwordInput = document.querySelector('#password');
const errorMessage = document.querySelector('#error-message');

loginForm.addEventListener('submit', async (e) => {
  e.preventDefault();

  if (!usernameInput.value || !passwordInput.value) {
    errorMessage.textContent = '请确保您已输入用户名和密码';
    return;
  }

  try {
    const response = await fetch('/api/users/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        username: usernameInput.value,
        password: passwordInput.value
      })
    });

    if (response.ok) {
      const userData = await response.json(); // 解析返回的 JSON 数据
      const userName = userData.userName; // 获取用户名

      // 重定向到 todo.html 页面，并附带用户名作为查询参数
      window.location.href = `/todo.html?username=${userName}`;
    } else {
      errorMessage.textContent = '用户名或密码错误';
    }
  } catch (error) {
    errorMessage.textContent = '出现错误：' + error.message;
  }
});
