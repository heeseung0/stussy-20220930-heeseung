const accountButtons = document.querySelectorAll(".account-button");

for(let accountButton of accountButtons){
    accountButton.onclick = () => {
        if(accountButton.textContent == "신규가입"){
            location.href="/account/register";
        }
    }
}