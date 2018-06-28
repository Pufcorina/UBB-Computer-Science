$(document).ready(function(){
    $("thead").click(function(){
        var rows=[];
        $("tbody tr").each(function(){
            rows.push($(this));
        });
        var first=$("tbody tr").last();
        console.log(rows);
        $("tbody tr").remove();
        rows.pop()
        console.log(rows);
        rows.push(first);
        $("tbody").append(rows);
        $("tbody").prepend(first);
    })
})