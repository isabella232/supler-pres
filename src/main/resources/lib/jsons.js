function showServerJson(data) {
    $('#json-server-code').html(JSON.stringify(data, null, 2));
    hljs.initHighlighting();
}

function showClientJson(data) {
    $('#json-client-code').html(JSON.stringify(data, null, 2));
    hljs.initHighlighting();
}