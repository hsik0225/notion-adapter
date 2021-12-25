$(document).ready(function () {
    $("#from-editor").change(decideToEditor);
});

function decideToEditor() {
    $("#content").empty();

    const fromEditors = $("#from-editor :selected").text();
    if ("Notion" === fromEditors) {
        showNotionToIntelliJForm();
    } else {
        showIntelliJToNotionForm();
    }
}

function showNotionToIntelliJForm() {
    showForm("IntelliJ",
        `<div id="option">
            <label> Notion Link: <input type="text"></label><br>
            <label> Title: <input type="text"></label><br>
            <label> Date: <input type="text"></label><br>
            <label> Categories: <input type="text"></label><br>
        </div>`);
}

function showIntelliJToNotionForm() {
    showForm("Notion",
        `<textarea id="text" name="text" cols="80" rows="15" wrap="off" onFocus="this.value='';this.onfocus=null;">
                    Enter your text...
              </textarea>`);
}

function showForm(toEditors, form) {
    $("#to-editor").html("To: " + toEditors);
    $("#content").append(form)
        .append(`<button id="convert-button" onclick="to${toEditors}()">Convert</button>`);
}
