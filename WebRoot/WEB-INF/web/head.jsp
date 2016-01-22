<%@ page language="java"  pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<header class="mdl-layout__header mdl-layout__header--scroll mdl-color--primary ">
	<div class="mdl-layout--large-screen-only mdl-layout__header-row">
	</div>
	<div class="mdl-layout--large-screen-only mdl-layout__header-row">
		<h3>Is Your AV</h3>
	</div>
	<div class="mdl-layout--large-screen-only mdl-layout__header-row">
	</div>
	<div class=" mdl-layout__tab-bar-container"><div class="mdl-layout__tab-bar-button mdl-layout__tab-bar-left-button"><i class="material-icons">chevron_left</i></div><div class="tabbar mdl-layout__tab-bar mdl-js-ripple-effect mdl-color--primary-dark mdl-js-ripple-effect--ignore-events" data-upgraded=",MaterialRipple">
		<li id="newspage" class="mdl-layout__tab ${tab eq 'newspage' ? 'is-active' : ''}">最新<span class="mdl-layout__tab-ripple-container mdl-js-ripple-effect" data-upgraded=",MaterialRipple"><span class="mdl-ripple"></span></span></li>
		<li id="censored" class="mdl-layout__tab ${tab eq 'censored' ? 'is-active' : ''}">有码<span class="mdl-layout__tab-ripple-container mdl-js-ripple-effect" data-upgraded=",MaterialRipple"><span class="mdl-ripple"></span></span></li>
		<li id="uncensored" class="mdl-layout__tab ${tab eq 'uncensored' ? 'is-active' : ''}">无码<span class="mdl-layout__tab-ripple-container mdl-js-ripple-effect" data-upgraded=",MaterialRipple"><span class="mdl-ripple"></span></span></li>
		<li id="westporn" class="mdl-layout__tab ${tab eq 'westporn' ? 'is-active' : ''}">欧美<span class="mdl-layout__tab-ripple-container mdl-js-ripple-effect" data-upgraded=",MaterialRipple"><span class="mdl-ripple"></span></span></li>
		<li id="classical" class="mdl-layout__tab ${tab eq 'classical' ? 'is-active' : ''}">经典<span class="mdl-layout__tab-ripple-container mdl-js-ripple-effect" data-upgraded=",MaterialRipple"><span class="mdl-ripple"></span></span></li>
		<li id="getbtlist" class="mdl-layout__tab ${tab eq 'getbtlist' ? 'is-active' : ''}">找种<span class="mdl-layout__tab-ripple-container mdl-js-ripple-effect" data-upgraded=",MaterialRipple"><span class="mdl-ripple"></span></span></li>
	</div></div>
</header>
<script type="text/javascript">
	$(".mdl-layout__tab").click(function(e){
		window.location.href="web/"+$(this).attr("id");
	});
</script>
