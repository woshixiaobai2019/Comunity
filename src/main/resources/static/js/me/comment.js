function edit() {
    let id = $("#editIcon").attr("eid");
    $.ajaxSettings.async = false;
    loadContent("/publish",".content");
    $.get("/editQuestion/"+id,function (response) {
        console.log(response)
        if (response.status === "OK"){
            parseEdit(response.response);
        }else{
            if (response.code ===500){
                errorHandle(response.response);
            }
        }
    })
}
function parseEdit(response) {
    $("#title").val(response.title)
    $("#description").val(response.description)
    $("#tag").val(response.tags.join(","))
    $(".btn-publish").html("更新").attr("update","true").attr("eid",response.id);
}
function comment() {
    let parentId = $("#editIcon").attr("eid");
    let type = 0;
    let content = $("#comment-content").val()
    let data = {};
    data["parentId"] = parentId;
    data["type"] = type;
    data["content"] = content;
    $.post("/comment",data,function (response) {
        console.log(response)
        if (response.status ==="OK"){
            showFirstLevelComments(1);
            $("#comment-content").val("")
            alert("评论成功");
        }else{
            alert("内部异常");
        }
    })
}
function showFirstLevelComments(currentPage) {
    //获取数据
    let data = {};
    data["currentPage"]= currentPage;
    data["qId"]= $("#editIcon").attr("eid");
    $.post("/firstLevelComment",data,function (response) {
        console.log(response)
        if (response.status==="OK"){
            parseFirstLevelComments(response.response);
        }else{
            if (response.code ===500){
                errorHandle(response.response);
            }
        }

    })
}
function parseFirstLevelComments(response) {
    let comments = response.objects;
    let divs = "";
    for (let i=0;i<comments.length;i++){
        let div = "<div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12 comment\">\n" +
            "                <div class=\"media\">\n" +
            "                    <div class=\"media-left\">\n" +
            "                        <a href=\"#\">\n" +
            "                            <img class=\"media-object img-rounded img-cache\" src='"+comments[i].avatarUrl+"' alt=\"\">\n" +
            "                        </a>\n" +
            "                    </div>\n" +
            "                    <div class=\"media-body\">\n" +
            "                        <h5 class=\"media-heading\">\n" +
            "                            <span>"+comments[i].username+"</span>\n" +
            "                        </h5>\n" +
            "                        <div>"+comments[i].content+"</div>\n" +
            "                        <div class=\"menu\">\n" +
            "                            <span class=\"glyphicon glyphicon-thumbs-up comment-icon\"></span>\n" +
            "                            <span class=\"glyphicon glyphicon-comment  comment-icon\" onclick=\"showSecondLevelComment("+comments[i].id+")\"><span class=\"commentCount\">"+comments[i].commentCount+"</span></span>\n" +
            "                            <span class=\"pull-right\">\n" +
            "                            "+comments[i].modified+"\n" +
            "                        </span>\n" +
            "<div class=\"second-comment-list collapse\" id='comment-"+comments[i].id+"' expanded=\"false\" needReload='true'>\n" +
            "                                <div class=\"second-comments-list\" >\n" +
            "                                    \n" +
            "                                </div>\n" +
            "                                <label for=\"se-comment-content\"></label><textarea class=\"form-control \" name=\"content\" id=\"se-comment-content\" cols=\"30\" rows=\"3\" style=\"margin: 10px 0\" placeholder=\"评论一下.....\"></textarea>\n" +
            "                                <a class=\"btn-success btn btn-reply \" onclick=\"seComment("+comments[i].id+")\">回复</a>\n" +
            "                            </div>\n"+
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>"
        divs += div;
    }
    $(".comment-list").empty().append(divs);
    let pages = ""
    let showPageList = response.showPageList;
    for (let i=0;i<showPageList.length;i++){
        let page = ""
        if (response.currentPage === showPageList[i]){
            page = "<li class='active show-pages'><a href=\"#\" onclick='showFirstLevelComments("+showPageList[i]+")'>"+showPageList[i]+"</a></li>"
        }else{
            page = "<li class='show-pages'><a href=\"#\" onclick='showFirstLevelComments("+showPageList[i]+")'>"+showPageList[i]+"</a></li>"
        }
        pages += page;
    }
    $("#comment-pagination").empty().append("<li id=\"firstPage\" class=\"page-button\">\n" +
        "                    <a aria-label=\"firstPage\">\n" +
        "                        <span aria-hidden=\"true\">&laquo;</span>\n" +
        "                    </a>\n" +
        "                </li>\n" +
        "                <li id=\"prePage\" class=\"page-button\">\n" +
        "                    <a  aria-label=\"Previous\">\n" +
        "                        <span aria-hidden=\"true\">&lt;</span>\n" +
        "                    </a>\n" +
        "                </li>").append(pages).append("<li id=\"nextPage\" class=\"page-button\">\n" +
        "                    <a  aria-label=\"Next\">\n" +
        "                        <span aria-hidden=\"true\">&gt;</span>\n" +
        "                    </a>\n" +
        "                </li>\n" +
        "                <li id=\"lastPage\" class=\"page-button\">\n" +
        "                    <a  aria-label=\"LastPage\">\n" +
        "                        <span aria-hidden=\"true\">&raquo;</span>\n" +
        "                    </a>\n" +
        "                </li>")
    response.showFirstPageButton?$("#firstPage").css("display","inline"):$("#firstPage").css("display","none");
    response.showLastPageButton?$("#lastPage").css("display","inline"):$("#lastPage").css("display","none");
    response.showNextPageButton?$("#nextPage").css("display","inline"):$("#nextPage").css("display","none");
    response.showPrePageButton?$("#prePage").css("display","inline"):$("#prePage").css("display","none");
    $("#firstPage").bind("click",function () {
        showFirstLevelComments(1)
    })
    $("#lastPage").bind("click",function () {
        showFirstLevelComments(response.totalPage)
    })
    $("#nextPage").bind("click",function () {
        showFirstLevelComments(response.currentPage+1)
    })
    $("#prePage").bind("click",function () {
        showFirstLevelComments(response.currentPage-1)
    })
}

