$(document).ready(function () {

    $(".nav li").on("click", function () {
        $(".nav li").removeClass("active");
        $(this).addClass("active");
    });

    var getPersons = function () {
        $.ajax({
            url: "DataServlet",
            type: "GET",
            dataType: "JSON",
            error: function (errorThrown) {
                alert(errorThrown);
                console.log(errorThrown);
            }
        }).then(function (data) {
            console.log(JSON.parse(data));
            var obj = JSON.parse(data);
            $('#thead').html("");
            $('#tbody').html("");
            var row;
            row = "<tr><td>First name</td><td>Last name</td><td>Email</td><td>Address</td><td>City</td><td>zip</td>\n\
                <td>Phone numbers</td><td>Hobbies</td></tr>";
            $('#thead').append(row);

            for (var i = 0; i < data.length; i++) {
                var phonenumbers = "";
                for (var j = 0; j < obj[i].phonenumbers.length; j++) {
                    phonenumbers += obj[i].phonenumbers[j].description + ": " + obj[i].phonenumbers[j].number + "\n";
                }
                var hobbies = "";
                for (var j = 0; j < obj[i].hobbies.length; j++) {
                    hobbies += obj[i].hobbies[j].name + "\n";
                }
                row = "<tr>" +
                        "<td>" + obj[i].firstname + "</td>" +
                        "<td>" + obj[i].lastname + "</td>" +
                        "<td>" + obj[i].email + "</td>" +
                        "<td>" + obj[i].address + "</td>" +
                        "<td>" + obj[i].city + "</td>" +
                        "<td>" + obj[i].zip + "</td>" +
                        "<td>" + phonenumbers + "</td>" +
                        "<td>" + hobbies + "</td>"
                        + "</tr>";
                $('#tbody').append(row);
            }

        });
    };
//    getPersons();
    $("#pButton").click(getPersons());
});

