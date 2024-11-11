const init = () => {
    let header = document.createElement("p");
    header.innerHTML = "This is from the Javascript file.";

    document.body.appendChild(header);
}

window.onload = init;