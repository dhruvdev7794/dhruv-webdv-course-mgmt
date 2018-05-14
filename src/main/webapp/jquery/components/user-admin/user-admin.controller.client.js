(function(){

    jQuery(main);

    var tbody;
    var tr_template;
    var userService = new UserServiceClient();
    function main(){
        // hide the template
        // var hidden = $('.wbdv-hidden');
        // hidden.css('display', 'none')

        tr_template = $('.wbdv-template');
        tbody = $('tbody');
        findAllUsers();
    }

    function findAllUsers() {
        userService.findAllUsers().then(function (response) {
            return response.json();
        }).then(renderUsers);
    }


    function renderUsers(users){
        // render all the users using the template
        for(var i = 0 ; i < users.length ; i++){
            var user = users[i];
            var clone  = tr_template.clone();
            clone.find('.wbdv-username').html(user.username);
            clone.find('.wbdv-first-name').html(user.firstName);
            clone.find('.wbdv-last-name').html(user.lastName);
            clone.find('.wbdv-phone').html(user.phone);
            clone.find('.wbdv-email-id').html(user.email);
            clone.find('.wbdv-role').html(user.role);
            tbody.append(clone);
        }
    }




    // function deleteUser(event){
    //
    // }








//    var $usernameFld, $passwordFld;
//    var $removeBtn, $editBtn, $createBtn;
//    var $firstNameFld, $lastNameFld;
//    var $userRowTemplate, $tbody;
//    var userService = new AdminUserServiceClient();
//    $(main);
//
//    function main(){}
//    function createUser(){}
//    function findAllUsers(){}
//    function findUserById(){}

//    function selectUser(){}
//    function updateUser(){}
//    function renderUser(user){}
//    function renderUsers(users){}
})();