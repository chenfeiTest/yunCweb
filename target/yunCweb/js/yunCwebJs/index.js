$(function(){
	$("#saveData").on("click",index.saveData);
})
var index = {
	saveData:function(){
		var ckContext = CKEDITOR.instances.editor1.getData();
		console.info('内容是'+ckContext);
		$.ajax({
			url:"index/saveCkInfo",
			type:"POST",
			data:{"ckContext":ckContext},
			success:function(data){
				console.info("成功");
			}
		})
	},	
}