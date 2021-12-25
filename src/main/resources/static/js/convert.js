const REGEX = /!\[([\w\s\d]+)\]\((.*\.png|jpg)\)/ig
const $CONVERT_BUTTON = $("#convert-button");

function toIntelliJ() {
    $CONVERT_BUTTON.replaceWith(`<button id="cancel-button" onclick="">Cancel</button>`);
    $CONVERT_BUTTON.replaceWith(`<button id="convert-button" onclick="toIntelliJ()">Convert</button>`);
}

function toNotion() {
    $CONVERT_BUTTON.replaceWith(`<button id="cancel-button" onclick="">Cancel</button>`);
    const content = $("#text").val();
    console.log(content);
    $CONVERT_BUTTON.replaceWith(`<button id="convert-button" onclick="toNotion()">Convert</button>`);
}
