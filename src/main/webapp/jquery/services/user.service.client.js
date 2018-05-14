function UserServiceClient() {
	// this.createUser = createUser;
	this.findAllUsers = findAllUsers;
	// this.findUserById = findUserById;
	// this.deleteUser = deleteUser;
	// this.updateUser = updateUser;
	this.url = 'http://localhost:8080/api/user';
	var self = this;


	function findAllUsers(){
        return fetch('http://localhost:8080/api/user');
        // return promise.then(function (response){
        //     return response.json();
        // }).then(renderUsers)
        //     .then();
	}
    function findUserById(userId, callback) {}
    function deleteUser(userId) {}
    function updateUser(userId, user, callback) {}


}