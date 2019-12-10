/* global PrimeFaces */
/*
 * 'blue'
 'ash'
 'kashmir'
 'orange'
 'midnight'
 'sunset'
 'marley'
 'harvey'
 'vanusa'
 'skyline'
 'royal'
 'disco'
 'sky'
 'rose'
 'revolt'
 'forest'
 'night'
 'apricot'
 'faraway'
 'grape'
 'river'
 'dock'
 'materialone'
 'materialtwo'
 'polygons'
 'connectionsone'
 'connectionstwo'
 'road'
 'reflection'
 'waves'
 'sandiego'
 'architecture'
 'snow'
 'palm'
 'fluid'
 'balloon'
 'downtown'
 'perfection'
 'northern'
 'highline'
 'mural'
 'aeriel'
 'wing'
 'skyscraper'
 'wall'
 'dawn'
 'lille'
 'condo'
 'waterfall'
 'coffee'
 'mountain'
 'lights'
 'desert'
 'beach'
 'classic'
 'hazy'
 'exposure'
 'norge'
 'island'
 'station'
 'fruity'
 'tropical'
 'beyoglu'
 'timelapse'
 'crystal'
 'aquarelle'
 'canvas'
 'olympic'
 'circuit'
 'flamingo'
 'flight'
 'tractor'
 'volcano'
 'pine'
 'emptiness'
 'splash'
 'urban'
 'bloom'
 'tinfoil'
 'hallway'
 'seagull'
 'city'
 'jet'
 'louisville'
 'spray'
 'symmetry'
 'destination'
 * @param {type} tema
 * @returns {Boolean}
 */
function fn_menuHColor(color) {
    PrimeFaces.SapphireConfigurator.changeSectionTheme(color, 'layout-topbar');
    return false;
}

/*
 *
 'light', 
 'dark', 
 'blue', 
 'ash', 
 'kashmir', 
 'orange', 
 'midnight', 
 'sunset', 
 'marley', 
 'harvey', 
 'vanusa', 
 'skyline', 
 'royal', 
 'disco', 
 'sky', 
 'rose', 
 'revolt', 
 'forest', 
 'night', 
 'apricot', 
 'faraway', 
 'grape', 
 'river', 
 'dock', 
 'materialone', 
 'materialtwo', 
 'polygons', 
 'connectionsone', 
 'connectionstwo', 
 'road', 
 'reflection', 
 'waves', 
 'sandiego', 
 'architecture', 
 'snow', 
 'palm', 
 'fluid', 
 'balloon', 
 'downtown', 
 'perfection', 
 'northern', 
 'highline', 
 'mural', 
 'aeriel', 
 'wing', 
 'skyscraper', 
 'wall', 
 'dawn', 
 'lille', 
 'condo', 
 'waterfall', 
 'coffee', 
 'mountain', 
 'lights', 
 'desert', 
 'beach', 
 'classic', 
 'hazy', 
 'exposure', 
 'norge', 
 'island', 
 'station', 
 'fruity', 
 'tropical', 
 'beyoglu', 
 'timelapse', 
 'crystal', 
 'aquarelle', 
 'canvas', 
 'olympic', 
 'circuit', 
 'flamingo', 
 'flight', 
 'tractor', 
 'volcano', 
 'pine', 
 'emptiness', 
 'splash', 
 'urban', 
 'bloom', 
 'tinfoil', 
 'hallway', 
 'seagull', 
 'city', 
 'jet', 
 'louisville', 
 'spray', 
 'symmetry', 
 'destination', 
 * @param {type} tema
 * @returns {Boolean}
 */
function fn_menuVColor(color) {
    PrimeFaces.SapphireConfigurator.changeSectionTheme(color, 'layout-menu');
    return false;
}
/*  Tamaños:
 *  - small
 *  - medium
 *  - large
 */

function fn_sizeMenuV(select) {
    $('#selectedSizeMenuV').remove();
    $(select).parent().append('<i id="selectedSizeMenuV" class="material-icons">check</i>');
    PrimeFaces.SapphireConfigurator.changeSectionTheme($(select).attr('title'), 'layout-top');
}

/*  Positions:
 * - Horizontal
 * - Vertical
 */
