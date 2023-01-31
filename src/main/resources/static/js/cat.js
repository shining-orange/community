//获取元素
let cat = document.querySelector('.cat_box');
// 监听鼠标滑动事件，调用事件对象
document.addEventListener('mousemove',function(e){
    cat.style.left = 50+e.pageX+'px'
    cat.style.top = e.pageY+'px'
})