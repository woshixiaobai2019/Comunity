function loadContent(url,element) {
    $.get(url,function (response) {
        $(element).empty().append(response);
    })
}
function nullCheck(element){
    let elements = $(element)
    if (elements ==null){
        return false;
    }
    for (let i=0;i<elements.length;i++){
        let val = $(elements[i]).val();
        if (val===null || val===""){
            let attr = $(elements[i]).attr("bar");
            attr+="不能为空";
            alert(attr);
            return false;
        }
    }

    return true;
}
function errorHandle(response) {
    loadContent("/exception",".content");
    $("#error-msg").html(response);
}