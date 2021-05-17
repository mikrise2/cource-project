let modal = document.getElementById("photoShowingModal");
let modalImg = document.getElementById("modalPhoto");
let span = document.getElementsByClassName("close")[0];

span.onclick = function () {
    modal.style.display = "none";
}

function enlarge_photo(photo_url) {
    modal.style.display = "block";
    modalImg.src = photo_url;
}


function connect() {
    let socket = new SockJS('/almaz');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/comment/' + $('#companyId').val(), function (response) {
            let data = JSON.parse(response.body);
            get_sender(data.text, data.postId, data.userId)
        });
    });
}


function add_bonus(companyName) {

    $('#addBonus').modal('hide');
    $('.modal-backdrop').hide();

    let bonus = {
        "name": $('#bonusName').val(),
        "info": $('#bonusInfo').val(),
        "price": $('#bonusPrice').val(),
        "companyName": companyName
    }

    $.ajax({
        type: "POST",
        url: "/api/bonus",
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(bonus),
        cache: false,
        timeout: 600000,
        success: function () {
            $('#bonuses').append('<button class="btn btn-light w-100">' + bonus.info + '</button>')
        },
    });
}

function add_post(companyName) {
    $('#addPost').modal('hide');
    $('.modal-backdrop').hide();

    let post = {
        "name": $('#postName').val(),
        "text": $('#postText').val(),
        "companyName": companyName
    }

    $.ajax({
        type: "POST",
        url: "/api/post",
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(post),
        dataType: 'text',
        cache: false,
        timeout: 600000,
        success: function (data) {
            $('#posts').prepend('<div id="' + data + '">\n' +
                '                                        <div class="row comment mt-3">\n' +
                '                                            <div class="col align-self-center">\n' +
                '                                                <h5 class="d-flex">' + post.name + '</h5>\n' +
                '                                            </div>\n' +
                '                                            <div class="row">\n' +
                '                                                <div class="col-sm-1">\n' +
                '                                                </div>\n' +
                '                                                <div class="col align-self-center">\n' +
                '                                                    <h6 class="fs-6">' + post.text + '</h6>\n' +
                '                                                </div>\n' +
                '                                            </div>\n' +
                '                                        </div>\n' +
                '                                    </div>' +
                '                                           <div class="row mt-2 mt-2">\n' +
                '                                            <div class="col">\n' +
                '                                                <div class="form-floating">\n' +
                '                                            <textarea class="form-control" placeholder="Leave a comment here"\n' +
                '                                                      id="floatingTextarea"></textarea>\n' +
                '                                                <label for="floatingTextarea">Comment</label>\n' +
                '                                            </div>\n' +
                '                                            </div>\n' +
                '                                            <div class="col-sm-2 align-self-center">\n' +
                '                                                <button class="btn btn-primary">comment</button>\n' +
                '                                            </div>\n' +
                '                                        </div>')
        },
    });
}

function send_comment(id, company_id) {
    console.log("hello")
    get_current_user_for_send_comment(id, company_id)
}


function get_current_user_for_send_comment(id, company_id) {
    $.ajax({
        type: "GET",
        url: "/api/current-user",
        dataType: 'text',
        contentType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log("hello1")
            let user = JSON.parse(data)
            continue_sending(id, company_id, user)
        },
        error: function (e) {
            //TODO
        }
    });

}

function get_sender(text, post_id, user_id) {
    obj = {
        "userId": user_id
    }
    $.ajax({
        type: "GET",
        url: "/api/sender-user",
        dataType: 'text',
        data: obj,
        contentType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            let user = JSON.parse(data)
            add_comment_to_html(post_id, user.photoUrl, user.username, text)
        },
        error: function (e) {
            //TODO
        }
    });
}

function continue_sending(id, company_id, user) {
    let comment_user = {
        "text": $('#text' + id).val(),
        "userId": user.id,
        "companyId": company_id,
        "postId": id
    }
    stompClient.send("/api/comment/" + $('#companyId').val(), {}, JSON.stringify(comment_user));
    console.log("hell2")
}


function add_comment_to_html(id, photo_url, username, text) {
    $('#' + id).append('<div>\n' +
        '                                                <div class="mx-5 row comment">\n' +
        '                                                    <div class="col-sm-1">\n' +
        '                                                        <img class="rounded-circle" width="30" height="30"\n' +
        '                                                             src="' + photo_url + '"\n' +
        '                                                             alt="photo">\n' +
        '                                                    </div>\n' +
        '                                                    <div class="col align-self-center">\n' +
        '                                                        <h5 class="d-flex">' + username + '</h5>\n' +
        '                                                    </div>\n' +
        '                                                    <div class="row">\n' +
        '                                                        <div class="col-sm-1">\n' +
        '                                                        </div>\n' +
        '                                                        <div class="col align-self-center">\n' +
        '                                                            <h6 class="fs-6">' + text + '</h6>\n' +
        '                                                        </div>\n' +
        '                                                    </div>\n' +
        '                                                </div>\n' +
        '                                            </div>')
}


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
                // create_logout_toolbar()
                // create_my_page_button(user.id)
                if (user.userRole === "ADMIN" || user.id.toString() === $('#userId').val()) {
                    create_editing_elements()
                }
                else {
                    let elements = document.getElementsByClassName('commentClass');

                    for (let i = 0; i < elements.length; i++) {
                        console.log(elements[i].innerHTML);
                    }
                }
            }
        },
        error: function (e) {
            //TODO
        }
    });
}


function create_editing_elements() {
    $('#bonusBtnCol').append(' <button type="button" id="addBonusButton" class="btn btn-primary"\n' +
        '                                            data-bs-toggle="modal"\n' +
        '                                            data-bs-target="#addBonus">\n' +
        '                                        add bonus\n' +
        '                                    </button>')
    $('#gallery').append('  <button type="button" id="addPhotoBtn" class="btn btn-primary"\n' +
        '                                            data-bs-toggle="modal"\n' +
        '                                            data-bs-target="#addPhoto">\n' +
        '                                        add photo\n' +
        '                                    </button>')
    $('#postsCol').append(' <button type="button" id="addPostButton" class="btn btn-primary"\n' +
        '                                                data-bs-toggle="modal"\n' +
        '                                                data-bs-target="#addPost">\n' +
        '                                            add post\n' +
        '                                        </button>')

}


connect()