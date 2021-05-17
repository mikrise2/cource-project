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
                if (user.userRole === "ADMIN" || user.id.toString() === $('#userId').val()) {
                    create_companies_toolbar()
                }

            }
        },
        error: function (e) {
            //TODO
        }
    });
}

function create_companies_toolbar() {
    $('#companiesToolbar').css("visibility", "visible")
}