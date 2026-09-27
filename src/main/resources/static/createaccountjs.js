async function createAccount() {
    var username = document.getElementById('username').value
    var accountType = document.getElementById('accountType').value

    const response = await fetch('/accountapi/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            username: username,
            accountType: accountType,
        }),
    })

    const result = await response.json()

    if (result.success) {
        alert('Account creation successful!')
        window.location.href = '/dashboard.html'
    } else {
        alert('Account creation failed: ' + result.message)
    }
}
