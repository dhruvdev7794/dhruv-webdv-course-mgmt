function UserServiceClient() {
	this.createUser = createUser;
	this.findAllUsers = findAllUsers;
	this.findUserById = findUserById;
	this.deleteUser = deleteUser;
	this.updateUser = updateUser;
	this.register = register;
	this.login = login;
	this.setSessionAttribute = setSessionAttribute;
	this.url = 'http://localhost:8080/api/user';
	this.loginUrl = 'http://localhost:8080/api/login';
    this.registerUrl = 'http://localhost:8080/api/register';
    this.setSessionUrl = 'http://localhost:8080/api/session/set';
	var self = this;

    //////////// Assignment: User admin functions ////////////
    function createUser(user) {
    	return fetch(self.url, {
			method: 'post',
			body: JSON.stringify(user),
			headers: {
				'content-type' : 'application/json'
			}
		})
    }

    function findAllUsers(){
        return fetch(self.url)
			.then(function (response){
            return response.json();
        });
	}

    function deleteUser(userId) {
    	return fetch(self.url +"/"+userId, {
    		method: 'delete'
		}).then(function (response) {
			return response;
        });
	}

    function updateUser(userId, user) {
    	return fetch(self.url+ "/"+userId, {
    		method: 'put',
			body: JSON.stringify(user),
			headers:{
                'content-type' : 'application/json'
			}
		}).then(function (response) {
			return response.json()
        });
	}


    function findUserById(userId) {
        return fetch(self.url +"/"+userId)
			.then(function (response) {
				return response.json();
            });
	}
	////////////////////////////////////////////////////////////

	function login(username, password){
    	return fetch(self.loginUrl, {
    		method: 'post',
			body: JSON.stringify({username: username, password: password}),
			headers:{
                'content-type' : 'application/json'
			}
		}).then(function (response){
			return response.json();
		});
	}

	function register(user){
    	return fetch(self.registerUrl, {
    		method: 'post',
			body: JSON.stringify(user),
			headers:{
                'content-type' : 'application/json'
			}
		}).then(function (response) {
			// console.log(response);
			if(response.status > 400){
				return null;
			}
			return response.json();
        })

	}

	function setSessionAttribute(username, password){
    	return fetch(self.setSessionUrl+"/username/"+username)
			.then(function (usernameResponse) {
				return fetch(self.setSessionUrl+"/password/"+password)
					.then(function (passwordResponse) {
						console.log(usernameResponse+ passwordResponse);
						return usernameResponse+ passwordResponse;
            		});
        	});

	}


}