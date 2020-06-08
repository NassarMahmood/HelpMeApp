

$( document ).ready(function() {
  
  console.log( "ready!" );
  
  startPage();

  getUser();

});



function getUser(){


  var userId = localStorage.getItem('userId');
  var fisrtName = localStorage.getItem('fisrtName');
  var lastName = localStorage.getItem('lastName');
  var gender = localStorage.getItem('gender');
  var phone = localStorage.getItem('phone');
  var email = localStorage.getItem('email');
  var password = localStorage.getItem('password');
  var role = localStorage.getItem('role');

  localStorage.clear();



  console.log(userId); 
  console.log(fisrtName);
  console.log(lastName);
  console.log(gender);
  console.log(phone);
  console.log(email);
  console.log(password);
  console.log(role);


}


function startPage(){

  var table = document.getElementById("volunteerRequests");
  table.style.visibility = "collapse";

  var search = document.getElementById("search");
  var map = document.getElementById("map");
  map.style.display = "none";
  search.style.display = "none";

  var search = document.getElementById("add-state");
  search.style.display = "none";


  var search = document.getElementById("add-mall");
  search.style.display = "none";


  var search = document.getElementById("add-store");
  search.style.display = "none";

  var search = document.getElementById("show-table");
  search.style.visibility = "collapse";


}




async function getAllVolunteerRequests() {


  startPage();
  var table = document.getElementById("volunteerRequests");
  table.style.visibility = "visible"

  document.getElementById("title-buttons").innerHTML = "Volunteer Requests";
  

  await axios.get("http://10.0.0.12:2345/jioningRequest/{userId}/all")
    .then(function (response) {
      // handle success
      var data = response.data;
      console.log(data);
      var all = document.getElementById("all");
      console.log(data[0].name);
      fillRequestTable(data)
    })
    .catch(function (error) {
      // handle error
      alert(error.response);

      // if(error.response.status == 404 || error.response.status == 500)
      //   alert(error.response.data.error);
      // else
        console.log(error);
    });


}



  

async function fillRequestTable(data) {



  var table = document.getElementById("volunteerRequests");
  var tableLength = table.rows.length;

  var row = table.insertRow(tableLength);

  var i;
  for (i = 0; i < data.length; i++) {

    var row = table.insertRow(tableLength);

    var cell0 = row.insertCell(0);
    var cell1 = row.insertCell(1);
    var cell2 = row.insertCell(2);
    var cell3 = row.insertCell(3);
    var cell4 = row.insertCell(4);
    var cell5 = row.insertCell(5);
    var cell6 = row.insertCell(6);
  
    cell0.innerHTML = data[i].user.userId;
    cell1.innerHTML = data[i].user.fisrtName +" "+ data[i].user.lastName;
    cell2.innerHTML = data[i].user.gender;
    cell3.innerHTML = data[i].user.address.streetAddress+" "+data[i].user.address.houseNumber+", "+data[i].user.address.city;
    cell4.innerHTML = data[i].user.phone;
    var btn = document.createElement("BUTTON");
    btn.type = "button";
    // btn.id = "accept"+i;
    btn.innerHTML = "Accept";
    // btn.onclick = f
    btn.className = "btn btn-outline-primary";
    cell5.appendChild(btn);

  }


}




function startGetSpecificElement(){


  startPage();
  document.getElementById("title-buttons").innerHTML = "Add Tasks";

  var search = document.getElementById("search");
  search.style.display = "block";
  var map = document.getElementById("map");
  map.style.display = "block";



  // I AM HERE

}


async function getSpecificElement(){


  console.log("ali");


  var id = document.getElementById("input-search").value.trim();

  //http://localhost:8888/collab/elements/2020a.alik/ali@Email.com/2020a.alik/2
  await axios.get("http://localhost:8888/collab/elements/" + localStorage.getItem('user-domain') + "/" + localStorage.getItem('user-email')+"/"+localStorage.getItem('user-domain')+"/"+id)
    .then(function (response) {
      // handle success
      var data = response.data;
      console.log(data);
      showSpecificElement(data);
    })
    .catch(function (error) {
      // handle error
      if(error.response.status == 404 || error.response.status == 500)
        alert(error.response.data.error);
      else
        console.log(error);
    });


/*
 if(error.response.status == 404 || error.response.status == 500)
        alert(error.response.data.error);
      else
*/



}



function startAddState(){

  startPage();
  var search = document.getElementById("add-state");
  search.style.display = "block";

}


async function addState(){

  var stateName = document.getElementById("input-state").value.trim();

  //http://localhost:8888/collab/elements/2020a.alik/ali@Email.com
  await axios.post("http://localhost:8888/collab/elements/" + localStorage.getItem('user-domain') + "/" + localStorage.getItem('user-email'),{

  type:"state",
  name: stateName,
  active: true

  })
    .then(function (response) {
      // handle success
      var data = response.data;
      console.log(data);
      showSpecificElement(data);
    })
    .catch(function (error) {
      // handle error
      if(error.response.status == 404 || error.response.status == 500)
        alert(error.response.data.error);
      else
        console.log(error);
    });

}





function startAddMall(){

  startPage();
  var search = document.getElementById("add-mall");
  search.style.display = "block";

}


