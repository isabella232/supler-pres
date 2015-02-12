var formContainer = document.getElementById('form-container');
var form = new SuplerForm(formContainer, {
    send_form_function: sendForm,
    i18n: {
        label_troll_height: "Height (cm)"
    },
    custom_data_handler: function(data) {
        feedback.html(data);
        feedback.show();
    }
});

$(document).ready(function() {
    $.get('/rest/form1.json', function(data) {
        form.render(data);
    });
});

var feedback = $('#feedback');
feedback.hide();

function sendForm(formValue, renderResponseFn, sendErrorFn) {
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