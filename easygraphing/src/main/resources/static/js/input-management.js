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
    var newInput = document.createElement("input");

    var newText = document.createTextNode("조건 : ");

    newInput.type = "text";
    newInput.name = "dynamicInput[" + inputStringCount + "]";
    newDiv.appendChild(newInput);
    container.appendChild(newDiv);

    inputStringCount++; // input 개수 증가
}
function inputInteger(container){
    var newDiv = document.createElement("div");
    var newInput1 = document.createElement("input");
    var newInput2 = document.createElement("input");
    newInput1.type = "text";
    newInput1.name = "dynamicInput[" + inputIntegerCount + "]";
    inputIntegerCount++;

    var newText = document.createTextNode(" <= ");

    newInput2.type = "text";
    newInput2.name = "dynamicInput[" + inputIntegerCount + "]";

    newDiv.appendChild(newInput1);
    newDiv.appendChild(newText);
    newDiv.appendChild(newInput2);

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