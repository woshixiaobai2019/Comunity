$(".btn-publish").click(function () {
    console.log(" test....")
    if (nullCheck(".notNull")){
       $.post("/publish", $(".questionForm").serialize(),function (response) {
            console.log(response);
            if (response.status==="OK"){
                alert("发布问题成功");
            }else{
                alert(response.response);
            }
       })
    }
})
