/**
 * object가 null인지 확인
 */

function isNull(obj, msg){
		if(obj.value ==''){
			alert(msg)
			obj.focus();
			return true
		}
		return false		
	}