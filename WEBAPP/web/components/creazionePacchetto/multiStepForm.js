//Step del form
jQuery().ready(function () {
    let v = $("#form")
    $(".open1").click(function () {
        console.log('hello')
        if(v) {
            $(".frm").hide("fast");
            $("#sf2").show("slow");
        }
    });
    $(".open2").click(function () {
        if(v) {
            $(".frm").hide("fast");
            $("#sf3").show("slow");
        }
    });
    $(".open3").click(function () {
        if(v) {
            $(".frm").hide("fast");
            $("#sf4").show("slow");
        }
    });
    $(".back2").click(function () {
        if(v) {
            $(".frm").hide("fast");
            $("#sf1").show("slow");
        }
    });
    $(".back3").click(function () {
        if(v) {
            $(".frm").hide("fast");
            $("#sf2").show("slow");
        }
    });
    $(".back4").click(function () {
        if(v) {
            $(".frm").hide("fast");
            $("#sf3").show("slow");
        }
    });
    $(".open4").click(function () {
        if(v) {
            return false;
        }
    });

})