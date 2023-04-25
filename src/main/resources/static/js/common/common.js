const sidebarToggleBtn = $('#sidebar-toggle-btn');
const toggleBtnIcon = $('#sidebar-toggle-btn i');
const sidebar = $('#sidebar');
const sidebarMenu = $('#sidebar button.nav-link');
const sidebarContent = $('.tab-content');

let sidebarElemArr = [sidebar, sidebarToggleBtn, sidebarContent];

$(function() {
    sidebarActiveTrigger();

    sidebarToggleBtn.click(function() {
        sidebar.trigger('classChanged');
    });

    sidebarMenu.click(function() {

        if($(this).hasClass('active-menu') || !sidebar.hasClass('active')) {
            sidebar.trigger('classChanged');
        }
        sidebarMenu.removeClass('active-menu');
        $(this).addClass('active-menu');
        addMarker.setMap(null);
    });
});


function sidebarActiveTrigger() {

    sidebar.on('classChanged', function() {
        sidebarElemArr.forEach(function(elem) {
            elem.toggleClass('active');
        });
        toggleBtnIcon.toggleClass('bx-chevron-left, bx-chevron-right');
    });

}
