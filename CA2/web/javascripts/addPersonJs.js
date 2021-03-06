$(document).ready(function () {

    $(".nav li").on("click", function () {
        $(".nav li").removeClass("active");
        $(this).addClass("active");
    });

    $("#createPButton").click(function () {
        var firstname = $("#firstname").val();
        var lastname = $("#lastname").val();
        var email = $("#email").val();
        var street = $("#street").val();
        var additionalinfo = $("#additionalinfo").val();
        var zip = $("#zip").val();
        var city = $("#city").val();
        var phoneNumber = $("#phoneNumber").val();
        var phoneDesc = $("#phoneDesc").val();

        var jsonOut = {
            "firstname": firstname,
            "lastname": lastname,
            "email": email,
            "address": {
                "street": street,
                "additionalinfo": additionalinfo
            },
            "zip": zip,
            "city": city,
            "phonenumbers": [
                {
                    "number": phoneNumber,
                    "description": phoneDesc
                }
            ],
            "hobbies": [
                {
                    "name": "Ridning",
                    "description": "Rid en tur"
                }
            ]
        };
        var parsed = JSON.stringify(jsonOut);
        postPerson(parsed);

        console.log(parsed);

    });

    var postPerson = function (jsonOut) {

        $.ajax({
            url: "https://localhost:8443/CA2/api/person/complete/poster",
            type: "POST",
            data: jsonOut,
            dataType: "json",
            contentType: "application/json",
            success: function (data, textStatus, jqXHR)
            {
                console.log(data);
            },
            error: function (jqXHR, textStatus, errorThrown)
            {
                console.log(errorThrown);
                console.log(jqXHR);
                console.log(textStatus);
            }
        });
    };

    var globalID = "";
    $('#getpersonbutton').click(function () {
        var id = $('#idgetter').val();
        globalID = id;
        console.log(globalID);
        $.ajax({
            url: "https://localhost:8443/CA2/api/person/complete/" + id,
            type: "GET",
            dataType: "JSON"
        }).then(function (data) {
            console.log(data);
            $('#thead').html("");
            $('#tbody').html("");
            var row;
            row = "<tr><td><h4><b>First name</b></h4></td><td><h4><b>Last name</b></h4></td><td><h4><b>Email</b></h4></td><td><h4><b>Address</b></h4></td><td><h4><b>City</b></h4></td><td><h4><b>zip</b></h4></td>\n\
                <td><h4><b>Phone numbers</b></h4></td><td><h4><b>Hobbies</b></h4></td></tr>";
            $('#thead').append(row);


            var phonenumbers = [];
            for (var j = 0; j < data.phonenumbers.length; j++) {
                phonenumbers += data.phonenumbers[j].description + ": " + data.phonenumbers[j].number + "\n";
            }
            var hobbies = "";
            for (var j = 0; j < data.hobbies.length; j++) {
                hobbies += data.hobbies[j].name + "\n";
            }
            row = "<tr>" +
                    "<td>" + data.firstname + "</td>" +
                    "<td>" + data.lastname + "</td>" +
                    "<td>" + data.email + "</td>" +
                    "<td>" + data.address + "</td>" +
                    "<td>" + data.city + "</td>" +
                    "<td>" + data.zip + "</td>" +
                    "<td>" + phonenumbers + "</td>" +
                    "<td>" + hobbies + "</td>"
                    + "</tr>";
//                $("#test").empty();
            $('#tbody').append(row);
            $('#idgetter').val("");
            $('#deletepersonbutton').show();

        });
    });

    $('#getcompanybutton').click(function () {
        var id = $('#idgetter').val();
        globalID = id;
        console.log(globalID);
        $.ajax({
            url: "https://localhost:8443/CA2/api/company/complete/" + id,
            type: "GET",
            dataType: "JSON"
        }).then(function (data) {
            console.log(data);
            $('#thead').html("");
            $('#tbody').html("");
            var row;
            row = "<tr><td><h4><b>Company name</b></h4></td><td><h4><b>Description</b></h4></td><td><h4><b>Email</b></h4></td><td><h4><b>Address</b></h4></td><td><h4><b>City</b></h4></td><td><h4><b>zip</b></h4></td>\n\
                <td><h4><b>Phone numbers</b></h4></td><td><h4><b>CVR</b></h4></td><td><h4><b>Number of employees</b></h4></td><td><h4><b>Market value</b></h4></td></tr>";
            $('#thead').append(row);


            var phonenumbers = "";
            for (var j = 0; j < data.phonenumbers.length; j++) {
                phonenumbers += data.phonenumbers[j].description + ": " + data.phonenumbers[j].number + "\n";
            }

            row = "<tr>" +
                    "<td>" + data.name + "</td>" +
                    "<td>" + data.description + "</td>" +
                    "<td>" + data.email + "</td>" +
                    "<td>" + data.address + "</td>" +
                    "<td>" + data.city + "</td>" +
                    "<td>" + data.zip + "</td>" +
                    "<td>" + phonenumbers + "</td>" +
                    "<td>" + data.cvr + "</td>" +
                    "<td>" + data.NumEmployees + "</td>" +
                    "<td>" + data.marketValue + "</td>"
                    + "</tr>";
//                $("#test").empty();
            $('#tbody').append(row);
            $('#idgetter').val("");
        });
    });

    $('#deletepersonbutton').click(function () {
        $.ajax({
            url: "https://localhost:8443/CA2/api/person/delete/" + globalID,
            type: "DELETE",
            dataType: "JSON"
        }).then(function (data) {
            alert(data.name + " has been deleted");
            getPersons();
        });
    });

    $("#getpersonbutton2").click(function () {

        getPersonForEdit();
    });

    var getPersonForEdit = function () {
        var id = $('#idgetter2').val();
        globalID = id;
        console.log(globalID);
        $.ajax({
            url: "https://localhost:8443/CA2/api/person/complete/" + id,
            type: "GET",
            dataType: "JSON"
        }).then(function (data) {
            console.log(data);
            $("#firstname").val(data.firstname);
            $("#lastname").val(data.lastname);
            $("#email").val(data.email);
            $("#street").val(data.address.street);
            $("#additionalinfo").val(data.address.additionalinfo);
            $("#zip").val(data.zip);
            $("#city").val(data.city);

            var number = data.phonenumbers[0].number;
            var desc = data.phonenumbers[0].description;

            $("#phoneNumber").val(number);
            $("#phoneDesc").val(desc);
            $("#hobbies").val(data.hobbies[0].name);

        });
    };
    $("#editPButton").click(function () {
        var firstname = $("#firstname").val();
        var lastname = $("#lastname").val();
        var email = $("#email").val();
        var street = $("#street").val();
        var additionalinfo = $("#additionalinfo").val();
        var zip = $("#zip").val();
        var city = $("#city").val();
        var phoneNumber = $("#phoneNumber").val();
        var phoneDesc = $("#phoneDesc").val();
        var hobbies = $("#hobbies").val();
        var id = $("#idgetter2").val();
        var jsonOut = {
            "id": id,
            "firstname": firstname,
            "lastname": lastname,
            "email": email,
            "address": {
                "street": street,
                "additionalinfo": additionalinfo
            },
            "zip": zip,
            "city": city,
            "phonenumbers": [
                {
                    "number": phoneNumber,
                    "description": phoneDesc
                }
            ],
            "hobbies": [
                {
                    "name": hobbies,
                    "description": "Empty"
                }
            ]
        };
        var parsed = JSON.stringify(jsonOut);
        editPerson(parsed);
        console.log(parsed);

    });
    var editPerson = function (jsonOut) {

        $.ajax({
            url: "https://localhost:8443/CA2/api/person/editperson",
            type: "PUT",
            data: jsonOut,
            dataType: "json",
            contentType: "application/json",
            success: function (data, textStatus, jqXHR)
            {
                console.log(data);
            },
            error: function (jqXHR, textStatus, errorThrown)
            {
                console.log(errorThrown);
                console.log(jqXHR);
                console.log(textStatus);
            }
        }).then(function () {
            $("#addnew").hide();
            $(".nav li").removeClass("active");
            $("#pList").addClass("active");
            $("#test").show(getPersons());
        });

    };


    $("#addButton").click(function () {
        $("#delete").hide();
        $("#addedit").show();
    });

    $("#deleteButton").click(function () {
        $("#addedit").hide();
        $("#delete").show();
    });

});