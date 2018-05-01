$(document).ready(function(){

    $("#tbUser").on('click','.removeRow',function(){
        var row = $(this).closest('tr');
        row.remove();
        $("#tbUser tr:first").after("<tr class=\"row\">" + 
        "<td></td>" +
        "<td></td>" + 
        "<td></td>" +
        "<td></td>" +
        "<td></td>" +
        "<td></td>" +
        "<td></td>" +
      "</tr>");
        });
    $("#tbUser").on('click','.addRow',function(){
        var row = $(this).closest('tr');
        $("<tr class=\"row\">" + 
            "<td><button class=\"removeRow\">Remove</button></td>" +
            "<td>Insert value</td>" + 
            "<td>Insert value</td>" + 
            "<td>Insert value</td>" + 
            "<td>Insert value</td>" + 
            "<td>Insert value</td>" + 
            "<td><button class=\"addRow\">Add</button></td>" +
          "</tr>").insertAfter(row);
    });

    $('table').on('click', 'td', function(){
        var tdId = $(this).closest('td').index();
        if ( tdId == 0 || tdId == 6)
        {
            return;
        }
        var id = $(this).closest('tr').index();
        var firstName = $('#tbUser tr').eq(id).find('td').eq(1).text();
        var secondName = $('#tbUser tr').eq(id).find('td').eq(2).text();
        var age = $('#tbUser tr').eq(id).find('td').eq(3).text();
        var song = $('#tbUser tr').eq(id).find('td').eq(4).text();
        var movie = $('#tbUser tr').eq(id).find('td').eq(5).text();
        var editable = "Insert value";
        if (firstName == editable || secondName == editable || age == editable || song == editable || movie == editable){
            $(this).closest('td').attr('contenteditable', 'true');
        }
        else
        {
            alert("Row read only!");
            $(this).closest('td').attr('contenteditable', 'false');
        }
    })

});