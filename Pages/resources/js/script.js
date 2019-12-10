
function id(id) {
    return  document.getElementById(id);
}
function clase(clase) {
    return  document.getElementsByClassName(clase);
}

function toggle(val) {
    if (id(val).style.display === 'none') {
        id(val).style.display = 'block';
    } else {
        id(val).style.display = 'none';
    }
}

id("btn-menu").addEventListener("click", function () {
    toggle("panel");
});

function addNew(e, dialogo) {
    console.log(e.value);
    if (!e.value) {
        PF(dialogo).show();
    }
}
