// 滚动标题
setInterval(function() {
    var tag = document.getElementById('title');//根据id获取元素
    var content = tag.innerText; // 获取title标签的文本内容
    var firstStr = content.charAt(0); // 获取第一个字
    var surplue = content.substring(1, content.length); // 删除第一个字
    var new_content = surplue + firstStr; // 把第一个字加到最后面
    tag.innerText = new_content;
}, 1000);