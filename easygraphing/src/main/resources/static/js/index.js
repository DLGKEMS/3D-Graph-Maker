import * as THREE from 'three';
import { OrbitControls } from 'three/addons/controls/OrbitControls.js';
import { TextGeometry } from 'three/addons/geometries/TextGeometry.js';
import { FontLoader } from 'three/addons/loaders/FontLoader.js';

export default function init(resultData){
    // 문자열에서 딕셔너리로 변환
    let dataArray = resultData.slice(1, -1).split(',');
    let dataObject = {};
    let graphType = null
    let maxData = 0;

    dataArray.forEach(function(item) {
        const parts = item.split(':');
        if(parts.length!==1){
            dataObject[parts[0].replace(/"/g, '')] = parseInt(parts[1]);
            maxData = Math.max(maxData, parseInt(parts[1]));
        }
    });

    // 그래프 타입
    const graphTypeList = document.getElementsByName('graph-type');
    graphTypeList.forEach((node) => {
        if (node.checked) {
            graphType = node.value;
        }
    })

    // three.js 코드 시작
    const renderer = new THREE.WebGLRenderer({ antialias: true, preserveDrawingBuffer: true});
    const canvasBox=document.getElementById('canvas-box');
    const scene = new THREE.Scene();
    const camera = new THREE.PerspectiveCamera( 75, window.innerWidth / window.innerHeight, 0.1, 1000 );
    const controls = new OrbitControls( camera, renderer.domElement );

    // set renderer
    // renderer.shadowMap.enabled = true;
    // renderer.shadowMap.type = THREE.PCFSoftShadowMap;
    renderer.setSize( canvasBox.offsetWidth, canvasBox.offsetHeight );

    // canvas는 한개만 사용
    if(canvasBox.hasChildNodes()){
        Array.from(canvasBox.childNodes).forEach(child => {
            canvasBox.removeChild(child);
        });
    }
    canvasBox.appendChild( renderer.domElement  );

    // set camera
    camera.position.set( 10, 10, 10 );
    controls.update();

    // set light
    const hemiLight = new THREE.AmbientLight( 0xffffff, 0.5 );
    hemiLight.position.set( 0, 20, 0 );
    scene.add( hemiLight );

    const dirLight = new THREE.DirectionalLight( 0xa0a0a0, 2 );
    dirLight.angle=Math.PI;
    dirLight.position.set( 0, 10, 10 );
    dirLight.castShadow = true;
    dirLight.shadow.camera.top = 20;
    dirLight.shadow.camera.bottom = - 20;
    dirLight.shadow.camera.left = - 20;
    dirLight.shadow.camera.right = 20;
    dirLight.shadow.mapSize.height = 512;
    dirLight.shadow.mapSize.width = 512;
    scene.add( dirLight );

    // set background
    scene.background = new THREE.Color( 0xf2f2f2 );
    scene.fog = new THREE.Fog( 0xf2f2f2, 10,80 );

    // set bottom
    const mesh = new THREE.Mesh(
        new THREE.PlaneGeometry( 500, 500 ),
        new THREE.MeshStandardMaterial({
            blur: [300,100],
            resolution:2048,
            roughness:0,
            metalness:0.5,
            color: 'white'
        })
    )
    mesh.rotation.x = - Math.PI / 2;
    mesh.receiveShadow = true;
    scene.add( mesh );

    const colors=generateRainbowColors(Object.keys(dataObject).length);

    // make bar graph
    if(graphType==="bar"){
        let count = 0;
        Object.keys(dataObject).forEach(function(key,index) {
            makeGraph(count,dataObject[key], scene, colors[count], maxData);
            count+=1;
        });
    }

    // make pie graph
    else if(graphType==="pie"){
        makePieGraph(dataObject,colors,scene)
    }

    // 이미지 저장 기능 추가
    const btnImage = document.getElementById('btn-create-image');
    btnImage.addEventListener('click',()=>{
        createImage(renderer);
    })

    // 옆 내용 추가
    createContent(dataObject,colors);

    // 애니메이션 실행
    animate()
    function animate() {
        requestAnimationFrame( animate );
        renderer.render(scene, camera);
        controls.update();
    }
}

//선형 색 보간
function interpolateColor(color1, color2, factor) {
    const c1 = color1.match(/\d+/g);
    const c2 = color2.match(/\d+/g);
    const result = [];

    for (let i = 0; i < 3; i++) {
        result.push(Math.round(Number(c1[i]) + factor * (Number(c2[i]) - Number(c1[i]))))
    }

    return `rgb(${result[0]}, ${result[1]}, ${result[2]})`;
}

// 그래프 색 설정
function generateRainbowColors(n) {
    const rainbowColors = [
        'rgb(255, 0, 0)',     // 빨강
        'rgb(255, 127, 0)',   // 주황
        'rgb(255, 255, 0)',   // 노랑
        'rgb(0, 255, 0)',     // 초록
        'rgb(0, 0, 255)',     // 파랑
        'rgb(75, 0, 130)',    // 남색
        'rgb(148, 0, 211)'    // 보라
    ];

    const colors = [];

    if (n <= 0) {
        return colors;
    }

    if (n === 1) {
        return [rainbowColors[0]];
    }

    for (let i = 0; i < n; i++) {
        const index = i / (n - 1) * (rainbowColors.length - 1);
        const startIndex = Math.floor(index);
        const endIndex = Math.min(startIndex + 1, rainbowColors.length - 1);
        const factor = index - startIndex;
        const color = interpolateColor(rainbowColors[startIndex], rainbowColors[endIndex], factor);
        colors.push(color);
    }

    return colors;
}

//막대 그래프 생성
function makeGraph(index, value, scene,color, maxData){
    // graph
    const geom = new THREE.BoxGeometry();
    const material = new THREE.MeshStandardMaterial();
    const maxVal = 15

    const cube = new THREE.Mesh( geom, material );
    cube.material.color.set(color);
    cube.scale.y = (value / maxData) * maxVal;
    cube.scale.x=0.5
    cube.position.set(index*0.7, (value / maxData) * maxVal/2 , 0);
    cube.castShadow =true;
    scene.add( cube );
}

// 파이 그래프 생성
function makePieGraph(dataObject,colors,scene){
    let totalValue = 0;
    for (let key in dataObject) {
        totalValue+=dataObject[key]
    }

    let currentAngle = -Math.PI / 2;
    let count = 0;
    Object.keys(dataObject).forEach(key => {
        const geometry = new THREE.CylinderGeometry(1, 1, 0.2, 64, 1, false, currentAngle, Math.PI * 2 * (dataObject[key] / totalValue));
        const material = new THREE.MeshBasicMaterial({ color: colors[count], side: THREE.DoubleSide });
        const pieSegment = new THREE.Mesh(geometry, material);
        scene.add(pieSegment);

        currentAngle += Math.PI * 2 * (dataObject[key] / totalValue);
        count+=1;
    });
}

// 스크린샷
function createImage(renderer){
    const strMime = 'image/jpeg';
    let img;
        try {
            // 텍스트용
            // console.log(document.getElementById('canvas-box').children[0]);
            html2canvas(document.querySelector("#canvas-text-box")).then(canvas => {
                let test=canvas.toDataURL('image/jpeg')
                let link2 = document.createElement('a')
                document.body.appendChild(link2);

                link2.href = test;
                link2.download = 'text.jpg';
                link2.click();
            });
             let imgData = document.getElementById('canvas-box').children[0].toDataURL(strMime);
             img = imgData.replace(strMime, "image/octet-stream");
             const link = document.createElement('a');
             document.body.appendChild(link);
             link.download = 'image.jpg';
             link.href = imgData;
             link.click();
            document.body.removeChild(link);
        } catch (e) {
            console.log(e);
        }
}

// 설명 디테일 추가 text
function createContent(dataObject,colors){
    if(Object.keys(dataObject).length===0){
        return;
    }
    const ctb = document.getElementById("canvas-text-box");
    ctb.innerHTML = '';
    let count =0;
    Object.keys(dataObject).forEach(function(key) {
        const div = document.createElement("div")
        div.className="col-6 d-flex"
        const p = document.createElement("p");
        p.textContent = `${key}(${dataObject[key]})`;
        p.className="w-50"
        div.appendChild(p);

        const colorDiv = document.createElement("div");
        colorDiv.style.background=colors[count];
        colorDiv.style.height='4px';
        colorDiv.className="w-50"
        colorDiv.style.marginTop="10px"
        div.appendChild(colorDiv);
        ctb.appendChild(div);
        count +=1;
    });
}

init(document.getElementById("resultData").value);