function fn_changeMenuPosition(select) {
    $('#selectedPositioMenu').remove();
    $(select).parent().append('<i id="selectedPositioMenu" class="material-icons">check</i>');
    PrimeFaces.SapphireConfigurator.changeMenuToHorizontal(true);
    return false;
}

function abrirEnPestana(url) {
		var a = document.createElement("a");
		a.href = url;
		a.click();
	}

$(function () {
    $("#landing-menu-button").on("click", function (e) {
        $("#landing-menu").toggleClass("landing-menu-active");
        $("#landing-menu-button").toggleClass("landing-menu-active");
        if ($("#landing-menu-button").hasClass("landing-menu-active")) {
            $("#landing-menu-button")
                    .children("i")
                    .text("close");
        } else {
            $("#landing-menu-button")
                    .children("i")
                    .text("menu");
        }
        e.preventDefault();
    });

    $("#landing-menu").on("click", function () {
        $("#landing-menu").removeClass("landing-menu-active");
        $("#landing-menu-button").removeClass("landing-menu-active");
        $("#landing-menu-button")
                .children("i")
                .text("menu");
    });
});
/*
 PrimeFaces.SapphireConfigurator.changeComponentsTheme('amber');
 PrimeFaces.SapphireConfigurator.changeComponentsTheme('blue');
 PrimeFaces.SapphireConfigurator.changeComponentsTheme('bluegray');
 PrimeFaces.SapphireConfigurator.changeComponentsTheme('brown');
 PrimeFaces.SapphireConfigurator.changeComponentsTheme('cyan');
 PrimeFaces.SapphireConfigurator.changeComponentsTheme('deeporange');
 PrimeFaces.SapphireConfigurator.changeComponentsTheme('deeppurple');
 PrimeFaces.SapphireConfigurator.changeComponentsTheme('gray');
 PrimeFaces.SapphireConfigurator.changeComponentsTheme('green');
 PrimeFaces.SapphireConfigurator.changeComponentsTheme('indigo');
 PrimeFaces.SapphireConfigurator.changeComponentsTheme('lightblue');
 PrimeFaces.SapphireConfigurator.changeComponentsTheme('lightgreen');
 PrimeFaces.SapphireConfigurator.changeComponentsTheme('lime');
 PrimeFaces.SapphireConfigurator.changeComponentsTheme('orange');
 PrimeFaces.SapphireConfigurator.changeComponentsTheme('pink');
 PrimeFaces.SapphireConfigurator.changeComponentsTheme('purple');
 PrimeFaces.SapphireConfigurator.changeComponentsTheme('teal');
 PrimeFaces.SapphireConfigurator.changeComponentsTheme('yellow');
 */

/*
 PrimeFaces.SapphireConfigurator.changePrimaryColor('amber');
 ---------------------------------------->>>>>>>>>>>>>>>>PrimeFaces.SapphireConfigurator.changePrimaryColor('blue');
 PrimeFaces.SapphireConfigurator.changePrimaryColor('bluegray');
 PrimeFaces.SapphireConfigurator.changePrimaryColor('brown');
 PrimeFaces.SapphireConfigurator.changePrimaryColor('cyan');
 PrimeFaces.SapphireConfigurator.changePrimaryColor('deeporange');
 PrimeFaces.SapphireConfigurator.changePrimaryColor('deeppurple');
 PrimeFaces.SapphireConfigurator.changePrimaryColor('gray');
 PrimeFaces.SapphireConfigurator.changePrimaryColor('green');
 PrimeFaces.SapphireConfigurator.changePrimaryColor('indigo');
 PrimeFaces.SapphireConfigurator.changePrimaryColor('lightblue');
 PrimeFaces.SapphireConfigurator.changePrimaryColor('lightgreen');
 PrimeFaces.SapphireConfigurator.changePrimaryColor('lime');
 PrimeFaces.SapphireConfigurator.changePrimaryColor('orange');
 PrimeFaces.SapphireConfigurator.changePrimaryColor('pink');
 PrimeFaces.SapphireConfigurator.changePrimaryColor('purple');
 PrimeFaces.SapphireConfigurator.changePrimaryColor('teal');
 PrimeFaces.SapphireConfigurator.changePrimaryColor('yellow');*/