function showSecondLevelComment(parentId) {
    let expand = $("#comment-"+parentId).attr("expanded");
    if (!(expand === true || expand === "true")) {
        let reload = $("#comment-"+parentId).addClass("in").attr("expanded", "true").attr("needReload");
        if (reload===true || reload==="true"){
            getSecondLevelComment(parentId);
            $("#comment-"+parentId).attr("needReload","false");
        }

    } else {
        $("#comment-"+parentId).removeClass("in").attr("expanded", "false")
    }
}
function getSecondLevelComment(parentId){
    //获取数据
    let data = {};
    data["parentId"]= parentId;
    $.post("/secondLevelComment",data,function (response) {
        console.log(response)
        if (response.status==="OK"){
            parseSecondLevelComments(response.response);
        }else{
            if (response.code ===500){
                errorHandle(response.response);
            }
        }

    })
}
function seComment(parentId) {
    let type = 1;
    let content = $("#se-comment-content").val()
    let data = {};
    data["parentId"] = parentId;
    data["type"] = type;
    data["content"] = content;
    $.post("/comment",data,function (response) {
        console.log(response)
        if (response.status ==="OK"){
           getSecondLevelComment(parentId);
            $("#se-comment-content").val("")
            alert("评论成功");
        }else{
            alert("内部异常");
        }
    })
}
function parseSecondLevelComments(response) {
    let divs = ""
    for(let i=0;i<response.length;i++){
        let div = "<div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12 comment\">\n" +
            "    <div class=\"media\">\n" +
            "        <div class=\"media-left\">\n" +
            "            <a href=\"#\">\n" +
            "                <img class=\"media-object img-rounded img-cache\" src='"+response[i].avatarUrl+"' alt=\"\">\n" +
            "            </a>\n" +
            "        </div>\n" +
            "        <div class=\"media-body\">\n" +
            "            <h5 class=\"media-heading\">\n" +
            "                <span>"+response[i].username+"</span>\n" +
            "            </h5>\n" +
            "            <div>"+response[i].content+"</div>\n" +
            "            <div class=\"menu\">\n" +
            "                <span class=\"pull-right\">"+response[i].modified+"</span>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</div>"
        divs += div;
        $(".second-comments-list").empty().append(divs);
    }
}