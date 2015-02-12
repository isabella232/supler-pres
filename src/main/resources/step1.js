var formContainer = document.getElementById('form-container');
var form = new SuplerForm(formContainer, {});

$(document).ready(function() {
    $.get('/rest/form1.json', function(data) {
        form.render(data);
    });
});
