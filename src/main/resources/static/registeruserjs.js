async function register() {
    const username = document.getElementById('username').value
    const password = document.getElementById('password').value
    const phone = document.getElementById('phno').value
    const email = document.getElementById('email').value

    console.log(phone)
    const response = await fetch('/userapi/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password, phone, email }),
    })

    const data = await response.json()
    console.log(data)

    if (data.status) {
        alert('Registration Successful!')
        // window.location.href = '/dashboard';
    } else {
        alert('Registration failed : ' + data.message)
    }
}
