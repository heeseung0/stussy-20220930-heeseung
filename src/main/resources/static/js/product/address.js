class AddressApi {
    static #instance = null;

    static getInstance() {
        if(this.#instance == null){
            this.#instance = new AddressApi();
        }
        return this.#instance;
    }

    #daumApi = null;
    constructor() {
        this.#daumApi = new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
                // 예제를 참고하여 다양한 활용법을 확인해 보세요.
                const addressZonecode = document.querySelector(".address-zonecode");
                const addressSido = document.querySelector(".address-sido");
                const addressSigungu = document.querySelector(".address-sigungu");
                const addressAll = document.querySelector(".address-all");

                addressZonecode.value = data.zonecode;
                addressSido.value = data.sido;
                addressSigungu.value = data.sigungu;
                addressAll.value = data.address + `
                    ${data.buildingName != "" ?
                    "(" + data.buildingName + ")" : ""}`;
            }
        });
    }

    setAddressInfo(data) {

    }


    addAddressButtonEvent() {
        document.querySelector(".address-button").onclick = () => {
            this.#daumApi.open();
        }
    }
}



