(function(){

    jQuery(main);

    var tbody;
    var tr_template;
    var userService = new UserServiceClient();
    var $editBtn;
    function main(){
        // hide the template
        var hidden = $('.wbdv-hidden');
        hidden.css('display', 'none');
        hidden.css("display", '');
        tr_template = $('.wbdv-template');
        tbody = $('tbody');
        $('#wbdv-add').click(createUser);
        $('#wbdv-done').click(updateUser)
        findAllUsers();
    }

    function findAllUsers(){
        userService.findAllUsers()
            .then(renderUsers);
    }


    function renderUsers(users){
        // render all the users using the template
        tbody.empty();
        for(var i = 0 ; i < users.length ; i++){
            renderUser(users[i]);
        }
    }
    function renderUser(user){
        var clone  = tr_template.clone();
        // id
        clone.attr('id', user.id);
        // get delete and edit buttons
        clone.find('.wbdv-remove').click(deleteUser);
        clone.find('.wbdv-edit').click(editUser);
        // find the contents
        clone.find('.wbdv-username').html(user.username);
        clone.find('.wbdv-first-name').html(user.firstName);
        clone.find('.wbdv-last-name').html(user.lastName);
        clone.find('.wbdv-phone').html(user.phone);
        clone.find('.wbdv-email-id').html(user.email);
        clone.find('.wbdv-role').html(user.role);
        tbody.append(clone);
    }


    function createUser(){
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var email = $('#emailFld').val();
        var phone = $('#phoneFld').val();
        var role = $('#roleFld').val();


        var user = {
            email: email,
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            phone: phone,
            role: role
        };

        userService.createUser(user)
            .then(findAllUsers);
    }

    function deleteUser(event){
        var $deleteBtn = $(event.currentTarget);
        var userId = $deleteBtn.parent().parent().parent().attr('id');

        // cal service to delete
        userService.deleteUser(userId)
            .then(function (response) {
                if(response.status>300){
                    alert("Incorrect id");
                }
                else{
                    findAllUsers();
                }
            });
    }

    function editUser(event){
        $editBtn = $(event.currentTarget);
        var userId = $editBtn.parent().parent().parent().attr('id');
        findUserById(userId)
            .then(selectUser);
    }


    function findUserById(userId){
        return userService.findUserById(userId);
    }

    function selectUser(user){
        $('#usernameFld').val(user.username);
        $('#passwordFld').val(user.password);
        $('#firstNameFld').val(user.firstName);
        $('#lastNameFld').val(user.lastName);
        $('#emailFld').val(user.email);
        $('#phoneFld').val(user.phone);
        $('#roleFld').val(user.role);
    }

    function updateUser(){
        var userId = $editBtn.parent().parent().parent().attr('id');
        findUserById(userId)
            .then(function (response) {
                var user = {
                    username : $('#usernameFld').val(),
                    password : $('#passwordFld').val(),
                    firstName : $('#firstNameFld').val(),
                    lastName : $('#lastNameFld').val(),
                    email : $('#emailFld').val(),
                    phone : $('#phoneFld').val(),
                    role : $('#roleFld').val(),

                };
                userService.updateUser(userId, user)
                    .then(findAllUsers);
            });
    }


})();