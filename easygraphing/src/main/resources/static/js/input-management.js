var inputCount = 0; // 초기 input 개수
//var inputIntegerCount = 0;
function addStringInput(conditionType) {
    var container = document.getElementById("inputs-container");
    var select = document.querySelector(".custom-select");
    var selectedValue = select.value.split('=') // selectedValue[0] = 컬럼값 selectedValue[1]은 컬럼의 타입


    //var value = selectedValue.split('=');

    if(conditionType == "String"){
        //inputIntegerCount = 0
        inputString(container)
    }
    else if (conditionType == "Integer"){
        //inputStringCount = 0
        inputInteger(container)
    }
}
function inputString(container){

    var newDiv = document.createElement("div");
    //var newStringInput1 = document.createElement("input");
    var StringInput = document.createElement("input");
    var columnText = document.createTextNode(" 컬럼 : ");
    var conditionText = document.createTextNode(" 조건 : ")
    var logicSelect = document.createElement("select");
    var equalSelect = document.createElement("select");

    var columnDataInput = document.getElementById("columnData");
    var inputValue = String(columnDataInput.value);
    var columnSelect = document.createElement("select");

    var keyPattern = /\b(\w+)\b(?=\=)/g; // 정규 표현식을 사용하여 키 값을 추출
    var keys = inputValue.match(keyPattern);


    if(inputCount <= 10) {
        columnSelect.name = "columnSelector";
        keys.forEach(function (operator){
            const option = document.createElement("option");
            option.value = operator;
            option.text = operator;
            columnSelect.appendChild(option);
        })

        logicSelect.name = "logicSelector";
        ["or", "and"].forEach(function (operator) {
            const option = document.createElement("option");
            option.value = operator;
            option.text = operator;
            logicSelect.appendChild(option);
        });

        equalSelect.name = "equalSelector";
        ["=", "!="].forEach(function (operator) {
            const equalOption = document.createElement("option");
            equalOption.value = operator;
            equalOption.text = operator;
            equalSelect.appendChild(equalOption);
        });


        // newStringInput1.type = "text";
        // newStringInput1.name = "dynamicInput [" + inputStringCount + "] ";
        inputCount++;

        StringInput.type = "text";
        StringInput.name = "dynamicInput [" + inputCount + "] ";

        if (inputStringCount > 1) {
            newDiv.appendChild(logicSelect);
        }
        newDiv.appendChild(columnText);
        newDiv.appendChild(columnSelect);
        newDiv.appendChild(equalSelect);
        newDiv.appendChild(conditionText);
        newDiv.appendChild(StringInput);
        container.appendChild(newDiv);

        inputCount++; // input 개수 증가
    }
}
function inputInteger(container){
    var newDiv = document.createElement("div");
    //var newStringInput1 = document.createElement("input");
    var newIntegerInput1 = document.createElement("input");
    var newIntegerInput2 = document.createElement("input");
    var columnIntegerText = document.createTextNode(" 컬럼 : ");
    var conditionIntegerText = document.createTextNode(" 조건 : ")
    var logicIntegerSelect = document.createElement("select");
    var equalIntegerSelect = document.createElement("select");

    var columnDataInput = document.getElementById("columnData");
    var inputValue = String(columnDataInput.value);
    var columnSelect = document.createElement("select");

    var keyPattern = /\b(\w+)\b(?=\=)/g; // 정규 표현식을 사용하여 키 값을 추출
    var keys = inputValue.match(keyPattern);


    if(inputCount <= 10) {
        columnSelect.name = "columnSelector";
        keys.forEach(function (operator){
            const option = document.createElement("option");
            option.value = operator;
            option.text = operator;
            columnSelect.appendChild(option);
        })

        logicIntegerSelect.name = "logicSelector";
        ["or", "and"].forEach(function (operator) {
            const option = document.createElement("option");
            option.value = operator;
            option.text = operator;
            logicIntegerSelect.appendChild(option);
        });

        equalIntegerSelect.name = "equalSelector";
        ["=", "<", ">", "<=", ">=", "BETWEEN"].forEach(function (operator) {
            const equalOption = document.createElement("option");
            equalOption.value = operator;
            equalOption.text = operator;
            equalIntegerSelect.appendChild(equalOption);
        });

        // newStringInput1.type = "text";
        // newStringInput1.name = "dynamicInput [" + inputStringCount + "] ";
        inputCount++;
        newIntegerInput1.type = "text";
        newIntegerInput1.name = "dynamicInput [" + inputCount + "] ";
        newIntegerInput2.type = "text";
        newIntegerInput2.name = "dynamicInput [" + inputCount + "] ";

        if (inputCount > 1) {
            newDiv.appendChild(logicSelect);
        }
        newDiv.appendChild(columnIntegerText);
        newDiv.appendChild(columnSelect);
        newDiv.appendChild(conditionIntegerText);
        newDiv.appendChild(newIntegerInput1);
        newDiv.appendChild(equalIntegerSelect);
        newDiv.appendChild(newIntegerInput2);
        container.appendChild(newDiv);

        inputCount++; // input 개수 증가
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