/**
 * Created by Maxwellt on 05.04.2015.
 */
$( document).ready(function() {
    if (untrackedDays > 7) {
        $('[data-toggle="popover"]').popover('show');
        $('body').on('click', function (e) {
            //did not click a popover toggle or popover
            if ($(e.target).data('toggle') !== 'popover'
                && $(e.target).parents('.popover.in').length === 0) {
                $('[data-toggle="popover"]').popover('hide');
            }
        });
    }


});
