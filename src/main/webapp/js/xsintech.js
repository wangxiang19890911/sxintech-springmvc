dateFormat = {
		  fmt : {
		    "yyyy": function(date) { return date.getFullYear() + ''; },
		    "MM": function(date) { return ('0' + (date.getMonth() + 1)).slice(-2); },
		    "dd": function(date) { return ('0' + date.getDate()).slice(-2); },
		    "hh": function(date) { return ('0' + date.getHours()).slice(-2); },
		    "mm": function(date) { return ('0' + date.getMinutes()).slice(-2); },
		    "ss": function(date) { return ('0' + date.getSeconds()).slice(-2); }
		  },
		  format:function dateFormat (date, format) {
		    var result = format;
		    for (var key in this.fmt)
		      result = result.replace(key, this.fmt[key](date));
		    return result;
		  }
		};

function isNumber(s) {
	if (s === "" || s === null) {
		return false;
	} else {
		var number = parseInt(s);
		if (number == 'NaN') {
			return false;
		} else {
			return true;
		}
	}
}

function addCommaToNumber(string) {
	var convertString = string.replace(/,/gi, "").split("").reverse().join("").replace(/(.{3})/g, "$1,").split("").reverse().join("");
	if (convertString.substring(0, 1) == ",") {
		return convertString.substring(1, convertString.length);
	}
	return convertString;
}

$('#a-logout').click(function() {
	if (window.confirm("ログアウトしますか？")) {
		window.location.href = CONTEXTPATH + "/secure/logout";
	}
});
var utils = {
	notify_notification : function(type, message, autoRemoveSeconds) {
		var message_notification = $('#message_notification');
		if (message_notification.length > 0) {
			message_notification.remove();
		}
		setTimeout(function() {
			message_notification = $('<div id="message_notification" class="alert alert-' + type + ' alert-fixed-top" />');
			message_notification.append('<button type="button" class="close" data-dismiss="alert">×</button>');
			message_notification.append(message);
			$(document.body).append(message_notification);
		}, 500);
		if (autoRemoveSeconds) {
			setTimeout(function() {
				$('#message_notification').remove();
			}, autoRemoveSeconds * 1000 + 500);
		}
	}
};
setTimeout(function() {
	window.location.href = CONTEXTPATH + '/';
}, 30*60*1000);

showAlert = function(message) {
	$("#alertModalMessage").text(message);
	$('#alertModal').modal('show');
};

showHTMLAlert = function(message) {
	$("#alertModalMessage").html(message);
	$('#alertModal').modal('show');
};