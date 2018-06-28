function User(username, password, email,  firstName, lastName, phone, role, dateOfBirth){
    this.username = username;
    this.password = password;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastname;
    this.phone = phone;
    this.role = role;
    this.dateOfBirth = dateOfBirth;

    // Username
    this.setUsername = setUsername;
    this.getUsername = getUsername;
    // Password
    this.setPassword = setPassword;
    this.getPassword = getPassword;
    // Email
    this.setEmail = setEmail;
    this.getEmail = getEmail;
    // First Name
    this.getFirstName = firstName;
    this.getFirstName = getFirstName
    // Last Name
    this.setLastName = setLastName;
    this.getLastName = getLastName;
    // Phone Number
    this.setPhone = setPhone;
    this.getPhone = getPhone;
    // Role
    this.setRole = setRole;
    this.getRole = getRole;
    // Date Of Birth
    this.setDateOfBirth = setDateOfBirth;
    this.getDateOfBirth = getDateOfBirth;
    
    function setUsername(username){
        this.username = username;
    }
    function getUsername(){
        return this.username;
    }
    function setPassword(password){
        this.password = password;
    }
    function getPassword(){
        return this.password;
    }
    function setEmail(email){
        this.email = email;
    }
    function getEmail(){
        return this.email;
    }
    function setFirstName(firstName){
        this.firstName = firstName;
    }
    function getFirstName(){
        return this.firstName;
    }
    function setLastName(lastName){
        this.lastName = lastName;
    }
    function getLastName(){
        return this.lastName;
    }
    function setPhone(phone){
        this.phone = phone;
    }
    function getPhone(){
        return this.phone;
    }
    function setRole(role){
        this.role = role;
    }
    function getRole(){
        return this.role;
    }
    function setDateOfBirth(dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    function getDateOfBirth(){
        return this.dateOfBirth;
    }
    

    
    
}