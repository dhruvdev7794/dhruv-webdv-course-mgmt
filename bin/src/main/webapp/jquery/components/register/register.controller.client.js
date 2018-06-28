(function () {
    var $registerBtn;
    var userService = new UserServiceClient();
    var profileRedirect = '../profile/profile.template.client.html';
    $(main);

    function main() {
        $('#registerBtn').click(register);
    }
    function register() {
        var date = new Date($('#dobFld').val());
        date.setDate(date.getDate()+1);
        if($('#passwordFld').val() == $('#verifyPasswordFld').val()){
            var user = {
                firstName : $('#firstNameFld').val(),
                lastName : $('#lastNameFld').val(),
                email : $('#emailFld').val(),
                username : $('#usernameFld').val(),
                password : $('#passwordFld').val(),
                phone : $('#phoneFld').val(),
                dateOfBirth : date
            };

            return userService.register(user)
                .then(function (response) {
                    if(response == null){
                        alert("username already exists");
                    }
                    else{
                        console.log(response);
                        window.location.href=profileRedirect+"?user="+response.id;;
                    }
                });
        }
        else{
            alert("password verification error");
        }
    }
})();
