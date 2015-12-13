/**
 * Created by L on 2015/9/8.
 */
$(function(){
    $.DrLazyload();
});

function isdownloadclick(){
        $(this).children().children().addClass("is-animating");
        setTimeout(function(){
            $(this).children().children().removeClass("is-animating");
        },1000);
        var btliistdiv= $(this).parent().next();
        if($(btliistdiv).find(".errorli").length>0){
            getbt(this,btliistdiv);
        }else {
            btliistdiv.fadeToggle();
        }
        if($(btliistdiv).find("li").length==0){
            getbt(this,btliistdiv);
        }
}
$(".isdownload").on("click",isdownloadclick);


function regetbt(){
    if(confirm("确定重新获取bt？")) {
        $("body").click();
        var btliistdiv = $(this).parents(".singlediv").find("#isbtlist");
        btliistdiv.fadeIn();
        var thisobj=$(this).parents(".singlediv").find(".isdownload");
        getbt(thisobj, btliistdiv);
    }
}
$(".regetbtbtn").on("click",regetbt);

function getloading(){
    var loading='<div class="mdl-spinner mdl-spinner--single-color mdl-js-spinner is-active is-upgraded" data-upgraded=",MaterialSpinner"><div class="mdl-spinner__layer mdl-spinner__layer-1"><div class="mdl-spinner__circle-clipper mdl-spinner__left"><div class="mdl-spinner__circle"></div></div><div class="mdl-spinner__gap-patch"><div class="mdl-spinner__circle"></div></div><div class="mdl-spinner__circle-clipper mdl-spinner__right"><div class="mdl-spinner__circle"></div></div></div><div class="mdl-spinner__layer mdl-spinner__layer-2"><div class="mdl-spinner__circle-clipper mdl-spinner__left"><div class="mdl-spinner__circle"></div></div><div class="mdl-spinner__gap-patch"><div class="mdl-spinner__circle"></div></div><div class="mdl-spinner__circle-clipper mdl-spinner__right"><div class="mdl-spinner__circle"></div></div></div><div class="mdl-spinner__layer mdl-spinner__layer-3"><div class="mdl-spinner__circle-clipper mdl-spinner__left"><div class="mdl-spinner__circle"></div></div><div class="mdl-spinner__gap-patch"><div class="mdl-spinner__circle"></div></div><div class="mdl-spinner__circle-clipper mdl-spinner__right"><div class="mdl-spinner__circle"></div></div></div><div class="mdl-spinner__layer mdl-spinner__layer-4"><div class="mdl-spinner__circle-clipper mdl-spinner__left"><div class="mdl-spinner__circle"></div></div><div class="mdl-spinner__gap-patch"><div class="mdl-spinner__circle"></div></div><div class="mdl-spinner__circle-clipper mdl-spinner__right"><div class="mdl-spinner__circle"></div></div></div></div>';
    return loading;
}

function getbt(thatis,btliistdiv){
    var that=thatis;
    var sbm= $(that).attr("sbm");
    var mgid= $(that).attr("mgid");
    var thatol=btliistdiv.find("ol")[0];
    var loading= getloading();
    $(thatol).append("<li class='clearnum'><div style=\"float:left\">正在请求数据，请稍后！</div>"+loading+"</li>");
    $(that).off("click");
    $.post('web/getBt',{"searchval":sbm,"mgid":mgid},function(data) {
        if(!!data){
            $(thatol).empty();
            var rejson=eval("("+data+")");
            for(var i=0;i<rejson.length;i++){
                var btname=rejson[i].btname;
                var btlink=rejson[i].btlink;
                if(btlink!="###"){
                    var li="<li><a target=\"_blank\" href=\""+btlink+"\">"+btname+"</a></li>";
                }else{
                    var li="<li class='clearnum'>"+btname+"</li>";
                }
                $(thatol).append(li);
            }
        }else{
            $(thatol).empty();
            var li="<li class='errorli'><div>数据获取异常，请点击重新获取</div></li>";
            $(thatol).append(li);
        }
        $(that).on("click",isdownloadclick);
    });
}

$(function () {
    $(window).scroll(function () {
        //$(window).scrollTop()这个方法是当前滚动条滚动的距离
        //$(window).height()获取当前窗体的高度
        //$(document).height()获取当前文档的高度
        if(ispc()){
            var bot = 320; //bot是底部距离的高度
            if ((bot + $(window).scrollTop()) >= ($(document).height() - $(window).height())) {
                $(".totop").show();
            }
            if($(window).scrollTop()<=300){
                $(".totop").hide();
            }

            var heightc=$(window).scrollTop() - ($(document).height() - $(window).height());
            if(heightc<-180){
                $(".totop").addClass("totop2");
            }else{
                $(".totop").removeClass("totop2");
            }
        }
    });
});

$(".totop").click(function(){
    //window.scrollTo(0,0);
    $("body").animate({
        scrollTop:0
    }, 500 );
});