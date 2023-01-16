let toggleBtnIcon = $('#sidebar-toggle-btn i');

$(function() {
    $("#sidebar-toggle-btn").click(function() {

        toggleBtnIcon.toggleClass('bx-chevron-left');
        toggleBtnIcon.toggleClass('bx-chevron-right');
        $("#sidebar-tab-area, #sidebar").toggleClass("active");
        $('.tab-content').toggleClass('active');
        $(this).toggleClass("active");
    });

    $('#sidebar button.nav-link').click(function() {

        if($(this).hasClass('orange') && $('#sidebar').hasClass('active')) {
            $("#sidebar").removeClass("active");
            $('.tab-content').removeClass('active');
            $('#sidebar-toggle-btn').removeClass('active');
        } else if(!$(this).hasClass('orange') && !$('#sidebar').hasClass('active')) {
            $("#sidebar").addClass("active");
            $('.tab-content').addClass('active');
            $('#sidebar-toggle-btn').addClass('active');
            $('#sidebar button.nav-link').removeClass('orange');
            $(this).addClass('orange');
        } else if($(this).hasClass('orange') && !$('#sidebar').hasClass('active')) {
            $("#sidebar").addClass("active");
            $('.tab-content').addClass('active');
            $('#sidebar-toggle-btn').addClass('active');
        } else {
            $('#sidebar button.nav-link').removeClass('orange');
            $(this).addClass('orange');
        }
    });

    $('#sidebar button.nav-link').not('active').click(function() {

        if($('#sidebar-toggle-btn').hasClass('active')) {
            toggleBtnIcon.addClass('bx-chevron-left');
            toggleBtnIcon.removeClass('bx-chevron-right');
        } else {
            toggleBtnIcon.addClass('bx-chevron-right');
            toggleBtnIcon.removeClass('bx-chevron-left');
        }

    });

});
