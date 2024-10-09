// script.js

document.addEventListener("DOMContentLoaded", function () {
    const scrollRight = document.getElementById("scrollRight");
    const productListContainer = document.querySelector(".product-list-container");

    console.log("Initial Scroll Left:", productListContainer.scrollLeft);

    scrollRight.addEventListener("click", function () {
        console.log("Clicked!");
        productListContainer.scrollBy({
            top: 0,
            left: 620,
            behavior: 'smooth'
        });
        console.log("Scroll Left After:", productListContainer.scrollLeft);
    });
});


// document
//     .getElementById("scroller")
//     .addEventListener("scroll", function () {
//         var scrollerWrapper = document.getElementById("scroller");
//         scrollPercent =
//             (scrollerWrapper.scrollLeft /
//                 (scrollerWrapper.scrollWidth - scrollerWrapper.clientWidth)) *
//             100;
//         document.getElementById("scroll-progress").style.width =
//             scrollPercent + "%";
//     });