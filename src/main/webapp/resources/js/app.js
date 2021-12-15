$(document).ready(function () {
    const rHidden = document.getElementById('form:r_hidden');
    let buttons = document.querySelectorAll(".r-group");


    buttons.forEach(click);
    function click(element) {
        element.onclick = function () {
            rHidden.value = this.textContent;
            redrawGraph();
            redrawPoints();
        }
    }
});