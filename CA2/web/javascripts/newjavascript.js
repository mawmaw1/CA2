$(document).ready(function () {
    $("#addnew").hide();
    $("#deletepersonbutton").hide();


    $(".nav li").on("click", function () {
        $(".nav li").removeClass("active");
        $(this).addClass("active");
    });

    var getPersons = function () {
        $.ajax({
            url: "/api/person/complete",
            type: "GET",
            dataType: "json",
            beforeSend: function () {
                myApp.showPleaseWait();
            },
            error: function (errorThrown) {
                myApp.hidePleaseWait();
                alert(errorThrown);
                console.log(errorThrown);
            }
        }).then(function (data) {
            myApp.hidePleaseWait();
            console.log(data);
            $('#thead').html("");
            $('#tbody').html("");
            var row;
            row = "<tr>\n\
            <td><h4><b>First name</b></h4></td><td><h4><b>Last name</b></h4></td><td><h4><b>Email</b></h4></td><td><h4><b>Address</b></h4></td><td><h4><b>City</b></h4></td><td><h4><b>zip</b></h4></td>\n\
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
        $("#addnew").hide();
        $("#home").hide();
        $("#test").show(getPersons());
        $('#getcompanybutton').hide();
        $('#getpersonbutton').show();
        $('#deletepersonbutton').hide();
    });


    var getCompanies = function () {
        $.ajax({
            url: "/api/company/complete",
            type: "GET",
            dataType: "json",
            beforeSend: function () {
                myApp.showPleaseWait();
            },
            error: function (errorThrown) {
                alert(errorThrown);
                console.log(errorThrown);
                myApp.hidePleaseWait();
            }
        }).then(function (data) {
            myApp.hidePleaseWait();
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
        $("#test").hide();
        $("#home").hide();
        $("#addnew").hide();
        $("#test").show(getCompanies());
        $('#getcompanybutton').show();
        $('#getpersonbutton').hide();
    });

    $("#homeButton").click(function () {
        $("#test").hide();
        $("#addnew").hide();
        $("#home").show();
    });
    $("#aButton").click(function () {
        $("#home").hide();
        $("#test").hide();
        $("#addnew").show();

    });
    var globalID = "";
    $('#getpersonbutton').click(function () {
        var id = $('#idgetter').val();
        globalID = id;
        console.log(globalID);
        $.ajax({
            url: "/api/person/complete/" + id,
            type: "GET",
            dataType: "JSON",
            beforeSend: function () {
                myApp.showPleaseWait();
            }

        }).done(function (data) {
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
            myApp.hidePleaseWait();
        }).fail(function (error) {
            myApp.hidePleaseWait();
            alert("Person not found: " + error.status);
        });
    });

    $('#getcompanybutton').click(function () {
        var id = $('#idgetter').val();
        globalID = id;
        console.log(globalID);
        $.ajax({
            url: "/api/company/complete/" + id,
            type: "GET",
            dataType: "JSON",
            beforeSend: function () {
                myApp.showPleaseWait();
            }
        }).then(function (data) {
            myApp.hidePleaseWait();
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
        }).fail(function (error) {
            myApp.hidePleaseWait();
            alert("Person not found: " + error.status);
        });
    });

    $('#deletepersonbutton').click(function () {
        $.ajax({
            url: "/api/person/delete/" + globalID,
            type: "DELETE",
            dataType: "JSON"
        }).then(function (data) {
            alert(data.name + " has been deleted");
            getPersons();
        });
    });

//    $("#createPButton").click(function () {
//        var personForm = $('#createPerson').serializeArray();
//
//    });

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
        var hobbies = $("#hobbies").val();

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
                    "name": hobbies,
                    "description": "Empty"
                }
            ]
        };
        var parsed = JSON.stringify(jsonOut);
        postPerson(parsed);

        console.log(parsed);

    });

    var postPerson = function (jsonOut) {

        $.ajax({
            url: "/api/person/complete/poster",
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
        }).then(function () {
            $("#addedit").hide();
            $(".nav li").removeClass("active");
            $("#pList").addClass("active");
            $("#test").show(getPersons());
        });

    };

    $("#getpersonbutton2").click(function () {
        
        getPersonForEdit();
    });

    var getPersonForEdit = function () {
        var id = $('#idgetter2').val();
        globalID = id;
        console.log(globalID);
        $.ajax({
            url: "/api/person/complete/" + id,
            type: "GET",
            dataType: "JSON",
            beforeSend: function () {
                myApp.showPleaseWait();
            }
        }).then(function (data) {
            myApp.hidePleaseWait();
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

        }).fail(function (error) {
            myApp.hidePleaseWait();
            alert("Person not found: " + error.status);
        });
        ;
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
            url: "/api/person/editperson",
            type: "PUT",
            data: jsonOut,
            dataType: "json",
            contentType: "application/json",
            success: function (data, textStatus, jqXHR)
            {
                console.log(data.firstname);


            },
            error: function (jqXHR, textStatus, errorThrown)
            {
                console.log(errorThrown);
                console.log(jqXHR);
                console.log(textStatus);
            }
        }).then(function () {
            $("#addedit").hide();
            $("#delete").show(getPersons());
            $("#idgetter").hide();
            $("#getpersonbutton").hide();
            $("#getcompanybutton").hide();
            $("#deletepersonbutton").hide();
            console.log("test2");
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

    var myApp;
    myApp = myApp || (function () {
        var pleaseWaitDiv = $('<div class="modal" id="pleaseWaitDialog" data-backdrop="static"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h1>Processing...</h1></div><div class="modal-body"><div class="progress progress-striped active"><div class="progress-bar" style="width: 100%;"></div></div></div></div></div></div>');
        return {
            showPleaseWait: function () {
                pleaseWaitDiv.modal();
            },
            hidePleaseWait: function () {
                pleaseWaitDiv.modal('hide');
            },
        };
    })();

});
