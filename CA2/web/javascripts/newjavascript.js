$(document).ready(function (){
   
    var getPersons = function () {
        $.ajax({
            url: "http://localhost:8080/CA2/api/person/complete",
            type: "GET",
            dataType: "JSON",
            error : function (errorThrown){
                alert(errorThrown);
                console.log(errorThrown);
            }
        }).then(function (data) {
            console.log(data);
            $('#thead').html("");
            $('#tbody').html("");
            var row;
            row = "<tr><td>First name</td><td>Last name</td><td>Email</td><td>Address</td><td>City</td><td>zip</td>\n\
                <td>Phone numbers</td><td>Hobbies</td></tr>";
            $('#thead').append(row);
            
            for (var i = 0; i < data.length; i++) {
                var phonenumbers = "";
                for (var j = 0; j < data[i].phonenumbers.length; j++) {
                    phonenumbers += data[i].phonenumbers[j].description + ": " + data[i].phonenumbers[j].number + "\n";
                }
                var hobbies = "";
                for (var j = 0; j < data[i].hobbies.length; j++) {
                    hobbies += data[i].hobbies[j].name+"\n";
                }
                row = "<tr>" +
                        "<td>" + data[i].firstname + "</td>" + 
                        "<td>" + data[i].lastname + "</td>" + 
                        "<td>" + data[i].email + "</td>" + 
                        "<td>" + data[i].address + "</td>" + 
                        "<td>" + data[i].city + "</td>"  +
                        "<td>" + data[i].zip + "</td>" +
                        "<td>" + phonenumbers + "</td>" +
                        "<td>" + hobbies + "</td>" 
                        + "</tr>";
                $('#tbody').append(row);
            }

        });
    };
    getPersons();
});

