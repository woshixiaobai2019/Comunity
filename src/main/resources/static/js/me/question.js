$(".btn-publish").click(function () {
    console.log(" test....")
    if (nullCheck(".notNull")){
       if ($(".btn-publish").attr("update")==="true"){
           let eid = $(".btn-publish").attr("eid");
           if (eid!=null && eid!==""){
               $.post("/publish/"+eid,$(".questionForm").serialize(),function (response) {
                   console.log(response);
                   if (response.status==="OK"){
                       alert("更新问题成功");
                   }else{
                       if (response.code ===500){
                           errorHandle(response.response);
                       }else{
                           alert(response.response);
                       }
                   }
               })
           }else{
               alert("内部错误")
           }
       }else{
           $.post("/publish", $(".questionForm").serialize(),function (response) {
               console.log(response);
               if (response.status==="OK"){
                   alert("发布问题成功");
               }else{
                   if (response.code ===500){
                       errorHandle(response.response);
                   }else{
                       alert(response.response);
                   }
               }
           })
       }
    }
})
