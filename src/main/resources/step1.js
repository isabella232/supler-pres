var formContainer = document.getElementById('form-container');
var form = new Supler.Form(formContainer, {});

$(document).ready(function() {
    $.get('/rest/form1.json', function(data) {
        showServerJson(data);
        form.render(data);
    });
});
