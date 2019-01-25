function common_onmUserView(userId)
{
	document.topForm.userId.value = userId;

	document.topForm.action = "/onm/user/view.ktgw";
	document.topForm.method = "post";
	document.topForm.target = "_self";
	document.topForm.submit();
}

/* --------------------------------------------------------------------------------------------------------------------------------------- */

function checkNullEmpty(value)
{
	if(value == null || value == "")
	{
		return true;
	}
	else
	{
		return false;
	}
}

function checkEnglishNumber(value)
{
	for(var i = 0; i < value.length; i++)
	{
		if(!((value.charAt(i) >= '0' && value.charAt(i) <= '9') || (value.charAt(i) >= 'a' && value.charAt(i) <= 'z')))
		{
			return false;
		}
	}

	return true;
}

function checkEnglishWithNumber(value)
{
	for(var i = 0; i < value.length; i++)
	{
		if(!((value.charAt(i) >= '0' && value.charAt(i) <= '9') || (value.charAt(i) >= 'a' && value.charAt(i) <= 'z')))
		{
			return false;
		}
	}

	var numberExitCheck = false;

	for(var i = 0; i < value.length; i++)
	{
		if(value.charAt(i) >= '0' && value.charAt(i) <= '9')
		{
			numberExitCheck = true;

			break;
		}
	}

	var englishExitCheck = false;

	for(var i = 0; i < value.length; i++)
	{
		if(value.charAt(i) >= 'a' && value.charAt(i) <= 'z')
		{
			englishExitCheck = true;

			break;
		}
	}

	if(!numberExitCheck || !englishExitCheck)
	{
		return false;
	}

	return true;
}

function checkIp(value)    
{   
    var iplength = (value.split(/\./)).length;   
       
    if( iplength == 4 ){   
        if (value.search(/^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/) != -1) { // ip 4   
            var myArray = value.split(/\./);   
            if (parseInt(myArray[0],10) > 255 || parseInt(myArray[1],10) > 255 || parseInt(myArray[2],10) > 255 || parseInt(myArray[3],10) > 255){   
                return false;   
            }   
               
            if ( parseInt(myArray[0],10) == 0 && parseInt(myArray[1],10) == 0 && parseInt(myArray[2],10) == 0 && parseInt(myArray[3],10) == 0){   
                return false;   
            }   
                   
            return true;   
        } else {   
            return false;   
        }   
    } else if( iplength == 6 ){   
        if (value.search(/^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}.\d{1,3}.\d{1,3}$/) != -1) { // ip 6   
            var myArray2 = value.split(/\./);   
            alert(myArray2);   
            if (parseInt(myArray2[0],10) > 255 || parseInt(myArray2[1],10) > 255 || parseInt(myArray2[2],10) > 255 || parseInt(myArray2[3],10) > 255 || parseInt(myArray2[4],10) > 255 || parseInt(myArray2[5],10) > 255){   
                return false;   
            }   
               
            if ( parseInt(myArray2[0],10) == 0 && parseInt(myArray2[1],10) == 0 && parseInt(myArray[2],10) == 0 && parseInt(myArray2[3],10) == 0 && parseInt(myArray2[4],10) == 0 && parseInt(myArray2[5],10) == 0){   
                return false;   
            }   
               
            return true;   
        } else {   
            return false;   
        }   
    } else {   
        return false;   
    }   
}

function checkId(value)
{
  var regG = /[-a-zA-Z0-9_-]/;
  for(i=0;i<value.length;i++)
  {
    var str = value.substr(i,1);
    
    if(!regG.test(str))
    {
      alert("입력이 불가능한 문자입니다. : " + str);
      return false;
    }
  }
  return true;
}

function deleteSpace(value)
{
	return value.replace(/ /g, "");
}

function trim(value)
{
	return value.replace(/^\s*/, '').replace(/\s*$/, '');
}

function checkLengthOver(value, maxLength)
{
	if(value.length <= maxLength)
	{
		return true;
	}
	else
	{
		return false;
	}
}

function checkByteOver(value, byteSize)
{
	var valueByteSize = getByteSize(value);

	if(valueByteSize >= byteSize)
	{
		return true;
	}
	else
	{
		return false;
	}
}

