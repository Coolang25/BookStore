$('document').ready(function () {
    $.ajax({
        url: 'http://localhost:8888/authors',
        type: 'GET',
        dataType: 'json',
    }).done(function(response) {
        var html = "";
        response.result.map((author) => {
            html += "<tr>\n" +
                `    <td>${author.fullName}</td>\n` +
                `    <td>${author.dob}</td>\n` +
                `    <td>${author.address}</td>\n` +
                "</tr>\n";
        })
        $('#root').append(html);
    });
})

function createAuthor() {
    $('#success-message').text("");
    $('#fail-message').text("");

    var fullName = $('#fullName').val();
    var dob = $('#dob').val();
    var address = $('#address').val();

    var request = {fullName, dob, address};

    $.ajax({
        url: 'http://localhost:8888/authors',
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(request),
    }).done(function(response) {
        $('#success-message').text("Thêm tác giả thành công");
    }).fail(function(response) {
        $('#fail-message').text(response.responseJSON.message);
    });
}