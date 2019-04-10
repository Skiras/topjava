$(function () {
    makeEditable({
            ajaxUrl: "ajax/meals/",
            datatableApi: $("#datatable").DataTable({
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "dateTime"
                    },
                    {
                        "data": "description"
                    },
                    {
                        "data": "calories"
                    },
                    {
                        "defaultContent": "Update",
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
                        "desc"
                    ]
                ]
            })
        }
    );
});

function filter() {
    $.ajax({
        type: "POST",
        url: context.ajaxUrl + "filter",
        data: $("#filerForm").serialize()
    }).done(function (data) {
        context.datatableApi.clear().rows.add(data).draw();
        successNoty("Filtering");
    });
}

function resetFilter() {
    $.ajax({
        type: "GET",
        url: context.ajaxUrl
    }).done(function (data) {
        context.datatableApi.clear().rows.add(data).draw();
        $("#startDate").val("");
        $("#endDate").val("");
        $("#startTime").val("");
        $("#endTime").val("");
    });
}