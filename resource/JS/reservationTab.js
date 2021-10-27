const tab_btn = document.querySelectorAll(".cont_block");
const tab_cont = document.querySelectorAll(".event_cont");
const tab1 = document.querySelector(".tab1");
const tab2 = document.querySelector(".tab2");

let index = 0;

tab1.addEventListener("click", function(){
    showTab1();
});
tab2.addEventListener("click", function(){
    showTab2();
});

function showTab1(){
    index = 0;
    changeTab();
}
function showTab2(){
    index = 1;
    changeTab();
}
function changeTab(){
    tab_btn.forEach(function(item){
        item.classList.remove("selected");
    });

    tab_btn[index].classList.add("selected");

    tab_cont.forEach(function(item){
        item.classList.remove("selected");
    });

    tab_cont[index].classList.add("selected");

}