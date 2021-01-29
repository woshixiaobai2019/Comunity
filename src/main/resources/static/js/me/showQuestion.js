function showQuestion(currentPage) {
    //获取数据
    let data = {};
    data["currentPage"]= currentPage;
    $.post("/getQuestion",data,function (response) {
        console.log(response)
        if (response.status==="OK"){
            parseQuestions(response.response);
        }else{
            alert("获取数据错误")
        }

    })
}
function showMyQuestion(currentPage) {
    //获取数据
    let data = {};
    data["currentPage"]= currentPage;
    $.post("/profile/question",data,function (response) {
        console.log(response)
        if (response.status==="OK"){
            parseQuestions(response.response);
        }else{
            alert("获取数据错误")
        }

    })
}

function parseQuestions(response) {
    let questions = response.questions;
    let questionDivs = ""
    for (let i=0;i<questions.length;i++){
        let questionDiv = "<div class=\"question\" id='"+questions[i].id+"'>\n" +
            "            <div class=\"media\">\n" +
            "                <div class=\"media-left\">\n" +
            "                    <a href=\"#\">\n" +
            "                        <img src='"+questions[i].avatarUrl+"' class=\"media-object img-cache img-rounded\" alt=\"\">\n" +
            "                    </a>\n" +
            "                </div>\n" +
            "                <div class=\"media-body\">\n" +
            "                    <h4 class=\"media-heading\">"+questions[i].title+"</h4>\n" +
            "                    "+questions[i].description+"<br>\n" +
            "                    <span class=\"text-desc\">"+questions[i].commentCount+"个回复 · "+questions[i].viewCount+"次浏览 · 1小时以前</span>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </div>"
        questionDivs+=questionDiv;
    }
    $(".questions").empty().append(questionDivs);
    let pages = ""
    let showPageList = response.showPageList;
    for (let i=0;i<showPageList.length;i++){
        let page = ""
        if (response.currentPage === showPageList[i]){
            page = "<li class='active show-pages'><a href=\"#\" onclick='showQuestion("+showPageList[i]+")'>"+showPageList[i]+"</a></li>"
        }else{
            page = "<li class='show-pages'><a href=\"#\" onclick='showQuestion("+showPageList[i]+")'>"+showPageList[i]+"</a></li>"
        }
        pages += page;
    }
    $("#question-pagination").empty().append("<li id=\"firstPage\" class=\"page-button\">\n" +
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
        showQuestion(1)
    })
    $("#lastPage").bind("click",function () {
        showQuestion(response.totalPage)
    })
    $("#nextPage").bind("click",function () {
        showQuestion(response.currentPage+1)
    })
    $("#prePage").bind("click",function () {
        showQuestion(response.currentPage-1)
    })
    $(".question").bind("click",function () {
        $.ajaxSettings.async = false;
        let questionId = $(this).attr("id");
        console.log(questionId);
        loadContent("/questionDetail",".content");
        //加载数据
        getQuestionDetail(questionId)
        $.ajaxSettings.async = true;
    })
}

function getQuestionDetail(id) {
    $.get("/getQuestionDetail/"+id,function (response) {
        console.log(response);
        if (response.status==="OK"){
            parseDetail(response.response);
        }else{
            alert("内部错误");
        }
    })
}

function parseDetail(response) {
    $(".question-detail-username").html(response.username);
    $("#question-detail-avatar").attr("src",response.avatarUrl);
    $("#question-detail-desc").html(response.description);
    $("#question-detail-publishDate").html(response.modified);
    $("#question-detail-title").html(response.title);
    $("#question-detail-viewCount").html(response.viewCount);
    if (response.editable){
        $("#editIcon").css("display","inline").attr("eid",response.id);
    }else{
        $("#editIcon").css("display","none");
    }
}

