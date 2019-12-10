function fn_validar_dialogo(args, vista) {
    var jqDialog = jQuery('#dlg_mod_' + vista);
    if (args.validationFailed) {
        jqDialog.effect('shake', {times: 3}, 100);
    } else {
        PF('dt_' + vista).clearFilters();
        PF('dlg_mod_' + vista).hide();
    }
}
function fn_cerrar_dialogo(vista) {
    PF('dt_' + vista).clearFilters();
    PF('dlg_mod_' + vista).hide();
}

function fn_reiniciarWZ(vista) {
    PF(vista).back()
}
