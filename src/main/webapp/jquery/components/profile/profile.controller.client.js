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


    var urlParams = new URLSearchParams(window.location.search);
    var userId = urlParams.get("user");
    $(init);

    function init(){
        $staticUsername = $('#staticUsername');
        $phone = $('#phone');
        $email = $('#email');
        $firstName = $('#firstName');
        $lastName = $('#lastName');
        $role = $('#role');
        $dob = $('#dob');
        $('#logoutBtn').click(logout);
        $('#updateBtn').click(updateUser);
        if(userId!=null){
            findUserById(userId);
        }
    }

    function findUserById(userId){
        userService.findUserById(userId)
            .then(renderUser)
    }

    function renderUser(user){
        var now = new Date(user.dateOfBirth);
        var day = ("0" + now.getDate()).slice(-2);
        var month = ("0" + (now.getMonth() + 1)).slice(-2);
        var today = now.getFullYear()+"-"+(month)+"-"+(day);


        $('#staticUsername').val(user.username);
        $('#phone').val(user.phone);
        $('#email').val(user.email);
        $('#firstName').val(user.firstName);
        $('#lastName').val(user.lastName);
        $('#role').val(user.role);
        $('#dob').val(today);

    }

    function updateUser() {
        var date = new Date($('#dob').val());
        date.setDate(date.getDate()+1);
        var user = {
            firstName : $('#firstName').val(),
            username : $('#staticUsername').val(),
            lastName : $('#lastName').val(),
            role : $('#role').val(),
            phone : $('#phone').val(),
            email : $('#email').val(),
            dateOfBirth: date
        };
        userService
            .updateUser(userId, user);
    }



    function logout(){
        window.location.href=loginPage;
    }
})();