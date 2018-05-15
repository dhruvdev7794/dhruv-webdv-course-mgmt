(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();
    var profileRedirect = '../profile/profile.template.client.html';
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
        if(response!= null && response.username !=null && response.password!=null){
            userService.setSessionAttribute(response.username, response.password).then(function () {
                window.location.href=profileRedirect;
            });

        }
        else{
            alert('incorrect details')
        }
    }


})();
