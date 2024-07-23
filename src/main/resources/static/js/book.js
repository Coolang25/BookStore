$('document').ready(function () {
    $.ajax({
        url: 'http://localhost:8888/books',
        type: 'GET',
        dataType: 'json',
    }).done(function(response) {
        var html = "";
        response.result.map((book) => {
            html += "<tr>\n" +
                `    <td>${book.title}</td>\n` +
                `    <td>${book.releaseDate}</td>\n` +
                `    <td>${book.author.fullName}</td>\n` +
                "</tr>\n";
        })
        $('#root').append(html);
    });
})

function createBook() {
    $('#success-message').text("");
    $('#fail-message').text("");

    var title = $('#title').val();
    var releaseDate = $('#releaseDate').val();
    var fullName = $('#fullName').val();
    var dob = $('#dob').val();
    var address = $('#address').val();

    var request = {
        title,
        releaseDate,
        author: {
            fullName,
            dob,
            address
        }
    }

    $.ajax({
        url: 'http://localhost:8888/books',
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(request),
    }).done(function(response) {
        $('#success-message').text("Thêm sách thành công");
    }).fail(function(response) {
        $('#fail-message').text(response.responseJSON.message);
    });
}