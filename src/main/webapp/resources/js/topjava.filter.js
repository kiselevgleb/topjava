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