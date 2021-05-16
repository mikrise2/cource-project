//TODO

// Get the modal
var modal = document.getElementById("myModal");

// Get the image and insert it inside the modal - use its "alt" text as a caption
var img = document.getElementById("myImg");
var modalImg = document.getElementById("img01");
var captionText = document.getElementById("caption");
img.onclick = function () {
    modal.style.display = "block";
    modalImg.src = this.src;
    captionText.innerHTML = this.alt;
}

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];


// When the user clicks on <span> (x), close the modal
span.onclick = function () {
    modal.style.display = "none";
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
        type: "PUT",
        url: "/api/bonus",
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(bonus),
        cache: false,
        timeout: 600000,
        success: function () {
            console.log("1")
            $('#bonuses').append('<button class="btn btn-light w-100">' + bonus.info + '</button>')

        },
        // error: function (e) {
        //     console.log("2")
        //     // failed_request()
        // }
    });

}


function failed_request() {
    alert("sorry, you can't do this function now")
}