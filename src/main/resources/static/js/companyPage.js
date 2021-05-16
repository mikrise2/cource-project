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
        cache: false,
        timeout: 600000,
        success: function () {
            $('#posts').prepend('<div>\n' +
                '                                        <div class="row comment">\n' +
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
                '                                    </div>')
        },
    });
}

