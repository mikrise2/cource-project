get_user()

function get_user() {
    $.ajax({
        type: "GET",
        url: "/api/user",
        dataType: 'text',
        contentType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            if (data) {
                let user = JSON.parse(data)
                create_logout_toolbar()
                create_my_page_button(user.id)
                if (user.userRole === "ADMIN")
                    create_user_list_button()
            } else
                create_login_toolbar()
        },
        error: function (e) {
            //TODO
        }
    });
}


function create_login_toolbar() {
    $('#auth').prepend(
        '<button type="button" onclick="redirect(\'' + 'login' + '\')" class="btn btn-outline-light me-2">Login</button>\n' +
        '<button type="button" onclick="redirect(\'' + 'registration' + '\')" class="btn btn-outline-warning">Sign-up</button>')
}

function create_logout_toolbar() {
    $('#auth').prepend('<button type="button" onclick="redirect(\'' + 'logout' + '\')" class="btn btn-outline-dark me-2 text-light">Logout</button>')
}

function create_user_list_button() {
    $('#navbar').append('<li><a href="#" class="nav-link px-2 text-white">users list</a></li>')
}

function create_my_page_button(id) {
    $('#navbar').prepend('<li><a href="/' + id + '" class="nav-link px-2 text-white">My page</a></li>'
    )
}

function redirect(path) {
    window.location.href = '../' + path
}