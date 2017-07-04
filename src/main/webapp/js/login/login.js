$(document).ready(function () {
	$('[data-toggle="offcanvas"]').click(function () {
		$('.row-offcanvas').toggleClass('active');
	});

	if ($.cookie("save-id-activation") == "active") {
		$("#save_id").addClass('active');
		if ($.cookie('saved-id')) {
			$("#userId").val($.cookie('saved-id'));
		}
	}else {
		$("#save_id").removeClass('active');
	}

	$("#save_id").click(function() {
		if ($(this).hasClass('active')) {
			// inactive status
			$(this).removeClass('active');
			$.cookie("save-id-activation")
			$.cookie('save-id-activation', '');
			$.cookie('saved-id', "");
			$("#userId").val('');
		} else {
			// active status
			$(this).addClass('active');
			$.cookie('save-id-activation', 'active', { expires: 31 });
		}
	});

	$("#login-form").submit(function(event) {
		if ($("#save_id").hasClass('active')) {
			$.cookie('saved-id', $("#userId").val(), { expires: 31 });
		}
	})

  	// パスワード忘れボタンの設定
	$("#password-forgot-next").click(function(event) {
		$("#modal-password-forgot *").prop("disabled", true);

		var email = $("#password-forgot-email").val();
/*		var validationResult = true;
		if (!isEmail(email)) {
			$("#password-forgot-email").next().show();
			$("#modal-password-forgot *").prop("disabled", false);
			validationResult = false;
		} else {
			$("#password-forgot-email").next().hide();
		}

		if (!validationResult) {
			return;
		}
*/
		$.post(CONTEXTPATH + "/send_password_recovery_notice", {
			"userName" : email
		}).done(function(data, textStatus, jqXHR) {
			if (data) {
				alert("登録頂いているメールアドレスへパスワード再設定メールが送信されました。");
				$("#modal-password-forgot").modal('hide');
			} else {
				alert("入力頂いたメールアドレスが登録されていません。");
			}
		}).fail(function(jqXHR, textStatus, errorThrown) {
			alert("大変申し訳ありません。\nシステムエラーが発生しております。\nしばらくしてから再度アクセスしてください。");
		}).always(function() {
			$("#modal-password-forgot *").prop("disabled", false);
		});
	});
});

function isEmail(email) {
	var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	return regex.test(email);
}