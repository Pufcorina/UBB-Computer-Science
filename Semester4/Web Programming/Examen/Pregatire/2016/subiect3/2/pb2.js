$(document).ready(function(){ //when document is completely loaded assign handlers
    $("#btnChangeContent").click(handleBtnChangeContentClick);
});

function handleBtnChangeContentClick(){
    var text = getAllText()
        .replace(/\r?\n|\r/g, '') //the text contains new line characters
        .replace(/[ ]{2,}/, ''); //remove extra spaces
    var strings = divideIntoStringOfEqualLength(text, 6);
    setNewContent(strings, $("img"));
}

function getAllText() {
    return $("p").text();
}

function divideIntoStringOfEqualLength(string, nrOfParts){
    var totalLength = string.length;
    var length = totalLength/nrOfParts;
    var start=0, end=length;
    var result = [];
    for(start = 0, end = length, i=1; i <= nrOfParts; start += length, end+=length, i++){
        if(i === nrOfParts){
            result.push(string.slice(start)) //add remainder of string
        }
        else result.push(string.slice(start, end));
    }
    return result;
}

function setNewContent(strings, backgrounds){
    $(strings).each(function(i){
        var backgroundSrc = $(backgrounds).eq(i).attr("src");
        var div = $("<div/>").text(this).css('background-image', "url('" + backgroundSrc + "')");
        $("body").append(div);
    });
}