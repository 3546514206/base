function Drag(id) {
var oDiv=document.getElementById(id);
	
	var disX=0;
	var disY=0;
	
	oDiv.onmousedown=function (ev)
	{
		var oEvent=ev||event;
		
		disX=oEvent.clientX-oDiv.offsetLeft;
		disY=oEvent.clientY-oDiv.offsetTop;
		
		document.onmousemove=function (ev)
		{
			var oEvent=ev||event;
			var l=oEvent.clientX-disX;
			var t=oEvent.clientY-disY;
			
			if(l<0)
			{
				l=0;
			}
			else if(l>document.documentElement.clientWidth-oDiv.offsetWidth)
			{
				l=document.documentElement.clientWidth-oDiv.offsetWidth;
			}
			
			if(t<0)
			{
				t=0;
			}
			else if(t>document.documentElement.clientHeight-oDiv.offsetHeight)
			{
				t=document.documentElement.clientHeight-oDiv.offsetHeight;
			}
			
			oDiv.style.left=l+'px';
			oDiv.style.top=t+'px';
		};
		
		document.onmouseup=function ()
		{
			document.onmousemove=null;
			document.onmouseup=null;
		};
		
		return true;
	};
       
   
   
    }
    
    function press(){
    	var oDiv = document.getElementById('myaddform');
    	oDiv.style.display = "block";
    	   }
    function del() { 
		 var msg = "您真的确定要删除吗？\n\n请确认！"; 
		 if (confirm(msg)==true){ 
		  return true; 
		 }else{ 
		  return false; 
		 } 
} 
    
	
	function check(){
	var a = document.getElementsByName('replace')[0];
	var b = document.getElementsByName('pass')[0];
	if(a.value == "" && b.value == "")
	document.form1.action="${rootPath }manage/EntryManager"
	else
	document.form1.action="${rootPath}manage/EntryManager_Inquiry"
	}
	
	function su() {
			var aTd = document.getElementsByClassName('sp');
			var id="";
			if(aTd[1].value == 0){
				id = "拨款"
			}else if(aTd[1].value == 0){
				id = "支出"
			}
			if(confirm("请再次确认以下提交信息\n\n"+
			"条目名称:"+aTd[0].value+"\n"+
			"条目类型:"+id
			))
				return true;
			else 
				return false;
			
		}