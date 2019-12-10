String.prototype.capitalize = function () {
    return this.replace(/(^|\s)([a-z])/g, function (m, p1, p2) {
        return p1 + p2.toUpperCase();
    });
};

function upperCase(e) {
    e.value = e.value.toUpperCase();
}

function capitalize(e) {
    e.value = e.value.toLowerCase();
    var texto = e.value.toString();
    texto = texto.capitalize();
    e.value = texto;
}

function adultos() {
    fec = new Date();
    fec.setFullYear(fec.getFullYear()+1);
    return '21-07-2001';
}