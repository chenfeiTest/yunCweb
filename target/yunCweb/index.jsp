<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://ckfinder.com" prefix="ckfinder" %>  
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	</head>
	<body>
		welcome to here!
		<form>
            <textarea name="editor1" id="editor1" rows="10" cols="80">
                This is my textarea to be replaced with CKEditor.
            </textarea>
            <input type="button" value="保存" id="saveData"/>
        </form>
	</body>
	<script src="js/jquery/jquery-1.8.3.js"></script>
	<script src="js/yunCwebJs/index.js"></script>
	<script src="js/ckeditor/ckeditor.js"></script>
	<script src="ckfinder/ckfinder.js"></script>
	<script>
		var editor = CKEDITOR.replace( 'editor1', {
	    	customConfig: 'js/ckeditor/config.js'
		});
		CKFinder.setupCKEditor( editor, 'ckfinder/' );
	</script>
</html>