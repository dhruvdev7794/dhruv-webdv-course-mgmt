(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $usernameFld = $('#wbdv-username');
        $passwordFld = $('#wbdv-password');
        $('#wbdv-login').click(login);
    }
    function login() {
        userService.login($usernameFld.val(), $passwordFld.val())
            .then(setSession)
    }

    function setSession(response){
        console.log(response);
        if(response.length>0){
            var user = response[0];
            sessionStorage.username = user.username;
            sessionStorage.password = user.password;
        }
        else{
            alert('incorrect details')
        }
    }


})();