function checkLengthBetween(value, minLength, maxLength)
{
	if(minLength <= value.length && value.length <= maxLength)
	{
		return true;
	}
	else
	{
		return false;
	}
}

function getByteSize(value)
{
	var byteSize = 0;

	for(var i = 0; i < value.length; i++)
	{
		if(value.charCodeAt(i) > 255)
		{
			byteSize += 2;
		}
		else
		{
			byteSize += 1;
		}
	}

	return byteSize;
}

function checkEmailFormat(value)
{
	var filter = /^([a-z]+)([a-z0-9\-\_\.]{1,100})([a-z0-9]+)\@([a-z0-9]+)([a-z0-9\-\.]*)([a-z0-9]+)\.([a-z]{2,6})$/;

	if (value.search(filter) != -1)
		return true;
	else
		return false;
}

function getCheckedValue(radioObj) {
	if(!radioObj)
		return "";
	var radioLength = radioObj.length;
	if(radioLength == undefined)
		if(radioObj.checked)
			return radioObj.value;
		else
			return "";
	for(var i = 0; i < radioLength; i++) {
		if(radioObj[i].checked) {
			return radioObj[i].value;
		}
	}
	return "";
}

function setCheckedValue(radioObj, newValue) {
	if(!radioObj)
		return;
	var radioLength = radioObj.length;
	if(radioLength == undefined) {
		radioObj.checked = (radioObj.value == newValue.toString());
		return;
	}
	for(var i = 0; i < radioLength; i++) {
		radioObj[i].checked = false;
		if(radioObj[i].value == newValue.toString()) {
			radioObj[i].checked = true;
		}
	}
}

function checkPwdAbc(value)
{
	var abcd = " ";
	var k = 0;
	for(var i = 0; i < value.length; i++) {
		if ((abcd.charCodeAt(k) + 1) == value.charCodeAt(i)) {
			k++;
			if (k < 2) {
				abcd = abcd + value.charAt(i);
			} else {
				return false;
			}
		} else {
			k = 0;
			abcd = value.charAt(i);
		}
	}
	return true;
}

function checkPwdAaa(value)
{
	var abcd = {};
	var k = 0;
	for(var i = 0; i < value.length; i++) {
		if (abcd[k] == value.charAt(i)) {
			k++;
			if (k < 2) {
				abcd[k] = value.charAt(i);
			} else {
				return false;
			}
		} else {
			k = 0;
			abcd[k] = value.charAt(i);
		}
	}
	return true;
}

// 달력창 띄우기(달력 하나)
function openCalendar()
{
    returnValue = showModalDialog( '/common/util/calendar.html', 'calendar',
                               'dialogWidth:210px;dialogHeight:242px;help:0;status:no;' );

    if( returnValue != null )
    {
        document.forms[1].resvDate.value = returnValue;
    }
}


// 달력창 띄우기(기간지정 : OO부터)
function openFromCalendar()
{
    returnValue = showModalDialog( '/common/util/calendar.html', 'calendar',
                               'dialogWidth:210px;dialogHeight:242px;help:0;status:no;' );
  
    if( returnValue != null )
    {
        document.forms[0].fromDate.value = returnValue;
    }
}


// 달력창 띄우기(기간지정 : OO까지)
function openUntilCalendar()
{
    returnValue = showModalDialog( '/common/util/calendar.html', 'calendar',
                               'dialogWidth:210px;dialogHeight:242px;help:0;status:no;' );

    if( returnValue != null )
        document.forms[0].untilDate.value = returnValue;
}

// 달력창 띄우기(기간지정 : OO까지)
function openAcceptCalendar()
{
    returnValue = showModalDialog( '/common/util/calendar.html', 'calendar',
                               'dialogWidth:210px;dialogHeight:242px;help:0;status:no;' );

    if( returnValue != null )
        document.forms[1].acceptDate.value = returnValue;
}

function trim(str){
	return str.replace(/(^\s*)|(\s*$)/gi,"");
}

function replaceAll(str, str1, str2){
	var temp_str = trim(str);
	temp_str = temp_str.replace(eval("/"+str1+"/gi"), str2);
	return temp_str;
}

function sReplace_str(str, s1, s2)  {  
    
for (i=0; i< str.length; i++)         
	str = str.replace(s1,s2);  
  
return str;  
}
