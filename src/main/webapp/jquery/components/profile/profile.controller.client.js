(function() {
    // $(init);
    var $staticUsername;
    var $phone;
    var $email;
    var $firstName;
    var $lastName;
    var $role;
    var $dob;
    var userService = new UserServiceClient();
    var loginPage = '../login/login.template.client.html';

    var $sessionId;
    if(sessionStorage.username!=undefined && sessionStorage.password!=undefined){
        userService.login(sessionStorage.username, sessionStorage.password)
            .then(function (response) {
                $sessionId = response[0].id;
                init();
            })
    }
    else{
        init();
    }

    function init(){
        $staticUsername = $('#staticUsername');
        $phone = $('#phone');
        $email = $('#email');
        $firstName = $('#firstName');
        $lastName = $('#lastName');
        $role = $('#role');
        $dob = $('#dob');

        if($sessionId!=undefined){
            findUserById($sessionId);
        }
        $('#logoutBtn').click(logout);
        $('#updateBtn').click(updateUser);
    }

    function findUserById(userId){
        userService.findUserById(userId)
            .then(renderUser)
    }

    function renderUser(user){
        $staticUsername.val(user.username);
        $phone.val(user.phone);
        $email.val(user.email);
        $firstName.val(user.firstName);
        $lastName.val(user.lastName);
        $role.val(user.role);
        $dob.val(user.dob);

    }

    function updateUser(){
        var user = {
            firstName : $firstName.val(),
            username : $staticUsername.val(),
            lastName : $lastName.val(),
            role : $role.val(),
            phone : $phone.val(),
            email : $email.val(),
            dob: $dob.val()
        };

        userService
            .updateUser($sessionId, user)
            .then(success);
    }

    function success(){
        console.log('success');
    }

    function logout(){
        sessionStorage.removeItem("username");
        sessionStorage.removeItem("password");
        window.location.href=loginPage;
    }
})();