$(document).ready(function() {
	$('#submit').click(function() {
		var data = {
			firstName: $('#firstName').val(),
			age: $('#age').val(),
		};
		$.ajax({
			type: 'POST', url: './form.do',
			contentType: 'application/json; charset=utf-8', dataType: 'json',
			data: JSON.stringify(data),
			success: function(data, textStatus, jqXHR) {
				window.location.replace('./confirm.html');
			},
			error: function(jqXHR, textStatus, errorThrown) {
				if(jqXHR.status == 400) {
					var messages = JSON.parse(jqXHR.responseText);
					$('#messages').empty();
					$.each(messages, function(i, v) {
						var item = $('<li>').append(v);
						$('#messages').append(item);
					});
				} else {
					alert('Unexpected server error.');
				}
			}
		});
	});
});