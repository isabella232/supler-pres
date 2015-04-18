var formContainer = document.getElementById('form-container');
var form = new Supler.Form(formContainer, {});

$(document).ready(function() {
    $.get('/rest/form1.json', function(data) {
        $('#json-server-code').html(JSON.stringify(data, null, 2));
        hljs.initHighlighting();
        form.render(data);
    });
});
