var formContainer = document.getElementById('form-container');
var form = new Supler.Form(formContainer, {
    send_form_function: sendForm
});

$(document).ready(function() {
    $.get('/rest/form1.json', function(data) {
        $('#json-server-code').html(JSON.stringify(data, null, 2));
        hljs.initHighlighting();
        form.render(data);
    });
});

function sendForm(formValue, renderResponseFn, sendErrorFn) {
    $('#json-client-code').html(JSON.stringify(formValue, null, 2));
    hljs.initHighlighting();
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
