var inputCount = 0; // 초기 input 개수
//var inputIntegerCount = 0;

function addStringInput() {
    var bool = false;
    var columnType = "";
    var container = document.getElementById("inputs-container");

    var columnDataInput = document.getElementById("columnData");

    // 문자열을 객체로 변환
    var inputKeyValuePairs = columnDataInput.value.replace(/[{}]/g, '').split(', ');
    var inputValue = {};

    // 키-값 쌍을 객체로 변환
    inputKeyValuePairs.forEach(function(pair) {
        var keyvalue = pair.split('='); // 앞뒤의 공백 제거
        inputValue[keyvalue[0]] = keyvalue[1]; // 키와 값을 반대로 저장
    });

    var newDiv = document.createElement("div"); // 새로운 div 생성

    var columnSelect = document.createElement("select");
    columnSelect.name = "columnSelector";
    // 객체의 키 값을 가져와서 옵션으로 추가
    Object.keys(inputValue).forEach(function (key) {
        const option = document.createElement("option");
        option.value = inputValue[key];
        option.text = key;
        columnSelect.appendChild(option);
    });

    console.log("columnType", columnType)

    newDiv.appendChild(columnSelect); // newDiv에 select 추가

    // 여기서 다른 동작 수행 가능
    columnSelect.addEventListener("change", function () {
        // 선택된 옵션의 값을 가져오기
        var selectedText = columnSelect.options[columnSelect.selectedIndex].text; // 선택된 옵션의 텍스트
        var selectedValue = inputValue[selectedText];
        console.log(selectedText, selectedValue);
        columnType = selectedValue;
        if(bool){
            newDiv.removeChild(equalSelect);
            newDiv.removeChild(StringInput);
        }
        if (columnType === "String") {
            var equalSelect = document.createElement("select");
            equalSelect.name = "equalSelector";
            ["=", "!=", "LIKE"].forEach(function (operator) {
                const equalOption = document.createElement("option");
                equalOption.value = operator;
                equalOption.text = operator;
                equalSelect.appendChild(equalOption);
            });

            var StringInput = document.createElement("input");
            inputCount++;
            StringInput.type = "text";
            StringInput.name = "dynamicInput [" + inputCount + "] ";

            newDiv.appendChild(equalSelect);
            newDiv.appendChild(StringInput);
            bool = true;
            console.log(inputCount)
        }
    });

    container.appendChild(newDiv); // container에 newDiv 추가
    //columnSelect.addEventListener("change", onSelectChange);
}

function removeInput() {
    var container = document.getElementById("inputs-container");
    var inputs = container.querySelectorAll("div");

    if(inputCount >= 2) {
        inputCount = inputCount - 2;
    }
    // 최소 하나의 input을 유지하기 위해
    if (inputs.length > 0) {
        container.removeChild(inputs[inputs.length - 1]);
        inputCount--; // input 개수 감소
    }
}

function removeInput() {
    var container = document.getElementById("inputs-container");
    var inputs = container.querySelectorAll("div");

    if(inputCount >= 2) {
        inputCount = inputCount - 2;
    }
    // 최소 하나의 input을 유지하기 위해
    if (inputs.length > 0) {
        container.removeChild(inputs[inputs.length - 1]);
        inputCount--; // input 개수 감소
    }
}
// var logicSelect = document.createElement("select");
// logicSelect.name = "logicSelector";
// ["or", "and"].forEach(function (operator) {
//     const option = document.createElement("option");
//     option.value = operator;
//     option.text = operator;
//     logicSelect.appendChild(option);
// });
//
// if (inputCount > 1) {
//     newDiv.appendChild(logicSelect);
// }// 조건이 2개 이상일때 logicSelect 생성
//
// newDiv.appendChild(columnText);
//
// container.appendChild(columnSelect);
//
// console.log("선택된 값 : ", columnSelect.value)
// var selectedValue = columnSelect.value;
// if(selectedValue === "String"){
//
//     var conditionText = document.createTextNode(" 조건 : ")
//     var equalSelect = document.createElement("select");
//     var StringInput = document.createElement("input");
//
//     equalSelect.name = "equalSelector";
//     ["=", "!=", "LIKE"].forEach(function (operator) {
//         const equalOption = document.createElement("option");
//         equalOption.value = operator;
//         equalOption.text = operator;
//         equalSelect.appendChild(equalOption);
//     });
//
//     inputCount++;
//     StringInput.type = "text";
//     StringInput.name = "dynamicInput [" + inputCount + "] ";
//
//     newDiv.appendChild(equalSelect);
//     newDiv.appendChild(conditionText);
//     newDiv.appendChild(StringInput);
//     container.appendChild(newDiv);