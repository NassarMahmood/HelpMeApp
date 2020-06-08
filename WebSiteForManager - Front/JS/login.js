
async function signIn()
{
    var signInEmail = document.getElementById("signInEmail").value.trim();
    var signInPassword = document.getElementById("signInPassword").value.trim();

    var nextPage = false;

    if(!validateEmail(signInEmail))
        alert("unvalid Email..");
    else
    {   
        if(signInPassword == "")
            alert("unvalid Password..");
        
        else
        {
            await axios.get("http://localhost:2345/user/login/"+signInEmail+"/"+signInPassword)
            .then(function(response){
                // handle success
                
                var data = response.data;

                if(data.role == "ADMIN"){
                    if(data.email == signInEmail && data.password == signInPassword){

                        a();
                        async function a(){
    
                            localStorage.setItem('userId' , data.userId);
                            localStorage.setItem('fisrtName' , data.fisrtName);
                            localStorage.setItem('lastName' , data.lastName);
                            localStorage.setItem('gender' , data.gender);
                            localStorage.setItem('phone' , data.phone);
                            localStorage.setItem('email' , data.email);
                            localStorage.setItem('password' , data.password);
                            localStorage.setItem('role' , data.role);
                        }
                       
    
                        // window.location.replace("../HTML/managerMainScreen.html");
                         window.document.location = '../HTML/managerMainScreen.html';
    
                    }
                }
                else
                alert("your are not an manager");


            })
            .catch(function(error){
                // handle error
                alert(error.response.data.error);
                
            });
        }

    }
    

}



async function signUp()
{

    var signUpId = document.getElementById("signUpId").value.trim();
    var signUpFirstName = document.getElementById("signUpFirstName").value.trim();
    var signUpLastName = document.getElementById("signUpLastName").value.trim();
    var signUpEmail = document.getElementById("signUpEmail").value.trim();
    var signUpPassword = document.getElementById("signUpPassword").value.trim();
    var signUpPhone = document.getElementById("signUpPhone").value.trim();
    var signUpState = document.getElementById("signUpState").value.trim();
    var signUpCity = document.getElementById("signUpCity").value.trim();
    var signUpStreetAddress = document.getElementById("signUpStreetAddress").value.trim();
    var signUpHouseNumber = document.getElementById("signUpHouseNumber").value.trim();
    var signUpZipCode = document.getElementById("signUpZipCode").value.trim();
    var birthday = document.getElementById("birthday").value.trim();
    var signUpGender = genderSelect.options[genderSelect.selectedIndex].value.toUpperCase();


    // var name = document.getElementById("signUpName").value.trim();
    // var email = document.getElementById("signUpEmail").value.trim();
    // var roleSelected = document.getElementById("roleSelect");
    // var role = roleSelected.options[roleSelected.selectedIndex].value.toUpperCase();
    // var avatar = document.getElementById("avatar").value.trim();
    var date = new Date(birthday)
    var day = date.getDate();
    var month = date.getMonth() + 1;
    var year = date.getFullYear();



    await axios.post("http://localhost:2345/user/registration/"+signUpId, {

     
        userId: signUpId,
        fisrtName: signUpFirstName,
        lastName: signUpLastName,
        birthdate: {
            days: day,
            months: month,
            years: year
        },
        gender: "MALE",
        phone: signUpPhone,
        address: {
            state: signUpState,
            city: signUpCity,
            streetAddress: signUpStreetAddress,
            houseNumber: signUpHouseNumber,
            zipCode: signUpZipCode
        },
        active: true,
        signUpTimestamp: null,
        email: signUpEmail,
        password: signUpPassword,
        role: "ADMIN",
        haveRequest: false


    })
    .then(function(response){
        // handle success
        
        var data = response.data;
        console.log(data);
        
        if(data.email == email){

            window.document.location = '../HTML/managerMainScreen.html';

        }

    })
    .catch(function(error){
        // handle error
        alert(error.response.data.error);

    });



}




function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}




/**
 *
 
 
fetch("http://localhost:5432/collab/users/login/"+signInDomain+"/"+signInEmail, {
        //mode: 'cors',
        method: "GET", 
        //body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(result => console.log(result))
    .catch(error => {
        console.error('Error:', error);
        console.log(error.status);
        alert(error);    
    });

 */