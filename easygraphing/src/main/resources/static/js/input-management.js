var inputCount = 0; // 초기 input 개수
var bool = false;
var columnType = "";
var equalSelect, StringInput, integerEqualSelect, IntegerInput1, IntegerInput2;
function addStringInput() {
    bool = false;
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

    var logicSelect = document.createElement("select");
    logicSelect.name = "logicSelector";
    ["or", "and"].forEach(function (operator) {
        const option = document.createElement("option");
        option.value = operator;
        option.text = operator;
        logicSelect.appendChild(option);
    });
    var newDiv = document.createElement("div"); // 새로운 div 생성

    var columnSelect = document.createElement("select");
    columnSelect.name = "columnSelector";
    // 객체의 키 값을 가져와서 옵션으로 추가
    Object.keys(inputValue).forEach(function (key) {
        const option = document.createElement("option");
        option.value = inputValue[key];
        option.text = key;
        console.log(option.value, option.text)
        columnSelect.appendChild(option);
    });

    console.log("columnType", columnType)
    if (inputCount >= 1) {
        newDiv.appendChild(logicSelect);
    }
    newDiv.appendChild(columnSelect); // newDiv에 select 추가

    // 여기서 다른 동작 수행 가능
    columnSelect.addEventListener("change", function () {
        // 선택된 옵션의 값을 가져오기
        var selectedText = columnSelect.options[columnSelect.selectedIndex].text; // 선택된 옵션의 텍스트
        var selectedValue = inputValue[selectedText];

        console.log(selectedText, selectedValue);
        columnType = selectedValue;
        if(bool){
            if (newDiv.contains(equalSelect)) {
                newDiv.removeChild(equalSelect);
                inputCount --;
            }
            if (newDiv.contains(StringInput)) {
                newDiv.removeChild(StringInput);
            }
            if (newDiv.contains(integerEqualSelect)) {
                newDiv.removeChild(integerEqualSelect);
                // inputCount = inputCount - 1;
            }
            if (newDiv.contains(IntegerInput1)) {
                newDiv.removeChild(IntegerInput1);
                inputCount --;
            }
            if (newDiv.contains(IntegerInput2)) {
                newDiv.removeChild(IntegerInput2)
                inputCount --;;
            }
            bool = false;
        }
        if (columnType === "String") {
            StringInput = document.createElement("input");
            StringInput.type = "text";
            equalSelect = document.createElement("select");
            equalSelect.name = "equalSelector";
            ["=", "!=", "LIKE"].forEach(function (operator) {
                const equalOption = document.createElement("option");
                equalOption.value = operator;
                equalOption.text = operator;
                equalSelect.appendChild(equalOption);
            });

            inputCount++;
            StringInput.name = "dynamicInput [" + inputCount + "] ";

            newDiv.appendChild(equalSelect);
            newDiv.appendChild(StringInput);
            bool = true;
            console.log(inputCount)
        }
        else if(columnType === "Integer"){
            integerEqualSelect = document.createElement("select");
            integerEqualSelect.name = "equalSelector";
            ["=", "BETWEEN", ">=", "<="].forEach(function (operator) {
                const equalOption = document.createElement("option");
                equalOption.value = operator;
                equalOption.text = operator;
                integerEqualSelect.appendChild(equalOption);
            });
            inputCount++;
            IntegerInput1 = document.createElement("input");
            IntegerInput1.type = "text";
            IntegerInput2 = document.createElement("input");
            IntegerInput2.type = "text";

            IntegerInput1.name = "dynamicInput [" + inputCount + "] ";
            newDiv.appendChild(integerEqualSelect);
            newDiv.appendChild(IntegerInput1);
            bool = true;

            integerEqualSelect.addEventListener("change", function () {
                if(bool){
                    if (newDiv.contains(IntegerInput1)) {
                        newDiv.removeChild(IntegerInput1);
                        inputCount--;
                    }
                    if (newDiv.contains(IntegerInput2)) {
                        newDiv.removeChild(IntegerInput2);
                        inputCount--;
                    }
                    if(newDiv.contains(integerEqualSelect)){
                        newDiv.removeChild(integerEqualSelect);
                    }
                }
                if (integerEqualSelect.options[integerEqualSelect.selectedIndex].text === "BETWEEN") {
                    inputCount++;
                    IntegerInput1.name = "dynamicInput [" + inputCount + "] ";
                    inputCount++;
                    IntegerInput2.name = "dynamicInput [" + inputCount + "] ";

                    newDiv.appendChild(IntegerInput1);
                    newDiv.appendChild(integerEqualSelect);
                    newDiv.appendChild(IntegerInput2);
                    bool = true;
                }
                else {
                    inputCount++;
                    IntegerInput1.name = "dynamicInput [" + inputCount + "] ";
                    newDiv.appendChild(integerEqualSelect);
                    newDiv.appendChild(IntegerInput1);
                    bool = true;
                }
            });
        }
    //columnSelect.addEventListener("change", onSelectChange);
    });
    container.appendChild(newDiv); // container에 newDiv 추가
}

function removeInput() {
    var container = document.getElementById("inputs-container");
    var inputs = container.querySelectorAll("div");

    // if(inputCount >= 2) {
    //     inputCount = inputCount - 2;
    // }
    // 최소 하나의 input을 유지하기 위해
    if (inputs.length > 0) {
        container.removeChild(inputs[inputs.length - 1]);
        if(inputs.length < inputCount){
            inputCount = inputCount - 2;
        }
        else {
            inputCount--; // input 개수 감소
        }
    }
}