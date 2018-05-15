(function () {
    var $registerBtn;
    var userService = new UserServiceClient();
    var loginRedirect = '../login/login.template.client.html'
    $(main);

    function main() {
        $('#registerBtn').click(register);
    }
    function register() {
        if($('#passwordFld').val() == $('#verifyPasswordFld').val()){
            var user = {
                firstName : $('#firstNameFld').val(),
                lastName : $('#lastNameFld').val(),
                email : $('#emailFld').val(),
                username : $('#usernameFld').val(),
                password : $('#passwordFld').val(),
                phone : $('#phoneFld').val(),
                dateOfBirth : $('#dobFld').val()
            };

            return userService.register(user)
                .then(function (response) {
                    if(response == null){
                        alert("username already exists");
                    }
                    else{
                        window.location.href=loginRedirect;
                    }
                });
        }
        else{
            alert("password verification error");
        }
    }
})();
