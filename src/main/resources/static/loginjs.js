async function loadAccounts() {
    const token = localStorage.getItem('token')
    const username = localStorage.getItem('username')
    const response = await fetch('/accountapi/accounts', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            Authorization: 'Bearer ' + token,
        },
        body: JSON.stringify({
            userId: userid,
        }),
    })

    console.log('Inside the function loadAccounts')
    const result = await response.json()
    console.log('response receievd')

    if (!result.status) {
        alert(result.message)
    } else {
        localStorage.setItem('accounts', JSON.stringify(result.accounts))
    }
}

async function login() {
    const username = document.getElementById('username').value
    const password = document.getElementById('password').value
    try {
        const response = await fetch('/userapi/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                username,
                password,
            }),
        })
        console.log('Response Received')
        const data = await response.json()
        console.log('Data')
        console.log(data)

        if (data.success) {
            localStorage.setItem('token', data.token)
            localStorage.setItem('userid',data.userId)
            window.location.href = '/dashboard.html'
        } else {
            alert('Login failed : ' + data.message)
        }
    } catch (error) {
        console.log(error)
    }
}
