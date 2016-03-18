$(document).ready(function () {
    $("#editPButton").click(function () {
        window.location.href = "index.html";
        $(".nav li").removeClass("active");
        $("#pButton").addClass("active");
        
        $("#pButton").click(function () {
//        $("#test1").hide();
            $("#addnew").hide();
            $("#home").hide();
            $("#test").show(getPersons());
            $('#getcompanybutton').hide();
            $('#getpersonbutton').show();
            $('#deletepersonbutton').hide();
        });
    });
});