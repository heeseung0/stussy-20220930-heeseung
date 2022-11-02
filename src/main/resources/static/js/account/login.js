const accountButton = document.querySelector(".account-button");

accountButton.onclick = () => {
    if(accountButton.textContent == "신규가입"){
        location.href="/account/register";
    }
}