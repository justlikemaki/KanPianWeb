function ismenuClick0(){
    var that=$(this).next();
    if(that.hasClass("is-visible0")){
        that.removeClass("is-visible0");
    }else {
        that.addClass("is-visible0");
    }
}

function changelist(){
    var list1="";
    var list2="";
    $(".contextlist .singlediv").each(function(index,item){
        if(index%2==0){
            list1=list1+item.outerHTML;
        }else{
            list2=list2+item.outerHTML;
        }
    });
    //重载数据
    $(".contextlist").empty();
    $(".contextlist").append("<div class='streamdiv'>"+list1+"</div>");
    $(".contextlist").append("<div class='streamdiv'>"+list2+"</div>");
    $(".tmpdiv").each(function(index,item){
        $(item).removeClass("tmpdiv");
    });

    //添加监听
    $(".isdownload").on("click",isdownloadclick);

}

changelist();

$("body").on("click",function(){
    $(".mdl-menu-list").removeClass("is-visible0");
});
$(".mdl-card__menu").on("click",function(e){
    e.stopPropagation();
});