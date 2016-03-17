$(document).ready(function () {

    $(".nav li").on("click", function () {
        $(".nav li").removeClass("active");
        $(this).addClass("active");
    });

    var getPersons = function () {
        $.ajax({
            url: "http://localhost:8080/CA2/api/person/complete",
            dataType: "json",
            error: function (errorThrown) {
                alert(errorThrown);
                console.log(errorThrown);
            }
        }).then(function (data) {
            console.log(data);
            $('#thead').html("");
            $('#tbody').html("");
            var row;
            row = "<tr><td><h4><b>First name</b></h4></td><td><h4><b>Last name</b></h4></td><td><h4><b>Email</b></h4></td><td><h4><b>Address</b></h4></td><td><h4><b>City</b></h4></td><td><h4><b>zip</b></h4></td>\n\
                <td><h4><b>Phone numbers</b></h4></td><td><h4><b>Hobbies</b></h4></td></tr>";
            $('#thead').append(row);

            for (var i = 0; i < data.length; i++) {
                var phonenumbers = [];
                for (var j = 0; j < data[i].phonenumbers.length; j++) {
                    phonenumbers += data[i].phonenumbers[j].description + ": " + data[i].phonenumbers[j].number + "\n";
                }
                var hobbies = "";
                for (var j = 0; j < data[i].hobbies.length; j++) {
                    hobbies += data[i].hobbies[j].name + "\n";
                }
                row = "<tr>" +
                        "<td>" + data[i].firstname + "</td>" +
                        "<td>" + data[i].lastname + "</td>" +
                        "<td>" + data[i].email + "</td>" +
                        "<td>" + data[i].address + "</td>" +
                        "<td>" + data[i].city + "</td>" +
                        "<td>" + data[i].zip + "</td>" +
                        "<td>" + phonenumbers + "</td>" +
                        "<td>" + hobbies + "</td>"
                        + "</tr>";
//                $("#test").empty();
                $('#tbody').append(row);
            }

        });
    };
//    getPersons();
    $("#pButton").click(function () {
//        $("#test1").hide();
        $("#home").hide();
        $("#test").show(getPersons());
    });


    var getCompanies = function () {
        $.ajax({
            url: "http://localhost:8080/CA2/api/company/complete",
            dataType: "json",
            error: function (errorThrown) {
                alert(errorThrown);
                console.log(errorThrown);
            }
        }).then(function (data) {
            console.log(data);
            $('#thead').html("");
            $('#tbody').html("");
            var row;
            row = "<tr><td><h4><b>Company name</b></h4></td><td><h4><b>Description</b></h4></td><td><h4><b>Email</b></h4></td><td><h4><b>Address</b></h4></td><td><h4><b>City</b></h4></td><td><h4><b>zip</b></h4></td>\n\
                <td><h4><b>Phone numbers</b></h4></td><td><h4><b>CVR</b></h4></td><td><h4><b>Number of employees</b></h4></td><td><h4><b>Market value</b></h4></td></tr>";
            $('#thead').append(row);

            for (var i = 0; i < data.length; i++) {
                var phonenumbers = "";
                for (var j = 0; j < data[i].phonenumbers.length; j++) {
                    phonenumbers += data[i].phonenumbers[j].description + ": " + data[i].phonenumbers[j].number + "\n";
                }
                
                row = "<tr>" +
                        "<td>" + data[i].name + "</td>" +
                        "<td>" + data[i].description + "</td>" +
                        "<td>" + data[i].email + "</td>" +
                        "<td>" + data[i].address + "</td>" +
                        "<td>" + data[i].city + "</td>" +
                        "<td>" + data[i].zip + "</td>" +
                        "<td>" + phonenumbers + "</td>" +
                        "<td>" + data[i].cvr + "</td>" +
                        "<td>" + data[i].NumEmployees + "</td>" +
                        "<td>" + data[i].marketValue + "</td>"
                        + "</tr>";
//                $("#test").empty();
                $('#tbody').append(row);
            }

        });
    };
//    getPersons();
    $("#cButton").click(function () {
//        $("#test").hide();
        $("#home").hide();
        $("#test").show(getCompanies());
    });
    
    $("#homeButton").click(function(){
        $("#test").hide();
        $("#home").show();
    });
});

