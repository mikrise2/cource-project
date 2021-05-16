get_user()

function get_user() {
    $.ajax({
        type: "GET",
        url: "/api/user",
        dataType: 'text',
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log(data)
            user = data
            switch (data) {
                case 'NULL':
                    create_login_toolbar()
                    break
                case 'ADMIN':
                    create_logout_toolbar()
                    break
                case  'USER':
                    create_logout_toolbar()
                    break

            }
        },
        error: function (e) {
            //TODO
        }
    });
}


function create_login_toolbar() {
    document.getElementById('auth').insertAdjacentHTML('afterbegin',
        '<button type="button" onclick="redirect(\'' + 'login' + '\')" class="btn btn-outline-light me-2">Login</button>\n' +
        '<button type="button" onclick="redirect(\'' + 'registration' + '\')" class="btn btn-outline-warning">Sign-up</button>')
}

function create_logout_toolbar() {
    document.getElementById('auth').insertAdjacentHTML('afterbegin',
        '<button type="button" onclick="redirect(\'' + 'logout' + '\')" class="btn btn-outline-dark me-2 text-light">Logout</button>')
}

function redirect(path) {
    window.location.href = '../' + path
}