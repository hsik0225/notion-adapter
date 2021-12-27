const $CONVERT_BUTTON = $("#convert-button");

const REGEX = /\s*!\[([\w\s\d]+)\]\((.*\.png|jpg)\)/igm;

function toIntelliJ() {
    $CONVERT_BUTTON.replaceWith(`<button id="cancel-button" onclick="">Cancel</button>`);
    $CONVERT_BUTTON.replaceWith(`<button id="convert-button" onclick="toIntelliJ()">Convert</button>`);
}

function toNotion() {
    $CONVERT_BUTTON.replaceWith(`<button id="cancel-button" onclick="">Cancel</button>`);
    let content = $("#text").val();

    const lines = content.split('\n');
    for (let i = 0; i < lines.length; i++) {
        let depth = "";
        if (lines[i].startsWith(" ")) {
            const count = lines[i].search(/\S/g);
            if (count >= 0) {
                depth = "".repeat(count);
            }
        }

        Array.from(lines[i].matchAll(REGEX))
            .map(matches => matches[0])
            .forEach(match => content = content.replace(match, "\r\n\r\n" + depth + match))
    }

    $("#result").replaceWith(
        `<textarea id="result" name="text" cols="80" rows="15" wrap="off">${content}</textarea>`);

    $CONVERT_BUTTON.replaceWith(`<button id="convert-button" onclick="toNotion()">Convert</button>`);
}
