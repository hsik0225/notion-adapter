$(document).ready(function () {
    $("#from-editor").change(decideToEditor);
});

function decideToEditor() {
    $("#content").empty();

    const fromEditors = $("#from-editor").val();
    if ("notion" === fromEditors) {
        showNotionToIntelliJForm();
    } else {
        showIntelliJToNotionForm();
    }

    showConvertButton();
}

function showNotionToIntelliJForm() {
    const toEditors = "IntelliJ";
    $("#to-editor").html("To: " + toEditors);
    $("#content").append(
        `<div id="setting">
            <label> Notion Link: <input type="text"></label><br>
            <label> Title: <input type="text"></label><br>
            <label> Date: <input type="text"></label><br>
            <label> Categories: <input type="text"></label><br>
        </div>`);
}

function showIntelliJToNotionForm() {
    const toEditors = "Notion";
    $("#to-editor").html("To: " + toEditors);
    $("#content").append(
        `<div id="text">
            <label>
                <textarea id="text" name="text" cols="80" rows="15" wrap="off" onFocus="this.value='';this.onfocus=null;">
                    Enter your text...
                </textarea>
            </label>
        </div>`);
}

function showConvertButton() {
    $("#content").append(
        `<div id="convert-button">
            <button class="convert-button" id="to-notion-button">Convert</button>
        </div>`);
}

