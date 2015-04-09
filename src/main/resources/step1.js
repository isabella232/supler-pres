var formContainer = document.getElementById('form-container');
var form = new Supler.Form(formContainer, {
    send_form_function: sendForm
});

$(document).ready(function() {
    $.get('/rest/form1.json', function(data) {
        $('#json-server').html(JSON.stringify(data));
        form.render(data);
    });
});

function sendForm(formValue, renderResponseFn, sendErrorFn) {
    $('#json-client').html(JSON.stringify(formValue));
    $.ajax({
        url: '/rest/form1.json',
        type: 'POST',
        data: JSON.stringify(formValue),
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        success: renderResponseFn,
        error: sendErrorFn
    });
};
