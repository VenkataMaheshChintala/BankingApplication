async function loadAccounts() {
    const token = localStorage.getItem('token')
    const userid = localStorage.getItem('userid')
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
    const userAccountsdiv = document.getElementById('userAccounts')
    console.log('response receievd')
    // need to fill displaying the accounts

    if (!result.status) {
        alert(response.message)
    } else {
        userAccountsdiv.innerHTML = ''
        result.accounts.forEach((account) => {
            const card = document.createElement('div')
            card.className = 'account-card'
            card.innerHTML = `
                            <h2>${account.accountType} Account</h2>
                            <p><b>Account Number:</b> ${account.accountNumber}</p>
                            <p><b>Status:</b> ${account.status}</p>
                            <button onclick="showPasswordDialog(${account.accountNumber})">
                            Check Balance
                            </button>`
            userAccountsdiv.appendChild(card)
        })
    }
}

let selectedAccountNumber = null

function showPasswordDialog(accountNumber) {
    selectedAccountNumber = accountNumber
    document.getElementById('passwordInput').value = ''
    document.getElementById('passwordModal').style.display = 'flex'
}

function closePasswordModal() {
    document.getElementById('passwordModal').style.display = 'none'
}

function submitPassword() {
    const password = document.getElementById('passwordInput').value
    closePasswordModal()
    checkBalance(selectedAccountNumber, password)
}

function showBalanceModal(balance) {
    document.getElementById('balanceText').textContent = '₹ ' + balance
    document.getElementById('balanceModal').style.display = 'flex'
}

function closeBalanceModal() {
    document.getElementById('balanceModal').style.display = 'none'
}

// function to check the balance
async function checkBalance(accountNumber, password) {
    const token = localStorage.getItem('token')

    const response = await fetch('/accountapi/checkbalance', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            Authorization: 'Bearer' + token,
        },
        body: JSON.stringify({
            accountNumber: accountNumber,
            password: password,
        }),
    })
    console.log('Inside check balance, request sent')
    const result = await response.json()
    console.log('Response received')
    if (result.status) {
        showBalanceModal(result.balance)
    } else {
        alert('Balance Check fetch failed : ' + response.message)
    }
}

async function createAccount() {
    var username = document.getElementById('username').value
    var accountType = document.getElementById('accountType').value
    const token = localStorage.getItem('token')

    const response = await fetch('/accountapi/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            Authorization: 'Bearer ' + token,
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

function showpage(pageId, element) {
    const pages = document.querySelectorAll('.page')
    pages.forEach((page) => {
        page.classList.remove('active-page')
    })

    document.getElementById(pageId).classList.add('active-page')
    const menuItems = document.querySelectorAll('.menu-item')

    menuItems.forEach((item) => {
        item.classList.remove('active')
    })

    element.classList.add('active')
}

function logout() {
    localStorage.clear("token");
    localStorage.clear("username");
    window.location.href = "/login.html"
}