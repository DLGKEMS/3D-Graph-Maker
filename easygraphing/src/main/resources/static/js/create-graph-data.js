function sendSelectedData() {

    // 선택된 값을 가져오기
    var selectedValue = document.querySelector('select[name="selectedOption"]').value;

    // Ajax 요청 생성 (이 부분은 이미 정의된 것을 사용)
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/your-endpoint', true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    // 요청이 완료되었을 때의 이벤트 처리
    xhr.onload = function () {
        if (xhr.status === 200) {
            // 서버에서 받은 응답 데이터를 처리할 수 있음
            console.log(xhr.responseText);
            // var graphType = document.getElementById('graph-type').value;
            // var resultData = xhr.responseText; // 서버에서 받은 데이터
            // console.log(resultData);


        } else {
            console.error('Error sending data');
        }
    };

    // 데이터를 JSON 문자열로 변환하여 전송
    xhr.send(JSON.stringify({ selectedValue: selectedValue }));
}

// 버튼 클릭 이벤트에 함수 연결
document.getElementById('submitBtn').addEventListener('click', sendSelectedData);