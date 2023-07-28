function formClear() {
  $("#changePw").on("hidden.bs.modal", function () {
    $("#changePw").find("form").trigger("reset");
    $("#changePw").find("span").empty();
  });
}

function checkPwdValues() {
  const changePw = document.querySelector("#changePw");
  const changePwModal = bootstrap.Modal.getInstance(changePw);

  let result = false;
  let currentPassword = document.getElementById("currentPassword").value;
  let newPassword = document.getElementById("newPassword").value;
  let confirmPassword = document.getElementById("confirmPassword").value;

  if (
    currentPassword.trim() == "" &&
    newPassword.trim() == "" &&
    confirmPassword.trim() == ""
  ) {
    document.querySelector(".currentPasswordError").innerHTML =
      "Please not be blank for current password!";
    document.querySelector(".newpasswordError").innerHTML =
      "Please not be blank for new password!";
    document.querySelector(".confirmpasswordError").innerHTML =
      "Please not be blank for confirm password!";
    return false;
  }

  if (currentPassword.trim() == "") {
    document.querySelector(".twoPwdError").innerHTML = "";
    document.querySelector(".newpasswordError").innerHTML = "";
    document.querySelector(".confirmpasswordError").innerHTML = "";
    document.querySelector(".validateCurrentPassword").innerHTML = "";
    document.querySelector(".currentPasswordError").innerHTML =
      "Please not be blank for current password!";
    return false;
  } else {
    document.querySelector(".currentPasswordError").innerHTML = "";
  }

  if (newPassword.trim() == "") {
	document.querySelector(".validateCurrentPassword").innerHTML = "";
    document.querySelector(".currentPasswordError").innerHTML = "";
    document.querySelector(".confirmpasswordError").innerHTML = "";
    document.querySelector(".twoPwdError").innerHTML = "";
    document.querySelector(".newpasswordError").innerHTML =
      "Please not be blank for new password!";
    return false;
  } else {
    document.querySelector(".newpasswordError").innerHTML = "";
  }

  if (confirmPassword.trim() == "") {
	document.querySelector(".validateCurrentPassword").innerHTML = "";
    document.querySelector(".newpasswordError").innerHTML = "";
    document.querySelector(".currentPasswordError").innerHTML = "";
    document.querySelector(".twoPwdError").innerHTML = "";
    document.querySelector(".confirmpasswordError").innerHTML =
      "Please not be blank for confirm password!";
    return false;
  } else {
    document.querySelector(".confirmpasswordError").innerHTML = "";
  }

  if (
    currentPassword.trim() == "" ||
    newPassword.trim() == "" ||
    confirmPassword.trim() == ""
  ) {
    return false;
  } else {
    if (newPassword != confirmPassword) {
      document.querySelector(".twoPwdError").innerHTML =
        "Please enter the same value in new password and confirm password!";
      return false;
    } else {
      alert("starting in ajax");
      document.querySelector(".twoPwdError").innerHTML = "";
      var chgPasswordForm = {
        currentPassword: currentPassword,
        newPassword: newPassword,
        confirmPassword: confirmPassword,
      };

      $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/admin/changePassword",
        data: JSON.stringify(chgPasswordForm),
        dataType: "text",
        cache: false,
        success: function (data) {
          result = true;
          changePwModal.hide();
        },
        error: function (e) {
          result = false;
          $(".validateCurrentPassword").text(e.responseText);
          
        },
        });
      $(".validateCurrentPassword").text();

      return result;
    }
  }

  return result;
}

let toDeleteid;
function confirmDeleteModal(id) {
  toDeleteid = id;
  $("#confirmDelete").modal();
}

function deleteUser() {
  window.location.href = "http://localhost:8080" + toDeleteid;
}
