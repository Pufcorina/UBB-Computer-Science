function insertValue(){
    var select = document.getElementById("select"),
    txtVal = document.getElementById("val").value,
    newOption = document.createElement("OPTION"),
    newOptionVal = document.createTextNode(txtVal);

    if(txtVal == ""){
        alert("Empty field!");
        return;
    }
    newOption.appendChild(newOptionVal);
    select.insertBefore(newOption, select.firstChild);
}

function removeValue() {
    value = select.selectedIndex;
    select.removeChild(select[value])
}