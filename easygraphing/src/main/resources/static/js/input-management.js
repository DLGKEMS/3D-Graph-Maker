var inputStringCount = 0; // 초기 input 개수
var inputIntegerCount = 0;
function addInput() {
    var container = document.getElementById("inputs-container");
    var select = document.querySelector(".custom-select");
    var selectedValue = select.value.split('=') // selectedValue[0] = 컬럼값 selectedValue[1]은 컬럼의 타입

    //var value = selectedValue.split('=');
    console.log(selectedValue[0])
    console.log(selectedValue[1])

    if(selectedValue[1] == "String"){
        inputIntegerCount = 0
        inputString(container)
    }
    else if (selectedValue[1] == "Integer"){
        inputStringCount = 0
        inputInteger(container)
    }
}
function inputString(container){
    var newDiv = document.createElement("div");
    var newStringInput1 = document.createElement("input");
    var newStringInput2 = document.createElement("input");
    var columnText = document.createTextNode(" 컬럼 : ");
    var conditionText = document.createTextNode(" 조건 : ")
    var newSelect = document.createElement("select");

    ["or", "and", "=", "!="].forEach(function(operator) {
        var option = document.createElement("option");
        option.value = operator;
        option.text = operator;
        newSelect.appendChild(option);
    });


    newStringInput1.type = "text";
    newStringInput1.name = "dynamicInput [" + inputIntegerCount + "] ";
    inputIntegerCount++;

    newStringInput2.type = "text";
    newStringInput2.name = "dynamicInput [" + inputIntegerCount + "] ";

    newDiv.appendChild(columnText);
    newDiv.appendChild(newStringInput1);
    newDiv.appendChild(conditionText);
    newDiv.appendChild(newStringInput2);
    newDiv.appendChild(newSelect);

    container.appendChild(newDiv);

    inputStringCount++; // input 개수 증가


}
function inputInteger(container){
    var newDiv = document.createElement("div");
    var newIntegerInput1 = document.createElement("input");
    var newIntegerInput2 = document.createElement("input");
    newIntegerInput1.type = "text";
    newIntegerInput1.name = "dynamicInput[" + inputIntegerCount + "]";
    inputIntegerCount++;

    var newText = document.createTextNode(" <= ");

    newIntegerInput2.type = "text";
    newIntegerInput2.name = "dynamicInput[" + inputIntegerCount + "]";

    newDiv.appendChild(newIntegerInput1);
    newDiv.appendChild(newText);
    newDiv.appendChild(newIntegerInput2);

    container.appendChild(newDiv);

    inputIntegerCount++; // input 개수 증가
}

function removeInput() {
    var container = document.getElementById("inputs-container");
    var inputs = container.querySelectorAll("div");

    // 최소 하나의 input을 유지하기 위해
    if (inputs.length > 0) {
        container.removeChild(inputs[inputs.length - 1]);
        inputCount--; // input 개수 감소
    }
}