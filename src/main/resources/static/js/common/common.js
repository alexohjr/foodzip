var toggleBtnIcon = $('#sidebar-toggle-btn i');

$(function() {
    $("#sidebar-toggle-btn").click(function() {

        toggleBtnIcon.toggleClass('bx-chevron-left');
        toggleBtnIcon.toggleClass('bx-chevron-right');
        $("#sidebar-tab-area, #sidebar").toggleClass("active");
        $('.tab-content').toggleClass('active');
        $(this).toggleClass("active");
    });

    $('#sidebar button.nav-link').not('active').click(function() {

        $("#sidebar, #sidebar-toggle-btn").addClass("active");
        $('.tab-content').addClass('active');

        if($('#sidebar-toggle-btn').hasClass('active')) {
            toggleBtnIcon.addClass('bx-chevron-left');
            toggleBtnIcon.removeClass('bx-chevron-right');
        } else {
            toggleBtnIcon.addClass('bx-chevron-right');
            toggleBtnIcon.removeClass('bx-chevron-left');
        }

    });

});
