$(function () {
    makeEditable({
            ajaxUrl: "ajax/meals/",
            datatableApi: $("#datatabledeFilter").DataTable({
                "ordering": false,
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "startDate"
                    },
                    {
                        "data": "endDate"
                    },
                    {
                        "data": "startTime"
                    },
                    {
                        "data": "endTime"
                    },
                    {
                        "defaultContent": "Edit",
                        "orderable": false
                    },
                    {
                        "defaultContent": "Delete",
                        "orderable": false
                    }
                ],
                "order": [
                    [
                        0,
                        "asc"
                    ]
                ]
            })
        }
    );
});
var context, form;

function makeEditable(ctx) {
    context = ctx;
    form = $('#datatabledeFilter');

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(jqXHR);
    });

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});
}
function sort() {
    $.ajax({
        type: "GET",
        url: context.ajaxUrl + "filter",
        data: form.serialize()
    }).done(function () {
        $("#datatabledeFilter").modal("hide");
        updateTable();
        successNoty("Filtered");
    });
}
function updateTable() {
    $.get(context.ajaxUrl + "filter", function (data) {
        context.datatableApi.clear().rows.add(data).draw();
    });
}