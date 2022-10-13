const registerButton = document.querySelector(".account-button");

registerButton.onclick = () => {
    const accountInputs = document.querySelectorAll(".account-input");
    
    let user = {
        lastName: accountInputs[0].value,
        firstName: accountInputs[1].value,
        email: accountInputs[2].value,
        password: accountInputs[3].value
    }

    //필수요소들
    //JSON.stringify()  ->  js 객체를 JSON문자열로 변환
    //JSON.prase()      ->  JSON 문자열을 js 객체로 변환
    let ajaxOption = {
        async: false,                   //필수
        type: "post",                   //필수
        url: "/api/account/register",   //필수
        contentType: "application/json",//전송할 데이터가 JSON인 경우 필수
        data: JSON.stringify(user),     //전송할 데이터가 있으면
        dataType: "json",               //json외 text 등을 사용할 수 있지만, json 사용함
        success: (response, textStatus, request) => {        //성공시에 실행될 메소드
            console.log(response);
            const successURI = request.getResponseHeader("location");
            location.replace(successURI + "?email=" + response.data);
        },
        error: (error) => {             //실패시에 실행될 메소드
            console.log(error.responseJSON);
            loadErrorMessage(error.responseJSON.data);
            errorBox(error.responseJSON.data);
        }
    }

    $.ajax(ajaxOption);
}

function loadErrorMessage(errors) {
    const errorList = document.querySelector(".errors")
    const errorMsgs = document.querySelector(".error-msgs");
    const errorArray = Object.values(errors);
    
    errorMsgs.innerHTML = "";
    errorArray.forEach(error => {
        errorMsgs.innerHTML += `
                <li>${error}</li>
        `;
    })

    errorList.classList.remove("errors-invisible");
}

function errorBox(errors){
    const inputs = document.querySelectorAll(".account-input");
    const errorKeys = Object.keys(errors);

    inputs.forEach(input => {
        input.classList.remove("account-input-error");
        errorKeys.forEach(key => {
            if(input.type == key){
                input.classList.add("account-input-error");
            }
        })
    })

}