async function addMall(){

  var name = document.getElementById("mall-name").value.trim();
  var parent = document.getElementById("mall-parent").value.trim();
  var city = document.getElementById("mall-city").value.trim();
  var street = document.getElementById("mall-street").value.trim();
  var bulding = document.getElementById("mall-bulding").value.trim();

  //http://localhost:8888/collab/elements/2020a.alik/ali@Email.com
  await axios.post("http://localhost:8888/collab/elements/" + localStorage.getItem('user-domain') + "/" + localStorage.getItem('user-email'),{

  type:"mall",
  name: name,
  active: true,
  parentElement: {
    elementId: {
        domain: localStorage.getItem('user-domain'),
        id: parent
    }
  },
  elementAttributes: {
    city: city,
    streetName: street,
    streentNum: bulding,
    lat: 30.2,
    lng: 30.1
    }

  })
    .then(function (response) {
      // handle success
      var data = response.data;
      console.log(data);
      showSpecificElement(data);
    })
    .catch(function (error) {
      // handle error
      if(error.response.status == 404 || error.response.status == 500)
          alert(error.response.data.error);
      else
        console.log(error);
    });

}



function startAddStore(){

  startPage();
  var search = document.getElementById("add-store");
  search.style.display = "block";
}


async function addStore(){

  var name = document.getElementById("store-name").value.trim();
  var parent = document.getElementById("store-parent").value.trim();
  var category = document.getElementById("store-category").value.trim();
  var floor = document.getElementById("store-floor").value.trim();

  //http://localhost:8888/collab/elements/2020a.alik/ali@Email.com
  await axios.post("http://localhost:8888/collab/elements/" + localStorage.getItem('user-domain') + "/" + localStorage.getItem('user-email'),{
 
  type:"store",
  name: name,
  active: true,
  parentElement: {
    elementId: {
        domain: localStorage.getItem('user-domain'),
        id: parent
    }
  },
  elementAttributes: {
    category: category,
    likes: "0",
    floor: floor,
 
    }

  })
    .then(function (response) {
      // handle success
      var data = response.data;
      console.log(data);
      showSpecificElement(data);
    })
    .catch(function (error) {
      // handle error
      if(error.response.status == 404 || error.response.status == 500)
        alert(error.response.data.error);
      else
        console.log(error);
    });


    

}



function showSpecificElement(data){

  var table = document.getElementById("show-table");

  clearTable(table);

  table.style.visibility = "visible"
 
  var row = table.insertRow(1);

  var cell0 = row.insertCell(0);
  var cell1 = row.insertCell(1);
  var cell2 = row.insertCell(2);
  var cell3 = row.insertCell(3);
  var cell4 = row.insertCell(4);
  var cell5 = row.insertCell(5);
  var cell6 = row.insertCell(6);
  var cell7 = row.insertCell(7);
  var cell8 = row.insertCell(8);
  var cell9 = row.insertCell(9);


  cell0.innerHTML = data.elementId.id;
  cell1.innerHTML = data.type;
  cell2.innerHTML = data.name;
  cell3.innerHTML = data.active;

  if(data.parentElement == null)
    cell4.innerHTML = "No Parent";
  else
    cell4.innerHTML = data.parentElement.elementId.id;

  cell5.innerHTML = data.createdBy.userId.email;
  cell6.innerHTML = data.createdTimestamp.trim();

  if(data.type == "mall"){
  
    addColumn(table , "Location");
    cell7.innerHTML = data.elementAttributes.streetName+" "+data.elementAttributes.streentNum+", "+data.elementAttributes.city;
  }

  if(data.type == "store"){
    addColumn(table , "category");
    addColumn(table , "likes");
    addColumn(table , "floor");
    cell7.innerHTML = data.elementAttributes.category;
    cell8.innerHTML = data.elementAttributes.likes;
    cell9.innerHTML = data.elementAttributes.floor;
  }

}



function addColumn(table , text){

  var tblHeadObj = table.tHead;
  var newTH = document.createElement('th');
    tblHeadObj.rows[0].appendChild(newTH);
    newTH.innerHTML = text;
 

}


function clearTable(table){
  
  var tableLength = table.rows.length;
  
  if(tableLength > 1){
    var type = table.rows[1].cells[1].innerHTML;
    
    if(type == "mall"){
      table.rows[0].deleteCell(table.rows[0].cells.length-1);
    }
    if(type == "store"){
      table.rows[0].deleteCell(table.rows[0].cells.length-1);
      table.rows[0].deleteCell(table.rows[0].cells.length-1);
      table.rows[0].deleteCell(table.rows[0].cells.length-1);
    }
    
    table.deleteRow(tableLength-1);
  }

}



function addColumns(){
  // ADD COLUMN TO LIST
  var tblHeadObj = document.getElementById("volunteerRequests").tHead;
  for (var h=0; h<tblHeadObj.rows.length; h++) {
    var newTH = document.createElement('th');
    tblHeadObj.rows[h].appendChild(newTH);
    newTH.innerHTML = '[th] row:' + h + ', cell: ' + (tblHeadObj.rows[h].cells.length - 1)
  }
 
  var tblBodyObj = document.getElementById("volunteerRequests").tBodies[0];
  for (var i=0; i<tblBodyObj.rows.length; i++) {
    var newCell = tblBodyObj.rows[i].insertCell(-1);
    newCell.innerHTML = '[td] row:' + i + ', cell: ' + (tblBodyObj.rows[i].cells.length - 1);
  }
 
 }
 